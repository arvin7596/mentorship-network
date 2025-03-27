# Mentorship Network API

A Spring Boot REST API for a mentorship network system that facilitates connections between mentors and mentees. Built using Java and Spring Boot following the MVC (Model-View-Controller) architecture pattern. The application uses MySQL for storing user profiles, skills, mentorship sessions, and other relational data, while MongoDB handles additional data requirements. This system enables users to manage mentorship relationships, schedule sessions, track progress, and provide feedback.

Key Technologies:
- Java 21
- Spring Boot
- MySQL 8.0+
- MongoDB
- Maven 3.6+
- MVC Architecture

--- 

## Prerequisites

- Java 21
- MySQL 8.0+
- Maven 3.6+

## Setup Instructions

1. Clone the repository:
```bash
git clone [your-repository-url]
cd mentorship-network
```

2. Create MySQL database:
```sql
CREATE DATABASE mentorship_network;
```

3. Configure database connection:
Create `application.properties` in `backend/src/main/resources/`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mentorship_network
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

4. Build and run:
```bash
cd backend
mvn spring-boot:run
```

---

## Testing with Postman

1. Download and install [Postman](https://www.postman.com/downloads/)
2. Import the Postman collection:
   - Open Postman
   - Click "Import" button
   - Select [`backend/postman/mentorship-network.postman_collection.json`](https://github.com/arvin7596/mentorship-network/blob/main/backend/postman/mentorship-network.postman_collection.json)
   - Click "Import"

3. The collection includes all API endpoints:
   - Users 
   - Skills
   - Mentor Availabilities
   - Mentorship Matches
   - Mentorship Sessions
   - Mentorship Feedbacks
   - Posts

4. Example requests are pre-configured with sample data

## API Endpoints

### Users
- GET `/api/users` - Get all users
- GET `/api/users/{id}` - Get user by ID
- GET `/api/users/mentors` - Get all mentors
- GET `/api/users/mentees` - Get all mentees
- GET `/api/users/{userId}/skills` - Get user's skills
- POST `/api/users` - Create new user
- PUT `/api/users/{id}` - Update user profile
- DELETE `/api/users/{id}` - Delete user

### Skills
- GET `/api/skills/{userId}` - Get user's skills
- GET `/api/skills/suggestions/{userId}` - Get suggested mentors for user's skills
- POST `/api/skills` - Create new skill
- PUT `/api/skills/{id}` - Update skill
- DELETE `/api/skills/{id}` - Delete skill

### Mentor Availabilities
- GET `/api/availabilities` - Get all availabilities
- GET `/api/availabilities/mentor/{mentorId}` - Get mentor's availabilities
- GET `/api/availabilities/weekday/{weekday}` - Get availabilities by weekday
- GET `/api/availabilities/{mentorId}/time-slots` - Get mentor availabilities time slots
- POST `/api/availabilities` - Create mentor availability
- PUT `/api/availabilities/{id}` - Update availability
- DELETE `/api/mentor-availabilities/{id}` - Delete availability

### Mentorship Matches
- GET `/api/mentorship-matches` - Get all matches
- GET `/api/mentorship-matches/{id}` - Get match by ID
- GET `/api/mentorship-matches/mentor/{id}` - Get matches by mentor
- POST `/api/mentorship-matches` - Create new match
- PUT `/api/mentorship-matches/{id}` - Update match
- DELETE `/api/mentorship-matches/{id}` - Delete match

### Mentorship Sessions
- GET `/api/sessions` - Get all sessions
- GET `/api/sessions/{id}` - Get session by ID
- POST `/api/sessions` - Create new session
- PUT `/api/sessions/{id}` - Update session
- PUT `/api/sessions/{id}/status` - Update session status
- DELETE `/api/sessions/{id}` - Delete session

### Session Feedbacks
- GET `/api/session-feedbacks` - Get all feedbacks
- GET `/api/session-feedbacks/{id}` - Get feedback by ID
- POST `/api/session-feedbacks` - Create new feedback
- PUT `/api/session-feedbacks/{id}` - Update feedback
- DELETE `/api/session-feedbacks/{id}` - Delete feedback

### Posts
- GET `/api/posts` - Get all posts
- GET `/api/posts/{id}` - Get post by ID
- POST `/api/posts` - Create new post
- PUT `/api/posts/{id}` - Update post
- DELETE `/api/posts/{id}` - Delete post

## Project Structure
```
mentorship-network/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/gisma/mentorship_network/
│   │   │   │       ├── controller/
│   │   │   │       ├── service/
│   │   │   │       ├── repository/
│   │   │   │       └── model/
│   │   │   └── resources/
│   │   └── test/
│   ├── postman/
│   │   └── mentorship-network.postman_collection.json
│   └── pom.xml
├── database/
│   ├── mongodb/
│   ├── my-sql/
└── README.md
```
