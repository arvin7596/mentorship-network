CREATE TABLE Users (
	id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    phone VARCHAR(50),
    birth_date DATE,
    location VARCHAR(50),
    is_mentor BOOLEAN,
    is_mentee BOOLEAN,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN 
);

CREATE TABLE User_Skills (
	skill_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES Users(id),
	skill_name VARCHAR(50),
    skill_level ENUM('beginner', 'intermediate','advanced')
);


CREATE TABLE Mentor_Availability (
 mentor_id INT,
 FOREIGN KEY (mentor_id) REFERENCES Users(id),
 weekday ENUM('monday', 'tuesday','wednesday' , 'thursday' , 'friday' , 'saturday' , 'sunday'),
 start_time DATETIME,
 end_date DATETIME
);


CREATE TABLE Mentorship_Match(
match_id INT PRIMARY KEY AUTO_INCREMENT,
mentor_id INT,
 FOREIGN KEY (mentor_id) REFERENCES Users(id),
 mentee_id INT,
 FOREIGN KEY (mentee_id) REFERENCES Users(id),
 topic VARCHAR(50),
 status ENUM('new', 'in progress','completed','canceled', 'on hold'),
 progress INT CHECK (progress >= 0 AND progress <= 100),
 mentor_feedback TEXT,
 mentee_feedback TEXT
);

CREATE TABLE Mentorship_Session(
  session_id INT PRIMARY KEY AUTO_INCREMENT,
  mentorship_match_id INT,
  FOREIGN KEY (mentorship_match_id) REFERENCES Mentorship_Network(match_id),
  status ENUM("pending", "accepted", "completed", "canceled"),
  scheduled_date DATETIME,
  mentor_notes TEXT
); 

CREATE TABLE Session_Feedback (
feedback_id INT PRIMARY KEY AUTO_INCREMENT,
session_id INT,
FOREIGN KEY (session_id) REFERENCES Mentorship_Session(session_id),
mentee_feedback TEXT,
rate DECIMAL CHECK (rate >= 0 AND rate <= 10)
);

CREATE TABLE Posts(
post_id INT PRIMARY KEY AUTO_INCREMENT,
title varchar(100),
description text,
author_id INT,
FOREIGN KEY (author_id) REFERENCES Users(id)
)
