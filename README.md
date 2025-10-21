# Study Material Sharing System

A Spring Boot application for sharing study materials with file upload support, event management, and user authentication.

## Features

- User authentication (Student, Faculty, Admin roles)
- Material upload and download (PDF, DOC, PPT)
- Event management and registration
- Announcements system
- Dashboard with recent materials and events
- Role-based access control

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Oracle Database 11g or higher
- 50MB free disk space for file uploads

## Setup Instructions

### 1. Database Setup

1. Connect to your Oracle database as an admin user
2. Run the SQL script to create tables and sample data:
   \`\`\`sql
   @db/schema_oracle.sql
   \`\`\`

### 2. Configure Database Connection

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:ORCL
spring.datasource.username=study_user
spring.datasource.password=study_password
file.upload.dir=/uploads/
