-- Register a New User
DELIMITER //
CREATE PROCEDURE RegisterUser(
    IN p_first_name VARCHAR(50),
    IN p_last_name VARCHAR(50),
    IN p_email VARCHAR(100),
    IN p_password VARCHAR(255),
    IN p_phone VARCHAR(50),
    IN p_birth_date DATE,
    IN p_country VARCHAR(50),
    IN p_city VARCHAR(50),
    IN p_is_mentor BOOLEAN,
    IN p_is_mentee BOOLEAN,
    IN p_is_active BOOLEAN
)
BEGIN
    INSERT INTO Users (first_name, last_name, email, password, phone, birth_date, country , city , is_mentor, is_mentee, is_active) 
    VALUES (p_first_name, p_last_name, p_email, p_password, p_phone, p_birth_date, p_country , p_city, p_is_mentor, p_is_mentee, TRUE);
END //
DELIMITER ;


-- Assign a Skill to a User
DELIMITER //
CREATE PROCEDURE AssignSkill(
    IN p_user_id INT,
    IN p_name VARCHAR(50),
    IN p_level ENUM('BEGINNER', 'INTERMEDIATE', 'ADVANCED')
)
BEGIN
	 -- Check if user exists
    IF EXISTS (SELECT 1 FROM Users WHERE id = p_user_id) THEN
        -- Insert skill for the existing user
        INSERT INTO User_Skills (user_id, name, level)
        VALUES (p_user_id, p_name, p_level);
    ELSE
        -- Return error message
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'User does not exist. Cannot assign skill.';
    END IF;
   
END //
DELIMITER ;


-- Add a Mentorship Match
DELIMITER //
CREATE PROCEDURE CreateMentorshipMatch(
    IN p_mentor_id INT,
    IN p_mentee_id INT,
    IN p_topic VARCHAR(50)
)
BEGIN
	 -- Check if user exists
    IF EXISTS (SELECT 1 FROM Users WHERE id = p_mentor_id) AND (SELECT 1 FROM Users WHERE id = p_mentee_id) THEN
        INSERT INTO Mentorship_Match (mentor_id, mentee_id, topic, status, progress) 
		VALUES (p_mentor_id, p_mentee_id, p_topic, 'NEW', 0);
    ELSE
        -- Return error message
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'User does not exist.';
    END IF;
   
END //
DELIMITER ;


-- Schedule a Mentorship Session
DELIMITER //
CREATE PROCEDURE ScheduleMentorshipSession(
    IN p_match_id INT,
    IN p_scheduled_date DATETIME
)
BEGIN
    INSERT INTO Mentorship_Session (mentorship_match_id, status, scheduled_date) 
    VALUES (p_match_id, 'PENDING', p_scheduled_date);
END //
DELIMITER ;


-- Update Session Status
DELIMITER //
CREATE PROCEDURE UpdateSessionStatus(
    IN p_session_id INT,
    IN p_status ENUM('PENDING', 'ACCEPTED', 'COMPLETED', 'CANCELED')
)
BEGIN
    UPDATE Mentorship_Session 
    SET status = p_status 
    WHERE id = p_session_id;
END //
DELIMITER ;


-- Add Feedback for a Session
DELIMITER //
CREATE PROCEDURE AddSessionFeedback(
    IN p_session_id INT,
    IN p_feedback TEXT,
    IN p_rate DECIMAL(2,1)
)
BEGIN
    INSERT INTO Session_Feedback (session_id, mentee_feedback, rate)
    VALUES (p_session_id, p_feedback, p_rate);
END //
DELIMITER ;




