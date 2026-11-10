# java-bloat

This repository contains both lab exam projects:

- `lab1exam/`: the original Lab Exam 1 folder, including its `assign5/` backend and local changes.
- `lab2exam/`: the cleaned Assignment 8 / Lab Exam 2 backend starter.
- `exam_two_frontend/`: the Next.js TypeScript Pages Router starter.

## Run Lab Exam 2

With Java 25 and Node.js installed, install frontend dependencies once with `npm --prefix exam_two_frontend ci`, then run from the repository root:

```sh
./dev.sh
```

This starts Next.js at `http://localhost:3000` and the Spring Boot API at `http://localhost:8080`.
Press Ctrl+C to stop both.
The frontend is a starter page with no backend connection or Assignment 8 features.
Edit `exam_two_frontend/pages/index.tsx` to begin frontend work.
Run `./dev.sh` from inside `lab2exam/` for the backend alone, or `npm run dev` inside `exam_two_frontend/` for the frontend alone.

## Run Lab Exam 1

The original VideoGame backend is inside `lab1exam/assign5/`:

```sh
cd lab1exam/assign5
./gradlew bootRun
```

Both backends default to port 8080, so run one at a time.
