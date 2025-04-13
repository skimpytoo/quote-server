# Quote Server

A Spring Boot application for managing and retrieving quotes with support for pagination, sorting, and search functionality.

## Features

- CRUD operations for quotes
- Pagination support
- Sorting capabilities
- Case-insensitive search by author and quote text
- Input validation
- Comprehensive test coverage

## Requirements

- Java 21 or higher
- Maven 3.8 or higher
- Spring Boot 3.2.3
- H2 Database (for testing)

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── at/bergmann/quoteserver/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── entity/
│   │       ├── repository/
│   │       └── service/
│   └── resources/
│       └── application.properties
└── test/
    ├── java/
    │   └── at/bergmann/quoteserver/
    │       ├── config/
    │       ├── controller/
    │       ├── repository/
    │       └── service/
    └── resources/
        └── application-test.properties
```

## Setup and Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd quote-server
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080` by default.

## Running Tests

### Running All Tests
```bash
mvn test
```

### Running Specific Test Classes
```bash
mvn test -Dtest=QuoteRepositoryTest
```

### Running Tests with Coverage Report
```bash
mvn test jacoco:report
```

## Test Configuration

The application uses an H2 in-memory database for testing. Test configuration is handled by:
- `TestConfig.java`: Configures the test environment
- `application-test.properties`: Contains test-specific properties

## API Endpoints

### Quotes
- `GET /api/quotes`: Get all quotes (supports pagination and sorting)
- `GET /api/quotes/{id}`: Get a quote by ID
- `POST /api/quotes`: Create a new quote
- `PUT /api/quotes/{id}`: Update an existing quote
- `DELETE /api/quotes/{id}`: Delete a quote

### Search
- `GET /api/quotes/search?author={author}`: Search quotes by author
- `GET /api/quotes/search?quote={text}`: Search quotes by text

## Validation Rules

- Quote text:
  - Cannot be blank
  - Maximum length: 1000 characters
- Author:
  - Cannot be blank
  - Maximum length: 100 characters

## Dependencies

- Spring Boot 3.4.4
- Spring Data JPA
- H2 Database (test scope)
- Jakarta Validation
- JUnit 5
- Mockito

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 