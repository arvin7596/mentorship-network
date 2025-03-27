-- Users table
INSERT INTO Users (first_name, last_name, email, password, phone, country, city, is_mentor, is_mentee) VALUES
('John', 'Doe', 'john.doe@email.com', 'password123', '+1234567890', 'USA', 'New York', true, false),
('Jane', 'Smith', 'jane.smith@email.com', 'password123', '+1234567891', 'UK', 'London', true, false),
('Mike', 'Johnson', 'mike.j@email.com', 'password123', '+1234567892', 'Canada', 'Toronto', false, true),
('Sarah', 'Williams', 'sarah.w@email.com', 'password123', '+1234567893', 'Australia', 'Sydney', true, true),
('David', 'Brown', 'david.b@email.com', 'password123', '+1234567894', 'Germany', 'Berlin', true, false),
('Emma', 'Davis', 'emma.d@email.com', 'password123', '+1234567895', 'France', 'Paris', false, true),
('James', 'Wilson', 'james.w@email.com', 'password123', '+1234567896', 'Spain', 'Madrid', true, false),
('Lisa', 'Anderson', 'lisa.a@email.com', 'password123', '+1234567897', 'Italy', 'Rome', false, true),
('Robert', 'Taylor', 'robert.t@email.com', 'password123', '+1234567898', 'Japan', 'Tokyo', true, false),
('Maria', 'Garcia', 'maria.g@email.com', 'password123', '+1234567899', 'Brazil', 'Sao Paulo', false, true);

-- User_Skills table
INSERT INTO User_Skills (user_id, name, level) VALUES
(1, 'Java', 'ADVANCED'),
(1, 'Python', 'INTERMEDIATE'),
(2, 'JavaScript', 'ADVANCED'),
(2, 'React', 'INTERMEDIATE'),
(3, 'Python', 'BEGINNER'),
(4, 'Java', 'INTERMEDIATE'),
(5, 'JavaScript', 'ADVANCED'),
(6, 'Python', 'BEGINNER'),
(7, 'React', 'INTERMEDIATE'),
(8, 'Java', 'BEGINNER');

-- Mentor_Availabilities table
INSERT INTO Mentor_Availabilities (mentor_id, weekday, start_time, end_time) VALUES
(1, 'MONDAY', '09:00:00', '11:00:00'),
(1, 'WEDNESDAY', '14:00:00', '16:00:00'),
(2, 'TUESDAY', '10:00:00', '12:00:00'),
(2, 'THURSDAY', '15:00:00', '17:00:00'),
(4, 'MONDAY', '13:00:00', '15:00:00'),
(4, 'FRIDAY', '09:00:00', '11:00:00'),
(5, 'WEDNESDAY', '16:00:00', '18:00:00'),
(7, 'THURSDAY', '11:00:00', '13:00:00'),
(9, 'TUESDAY', '14:00:00', '16:00:00'),
(9, 'FRIDAY', '15:00:00', '17:00:00');

-- Mentorship_Matches table
INSERT INTO Mentorship_Matches (mentor_id, mentee_id, topic, status, progress) VALUES
(1, 3, 'Java Programming', 'IN_PROGRESS', 30),
(1, 6, 'Python Basics', 'NEW', 0),
(2, 8, 'JavaScript Development', 'IN_PROGRESS', 50),
(2, 10, 'React Fundamentals', 'COMPLETED', 100),
(4, 3, 'Java Spring Boot', 'IN_PROGRESS', 70),
(5, 6, 'Frontend Development', 'NEW', 0),
(7, 8, 'React Advanced', 'IN_PROGRESS', 40),
(9, 10, 'System Design', 'NEW', 0),
(9, 3, 'Database Design', 'IN_PROGRESS', 60),
(1, 8, 'API Development', 'NEW', 0);

-- Mentorship_Sessions table
INSERT INTO Mentorship_Sessions (mentorship_match_id, status, scheduled_date) VALUES
(1, 'COMPLETED', '2024-03-15 10:00:00'),
(1, 'PENDING', '2024-03-22 10:00:00'),
(2, 'PENDING', '2024-03-20 14:00:00'),
(3, 'ACCEPTED', '2024-03-21 15:00:00'),
(4, 'COMPLETED', '2024-03-18 11:00:00'),
(5, 'PENDING', '2024-03-25 13:00:00'),
(6, 'ACCEPTED', '2024-03-19 16:00:00'),
(7, 'PENDING', '2024-03-23 14:00:00'),
(8, 'ACCEPTED', '2024-03-24 10:00:00'),
(9, 'PENDING', '2024-03-26 15:00:00');

-- Session_Feedbacks table
INSERT INTO Session_Feedbacks (session_id, mentee_feedback, rate) VALUES
(1, 'Great session, learned a lot!', 9),
(2, 'Very helpful mentor, clear explanations', 9),
(3, 'Good introduction to the topic', 8),
(4, 'Excellent practical examples', 9),
(5, 'Very knowledgeable mentor', 9),
(6, 'Helped me understand complex concepts', 8),
(7, 'Great practical exercises', 9),
(8, 'Very patient and thorough explanation', 9),
(9, 'Excellent session overall', 9),
(10, 'Very informative and helpful', 8);

-- Posts table
INSERT INTO Posts (title, description, author_id) VALUES
('Getting Started with Java', 'Tips for beginners in Java programming', 1),
('Advanced React Patterns', 'Deep dive into React design patterns', 2),
('Python Best Practices', 'Writing clean and efficient Python code', 1),
('JavaScript Tips and Tricks', 'Useful JavaScript programming techniques', 2),
('Learning Path: Full Stack', 'Guide to becoming a full stack developer', 4),
('Database Design Principles', 'Fundamentals of good database design', 5),
('API Development Guide', 'Best practices in API development', 7),
('Frontend Development Tips', 'Important concepts in frontend development', 9),
('Coding Interview Prep', 'Preparing for technical interviews', 4),
('System Design Basics', 'Introduction to system design concepts', 5);