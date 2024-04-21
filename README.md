# Hospitality APIService

The Hospitality APIService is designed to manage hospital operations and medical records, making these records accessible to hospital staff via an API. This service handles everything from managing staff records to handling patient profiles.

## Features

1. **Staff Management**: Add new staff members with auto-generated UUIDs for every staff member required for subsequent API requests.
2. **Staff Profile Updates**: Secure endpoint to update staff member profiles. Requires validating staff uuid before access.
3. **Patient Profile Management**: Fetch all patient profiles where their age is up to 2 years old. Requires validating staff uuid before access.
4. **Data Export**: Export a specific patient’s profile into a CSV file. Requires validating staff uuid before access.
5. **Batch Operations**: Delete multiple patient profiles between a specified date range. Requires validating staff uuid before access.

## Getting Started

### Prerequisites

- Java 17
- Maven 3.6+ (for building the project)
- Any IDE that supports Java (e.g., IntelliJ IDEA, Eclipse)

### Running the Application Locally

```bash
git clone <repository-url>
cd api-service
mvn clean install
mvn spring-boot:run

## Service Startup

The service will start running on `http://localhost:8080`.

## API Endpoints

The API service provides several endpoints:

- **POST /api/staff/add** - Add a new staff member.
- **POST /api/staff/update/{uuid}** - Update a staff member's profile.
- **GET /api/patients/young** - Fetch all patients up to 2 years old.
- **GET /api/patients/{id}/download** - Download a patient's profile as a CSV file.
- **DELETE /api/patients** - Delete multiple patient profiles between specified dates.

## Authentication

All sensitive endpoints require a staff member's UUID for authentication. This UUID must be included in the request header.

## Database Configuration

This project uses an H2 in-memory database for simplicity and ease of development. For production environments, you might consider switching to a more robust database system like MySQL or PostgreSQL.

## Populating the Database

Initial data for the application is populated using an SQL script that is executed when the application starts. You can modify this script at `src/main/resources/data.sql` to adjust the initial data set.

## Testing

This project includes integration tests for the endpoints. To run the tests, execute:

```bash
mvn test
