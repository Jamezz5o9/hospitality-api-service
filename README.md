# Hospitality APIService

The Hospitality APIService is designed to manage hospital operations and medical records, making these records accessible to hospital staff via an API. This service handles everything from managing staff records to handling patient profiles.

# Features
1.  Staff Management: Add new staff members with auto-generated UUIDs.
2.  Staff Profile Updates: Secure endpoints to update staff member profiles.
3.  Patient Profile Management: Fetch and manage patient profiles, with specific features for young patients.
4.  Data Export: Export patient profiles to CSV.
5.  Batch Operations: Delete multiple patient profiles within a specified date range.
# Getting Started
  Prerequisites
a.  Java 17
b.  Maven 3.6+ (for building the project)
c.  Any IDE that supports Java (e.g., IntelliJ IDEA, Eclipse)

*  Running the Application Locally
A.  Clone the repository:
  git clone <repository-url>
  cd api-service

B. Build the project using Maven:
  mvn clean install

C.  mvn spring-boot:run

The service will start running on http://localhost:8080.

# API Endpoints
The API service provides several endpoints:

POST /api/staff/add - Add a new staff member.
POST /api/staff/update/{uuid} - Update a staff member's profile.
GET /api/patients/young - Fetch all patients up to 2 years old.
GET /api/patients/{id}/download - Download a patient's profile as a CSV file.
DELETE /api/patients - Delete multiple patient profiles between specified dates.


# Authentication
All sensitive endpoints require a staff member's UUID for authentication. This UUID must be included in the request header.

# Database Configuration
This project uses an H2 in-memory database for simplicity and ease of development. For production, you might consider switching to a more robust database system like MySQL or PostgreSQL.

# Populating the Database
Initial data for the application is populated using an SQL script that is executed when the application starts. You can modify this script at src/main/resources/data.sql to adjust the initial data set.

# Testing
This project includes integration tests for the endpoints. To run the tests, execute.



