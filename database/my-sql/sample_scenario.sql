USE mentorship_network;

INSERT INTO Users (first_name, last_name, email, password, phone, birth_date, country , city , is_mentor, is_mentee, is_active) VALUES
('Sanaz', 'Hosseini', 'sanaz@gmail.com', 'pass123', '+491234567890', '1994-05-16', 'Germany', 'Berlin', FALSE, TRUE, TRUE);

INSERT INTO User_Skills (user_id, name, level) VALUES
(11, 'Java', 'BEGINNER'),
(11, 'SQL', 'INTERMEDIATE'),
(11, 'Python', 'BEGINNER');

SELECT users.id, users.first_name, users.last_name, user_skills.name, user_skills.level
FROM users 
LEFT JOIN user_skills ON users.id = user_skills.user_id
WHERE users.id = 11;  

SELECT DISTINCT users.id , users.first_name , users.last_name , users.email , users.country ,  users.city 
FROM users 
JOIN user_skills ON users.id = user_skills.user_id 
WHERE user_skills.name = 'Java' AND users.is_mentor = true AND users.is_active = true;

INSERT INTO Mentorship_Matches (mentor_id, mentee_id, topic, status, progress, mentor_feedback, mentee_feedback) VALUES
(1, 11, 'Java', 'NEW', 0, '', '');

SELECT users.id, users.first_name, users.last_name , mentor_availabilities.weekday , mentor_availabilities.start_time , mentor_availabilities.end_time 
FROM users  
RIGHT JOIN mentor_availabilities ON users.id = mentor_availabilities.mentor_id    
WHERE mentor_availabilities.mentor_id = 1;

INSERT INTO mentorship_sessions (mentorship_match_id, status, scheduled_date, mentor_notes) VALUES
(12, 'PENDING', '2025-03-28 12:00:00', '');

UPDATE mentorship_sessions SET status = 'ACCEPTED' , mentor_notes = 'Please study this topic before the session' WHERE mentorship_match_id = 11;

INSERT INTO Session_Feedbacks (session_id, mentee_feedback, rate) VALUES
(11, 'Great session! Learned a lot about Java.', 9);

SELECT mentorship_sessions.id, mentorship_sessions.status, mentorship_sessions.scheduled_date, mentorship_matches.topic, mentee.first_name AS mentee_name , mentor.first_name , session_feedbacks.mentee_feedback , session_feedbacks.rate
FROM session_feedbacks
JOIN mentorship_sessions ON session_feedbacks.session_id = mentorship_sessions.id 
JOIN mentorship_matches ON mentorship_sessions.mentorship_match_id = mentorship_matches.id
JOIN users mentee ON mentorship_matches.mentee_id = mentee.id  
JOIN users mentor ON  mentorship_matches.mentor_id = mentor.id
WHERE session_feedbacks.session_id = 11;

INSERT INTO Posts (title, description, author_id) VALUES
('Understanding Java Streams', 'A deep dive into Java streams and functional programming.', 1);

