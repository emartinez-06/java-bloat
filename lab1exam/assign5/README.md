# Lab Exam 1

## Requirements

- Java 25
- Gradle wrapper

## Run

```bash
./gradlew bootRun
```

The application runs at `http://localhost:8080`.

## Database

The project uses an in-memory H2 database. No external database setup is required. Data is cleared when the application restarts.

Initialize the database after starting the application:

```text
GET http://localhost:8080/api/initialize
```

## API Examples

```text
POST http://localhost:8080/api/videogame
GET  http://localhost:8080/api/top_games?count=12
```

## Tests

```bash
./gradlew test
```
