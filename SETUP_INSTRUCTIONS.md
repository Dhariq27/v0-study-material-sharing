# Study Material Sharing System - Setup Guide

## Prerequisites
- Java 17 or higher
- Node.js 18+ and npm/pnpm
- Oracle Database (or H2 for testing)
- Maven 3.8+

## Backend Setup (Spring Boot)

### 1. Configure Database
Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:ORCL
spring.datasource.username=study_user
spring.datasource.password=study_password
