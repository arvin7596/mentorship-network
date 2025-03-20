DELIMITER //

CREATE PROCEDURE AssignMenteeToMentor(
    IN mentorID INT, 
    IN menteeID INT, 
    IN topic VARCHAR(50), 
    IN sessionDate DATETIME
)
BEGIN
    DECLARE mentorshipID INT;
    DECLARE mentorStatus BOOLEAN;
	DECLARE transaction_successful BOOLEAN DEFAULT TRUE;
    
    -- Start transaction
    START TRANSACTION;
    
    -- Check if the mentor is active
    SELECT is_active INTO mentorStatus FROM Users WHERE id = mentorID;
    
    IF mentorStatus = FALSE THEN
		SET transaction_successful = FALSE; 
        SELECT ("Mentor is inactive. Cannot assign mentee.") AS message;
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Inactive Mentor';
    END IF;


	IF transaction_successful = TRUE THEN
    -- Insert into Mentorship_Match
    INSERT INTO Mentorship_Match (mentor_id, mentee_id, topic, status, progress) 
    VALUES (mentorID, menteeID, topic);
    
    -- Get the last inserted mentorship ID
    SET mentorshipID = LAST_INSERT_ID();
    
    -- Insert into Mentorship_Session
    INSERT INTO Mentorship_Session (mentorship_match_id, status, scheduled_date)
    VALUES (mentorshipID, 'PENDING', sessionDate);

    -- Commit the transaction
    COMMIT;
    SELECT 'Transaction successful' AS message; 
    END IF;
END //

DELIMITER ;