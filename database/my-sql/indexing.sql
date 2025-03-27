-- Index for optimizing feedback retrieval by user_id
CREATE INDEX idx_user_id ON feedback(user_id);

-- Index for optimizing feedback retrieval by post_id
CREATE INDEX idx_post_id ON feedback(post_id);

-- Index for optimizing search by session_id in Mentorship_Session table
CREATE INDEX idx_session_id ON Mentorship_Session(session_id);

-- Index for optimizing searches by mentor_id and mentee_id in Mentorship_Match table
CREATE INDEX idx_mentor_mentee ON Mentorship_Match(mentor_id, mentee_id);

-- Index for optimizing search by user_id in the Posts table
CREATE INDEX idx_user_id_posts ON Posts(user_id);