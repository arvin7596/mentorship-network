-- Insert Users (Mentors & Mentees)
INSERT INTO Users (first_name, last_name, email, password, phone, birth_date, country , city , is_mentor, is_mentee, is_active) VALUES
('Alice', 'Johnson', 'alice.johnson@email.com', 'pass123', '+491234567890', '1990-05-15', 'Germany', 'Berlin', TRUE, FALSE, TRUE),
('Bob', 'Smith', 'bob.smith@email.com', 'pass456', '+491234567891', '1995-08-22', 'Germany', 'Munich', FALSE, TRUE, TRUE),
('Charlie', 'Brown', 'charlie.brown@email.com', 'pass789', '+491234567892', '1988-11-10', 'Germany', 'Hamburg', TRUE, FALSE, TRUE),
('Diana', 'Miller', 'diana.miller@email.com', 'pass111', '+491234567893', '1992-07-30', 'Germany', 'Frankfurt', TRUE, FALSE, TRUE),
('Eve', 'Davis', 'eve.davis@email.com', 'pass222', '+491234567894', '1998-02-17', 'Germany', 'Stuttgart', FALSE, TRUE, TRUE);

-- Insert User Skills
INSERT INTO User_Skills (user_id, skill_name, skill_level) VALUES
(1, 'Java', 'advanced'),
(1, 'SQL', 'intermediate'),
(2, 'Python', 'advanced'),
(2, 'Machine Learning', 'beginner'),
(3, 'JavaScript', 'intermediate'),
(4, 'Cloud Computing', 'advanced'),
(5, 'Data Science', 'intermediate');

-- Insert Mentor Availability
INSERT INTO Mentor_Availability (mentor_id, weekday, start_time, end_date) VALUES
(1, 'monday', '2025-03-10 09:00:00', '2025-03-10 12:00:00'),
(3, 'wednesday', '2025-03-12 14:00:00', '2025-03-12 17:00:00'),
(4, 'friday', '2025-03-14 10:00:00', '2025-03-14 13:00:00');

-- Insert Mentorship Matches
INSERT INTO Mentorship_Match (mentor_id, mentee_id, topic, status, progress, mentor_feedback, mentee_feedback) VALUES
(1, 2, 'Java Programming', 'in progress', 40, 'Good understanding so far.', 'Learning a lot.'),
(3, 5, 'Web Development', 'new', 0, NULL, NULL),
(4, 2, 'Cloud Computing', 'completed', 100, 'Excellent progress!', 'Gained valuable knowledge.');

-- Insert Mentorship Sessions
INSERT INTO Mentorship_Session (mentorship_match_id, status, scheduled_date, mentor_notes) VALUES
(1, 'pending', '2025-03-15 10:00:00', 'Focus on Java OOP principles.'),
(2, 'accepted', '2025-03-16 14:00:00', 'Prepare for JavaScript ES6.'),
(3, 'completed', '2025-03-10 16:00:00', 'Covered AWS services.');

-- Insert Session Feedback
INSERT INTO Session_Feedback (session_id, mentee_feedback, rate) VALUES
(1, 'Great session! Learned a lot about Java.', 9),
(2, 'Excited to explore more about JavaScript.', 8),
(3, 'Cloud computing concepts were well explained.', 10);

-- Insert Posts
INSERT INTO Posts (title, description, author_id) VALUES
('Understanding Java Streams', 'A deep dive into Java streams and functional programming.', 1),
('Machine Learning for Beginners', 'Step-by-step guide to getting started with ML.', 2),
('Web Development Best Practices', 'Tips to improve web performance and accessibility.', 3),
('Cloud Security Basics', 'How to secure your cloud applications effectively.', 4);
