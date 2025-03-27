select * from users;
select * from user_skills;
select * from mentor_availabilities;
select * from mentorship_matches;
select * from mentorship_sessions;
select * from session_feedbacks;
select * from posts;

-- Retrieve All Mentors and Their Skills
SELECT users.id, users.first_name, users.last_name, user_skills.name, user_skills.level
FROM users 
LEFT JOIN user_skills ON users.id = user_skills.user_id
WHERE users.is_mentor = TRUE;   

-- Find Available Mentors for a Given Weekday
SELECT users.id, users.first_name, users.last_name , mentor_availabilities.weekday , mentor_availabilities.start_time , mentor_availabilities.end_time 
FROM users  
JOIN mentor_availabilities ON users.id = mentor_availabilities.mentor_id    
WHERE mentor_availabilities.weekday = "MONDAY";

-- All Active Mentorship Matches
SELECT mentorship_matches.mentor_id , mentor.first_name AS mentor_name , mentee.first_name AS mentee_name , mentorship_matches.status , mentorship_matches.topic 
FROM mentorship_matches 
JOIN users mentor ON mentorship_matches.mentor_id = mentor.id
JOIN users mentee ON mentorship_matches.mentee_id = mentee.id
WHERE mentorship_matches.status IN ('NEW','IN_PROGRESS');   

-- Retrieve All Sessions for a Specific Mentor
SELECT mentorship_sessions.id, mentorship_sessions.status, mentorship_sessions.scheduled_date, mentorship_matches.topic, mentee.first_name AS mentee_name
FROM mentorship_sessions 
JOIN mentorship_matches ON mentorship_sessions.mentorship_match_id = mentorship_matches.id
JOIN users mentee ON mentorship_matches.mentee_id = mentee.id   
WHERE mentorship_matches.mentor_id = 1;

-- Show the Top 3 Highest Rated Mentors
SELECT mentor.first_name AS mentor_name , COUNT(session_feedbacks.id) AS sessions , AVG(session_feedbacks.rate) AS mentor_rate 
FROM session_feedbacks
JOIN mentorship_sessions ON mentorship_sessions.id = session_feedbacks.session_id 
JOIN mentorship_matches  ON  mentorship_sessions.mentorship_match_id = mentorship_matches.id 
JOIN users mentor ON mentorship_matches.mentor_id =  mentor.id  
GROUP BY mentorship_matches.mentor_id , mentor.first_name
ORDER BY mentor_rate DESC
LIMIT 3;




