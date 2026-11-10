# Assignment 8 workspace

The root project is `exam_two_backend`, a Spring Boot backend using Java 25 and an in-memory H2 database.
The reusable VideoGame entity, repository, CSV dataset, and existing initialize, search, and top-games endpoints are retained from the previous project.
Their behavior has not been updated for Assignment 8.

## Run

Install Java 25, then run:

```sh
./dev.sh
```

The backend runs at `http://localhost:8080`.
Stop it with Ctrl+C.
The database starts empty and is cleared on restart.
The existing `GET /api/initialize` endpoint can load the CSV manually.

## Checks

```sh
./gradlew test
```

No frontend or new Assignment 8 features have been implemented.
The frontend and required API changes are left for you to build.
