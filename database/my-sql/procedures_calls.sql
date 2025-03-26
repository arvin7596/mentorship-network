CALL RegisterUser('Test', 'Test', 'Test@example.com', 'test_password', '1122334455', '1996-06-16', 'Iran' , 'Tehran', TRUE, FALSE, TRUE);
CALL AssignSkill(1, 'Java Programming', 'advanced'); -- (user_id, skill_name, skill_level)
CALL CreateMentorshipMatch(2, 5, 'Data Science'); -- (mentor_id, mentee_id, topic) 
CALL ScheduleMentorshipSession(3, '2025-04-01 14:00:00'); -- (mentorship_match_id, scheduled_date)
CALL UpdateSessionStatus(1, 'completed'); -- (match_id , session_status) 
CALL AddSessionFeedback(1, 'The session was very helpful!', 9.5); -- (session_id, mentee_feedback, rate)

CALL AssignMenteeToMentor(1, 5, 'Java script', '2025-04-01 15:00:00'); 