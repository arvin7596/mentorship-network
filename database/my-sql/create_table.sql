CREATE TABLE Users (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(64) NOT NULL,
    phone VARCHAR(20),
    birth_date DATE,
    country VARCHAR(100),
    city VARCHAR(100),
    is_mentor BOOLEAN,
    is_mentee BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT true
);

CREATE TABLE User_Skills (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES Users(id),
	skill_name VARCHAR(50),
    skill_level ENUM('BEGINNER', 'INTERMEDIATE','ADVANCED')
);


CREATE TABLE Mentor_Availability (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 mentor_id BIGINT,
 FOREIGN KEY (mentor_id) REFERENCES Users(id),
 weekday ENUM('MONDAY', 'TUESDAY','WEDNESDAY' , 'THURSDAY' , 'FRIDAY' , 'SATURDAY' , 'SUNDAY'),
 start_time DATETIME,
 end_date DATETIME
);


CREATE TABLE Mentorship_Match(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
mentor_id BIGINT,
 FOREIGN KEY (mentor_id) REFERENCES Users(id),
 mentee_id BIGINT,
 FOREIGN KEY (mentee_id) REFERENCES Users(id),
 topic VARCHAR(50),
 status ENUM('NEW', 'IN PROGRESS','COMPLETED','CANCELED', 'ON HOLD') DEFAULT 'NEW',
 progress INT CHECK (progress >= 0 AND progress <= 100) DEFAULT 0,
 mentor_feedback TEXT,
 mentee_feedback TEXT
);

CREATE TABLE Mentorship_Session(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  mentorship_match_id BIGINT,
  FOREIGN KEY (mentorship_match_id) REFERENCES Mentorship_Match(id),
  status ENUM("PENDING", "ACCEPTED", "COMPLETED", "CANCELED") DEFAULT 'PENDING',
  scheduled_date DATETIME,
  mentor_notes TEXT
); 

CREATE TABLE Session_Feedback (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
session_id BIGINT,
FOREIGN KEY (session_id) REFERENCES Mentorship_Session(id),
mentee_feedback TEXT,
rate DECIMAL CHECK (rate >= 0 AND rate <= 10)
);

CREATE TABLE Posts(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
title varchar(100),
description text,
author_id BIGINT,
FOREIGN KEY (author_id) REFERENCES Users(id)
)
