select * from users;
select * from user_skills;
select * from mentor_availability;
select * from mentorship_match;
select * from mentorship_session;
select * from session_feedback;
select * from posts;

-- Retrieve All Mentors and Their Skills
SELECT users.id, users.first_name, users.last_name, user_skills.skill_name, user_skills.skill_level
FROM users 
LEFT JOIN User_Skills ON users.id = user_skills.user_id
WHERE users.is_mentor = TRUE;

-- Find Available Mentors for a Given Weekday
SELECT users.id, users.first_name, users.last_name , mentor_availability.weekday , mentor_availability.start_time , mentor_availability.end_date 
FROM users 
JOIN mentor_availability ON users.id = mentor_availability.mentor_id
WHERE mentor_availability.weekday = "MONDAY";

-- All Active Mentorship Matches
SELECT mentorship_match.mentor_id , mentor.first_name AS mentor_name , mentee.first_name AS mentee_name , mentorship_match.status , mentorship_match.topic 
FROM mentorship_match
JOIN users mentor ON mentorship_match.mentor_id = mentor.id
JOIN users mentee ON mentorship_match.mentee_id = mentee.id
WHERE mentorship_match.status IN ('NEW','IN PROGRESS');

-- Retrieve All Sessions for a Specific Mentor
SELECT mentorship_session.id, mentorship_session.status, mentorship_session.scheduled_date, mentorship_match.topic, mentee.first_name AS mentee_name
FROM mentorship_session 
JOIN mentorship_match ON mentorship_session.mentorship_match_id = mentorship_match.id
JOIN Users mentee ON mentorship_match.mentee_id = mentee.id
WHERE mentorship_match.mentor_id = 1;

-- Show the Top 3 Highest Rated Mentors
SELECT mentor.first_name AS mentor_name , COUNT(session_feedback.id) AS sessions , AVG(session_feedback.rate) AS mentor_rate 
FROM session_feedback
JOIN mentorship_session ON mentorship_session.id = session_feedback.session_id 
JOIN mentorship_match  ON  mentorship_session.mentorship_match_id = mentorship_match.id 
JOIN users mentor ON mentorship_match.mentor_id =  mentor.id 
GROUP BY mentorship_match.mentor_id , mentor.first_name
ORDER BY mentor_rate DESC
LIMIT 3;




