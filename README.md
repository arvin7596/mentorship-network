# Mentorship Network API

A Spring Boot REST API for a mentorship network system.

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

## Testing with Postman

1. Download and install [Postman](https://www.postman.com/downloads/)
2. Import the Postman collection:
   - Open Postman
   - Click "Import" button
   - Select `backend/postman/mentorship-network.postman_collection.json`
   - Click "Import"

3. The collection includes all API endpoints:
   - Users (CRUD operations)
   - Mentors
   - Mentees

4. Example requests are pre-configured with sample data

## API Endpoints

### Users
- GET `/api/users` - Get all users
- GET `/api/users/{id}` - Get user by ID
- POST `/api/users` - Create new user
- PUT `/api/users/{id}` - Update user
- DELETE `/api/users/{id}` - Delete user

### Mentors
- GET `/api/users/mentors` - Get all mentors

### Mentees
- GET `/api/users/mentees` - Get all mentees

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
└── README.md
```
