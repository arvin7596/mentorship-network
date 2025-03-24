-- Prevent Overlapping Mentor Availability
DELIMITER //

CREATE TRIGGER check_mentor_availability
BEFORE INSERT ON Mentor_Availability
FOR EACH ROW
BEGIN
    DECLARE conflict_count INT;
    
    SELECT COUNT(*) INTO conflict_count
    FROM Mentor_Availability
    WHERE mentor_id = NEW.mentor_id 
      AND weekday = NEW.weekday
      AND ((NEW.start_time BETWEEN start_time AND end_date) 
           OR (NEW.end_date BETWEEN start_time AND end_date));

    IF conflict_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: Overlapping mentor availability.';
    END IF;
END;

//

DELIMITER ;


-- Automatically Set Mentorship Status to 'Completed' When Progress Reaches 100%
DELIMITER //

CREATE TRIGGER update_mentorship_status
BEFORE UPDATE ON Mentorship_Match
FOR EACH ROW
BEGIN
    IF NEW.progress = 100 THEN
        SET NEW.status = 'completed';
    END IF;
END;

//

DELIMITER ;



-- Prevent Mentors from Assigning Themselves as Mentees
DELIMITER //

CREATE TRIGGER prevent_self_mentorship
BEFORE INSERT ON Mentorship_Match
FOR EACH ROW
BEGIN
    IF NEW.mentor_id = NEW.mentee_id THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: A user cannot mentor themselves.';
    END IF;
END;

//

DELIMITER ;


-- Preventing Duplicate Skills for Users
DELIMITER //

CREATE TRIGGER prevent_duplicate_skills
BEFORE INSERT ON User_Skills
FOR EACH ROW
BEGIN
    IF EXISTS (SELECT 1 FROM User_Skills 
               WHERE user_id = NEW.user_id 
               AND name = NEW.name) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Duplicate skill: The user already has this skill.';
    END IF;
END //

DELIMITER ;



-- Ensuring Only Active Mentors Can Be Assigned Mentees
DELIMITER //

CREATE TRIGGER check_active_mentor
BEFORE INSERT ON Mentorship_Match
FOR EACH ROW
BEGIN
    DECLARE mentor_status BOOLEAN;
    
    -- Get the is_active status of the mentor
    SELECT is_active INTO mentor_status FROM Users WHERE id = NEW.mentor_id;
    
    -- If mentor is inactive, prevent insertion
    IF mentor_status = FALSE THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Mentor is inactive. Cannot assign mentees.';
    END IF;
END //

DELIMITER ;

