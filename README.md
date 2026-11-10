# java-bloat

This repository contains both lab exam projects:

- `lab1exam/`: the original Lab Exam 1 folder, including its `assign5/` backend and local changes.
- `lab2exam/`: the cleaned Assignment 8 / Lab Exam 2 backend starter.

## Run Lab Exam 2

With Java 25 installed, run from the repository root:

```sh
./dev.sh
```

You can also run `./dev.sh` from inside `lab2exam/`.
This starts only the Spring Boot API at `http://localhost:8080`.
No frontend or new Assignment 8 features have been implemented.

## Run Lab Exam 1

The original VideoGame backend is inside `lab1exam/assign5/`:

```sh
cd lab1exam/assign5
./gradlew bootRun
```

Both backends default to port 8080, so run one at a time.
