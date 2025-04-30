# Books API

This is a simple Books API built using Spring Boot 3 and Java 17. The API provides endpoints for managing books, including creating, reading, updating, and deleting book records.

## Features

- CRUD operations for books
- In-memory H2 database for quick setup and testing
- Spring Boot for easy configuration and deployment

## Prerequisites

- Java 17
- Maven

## Setup Instructions

1. Clone the repository:
   ```
   git clone <repository-url>
   cd books-api
   ```

2. Build the project:
   ```
   mvn clean install
   ```

3. Run the application:
   ```
   mvn spring-boot:run
   ```

4. Access the API at `http://localhost:8080`.

## Usage

- **Get all books**: `GET /api/books`
- **Get a book by ID**: `GET /api/books/{id}`
- **Create a new book**: `POST /api/books`
- **Update a book**: `PUT /api/books/{id}`
- **Delete a book**: `DELETE /api/books/{id}`

## Database Initialization

The application initializes the database with sample data using the `data.sql` file located in the `src/main/resources` directory.

## License

This project is licensed under the MIT License.