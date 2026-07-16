# Task Manager API

![CI](https://github.com/simaofpereira/task-manager-api/actions/workflows/ci.yml/badge.svg)

A simple REST API for managing personal tasks, built with Spring Boot. Each user can register, log in, and manage their own tasks.

## What it does

- Register / login with JWT authentication
- Create, list, update, and delete tasks
- Each user only sees their own tasks
- Runs in Docker, with a Postgres database
- Has unit tests and a CI pipeline that runs them automatically

## Built with

Java 17 · Spring Boot · Spring Security · Spring Data JPA · PostgreSQL · Docker · JUnit + Mockito · GitHub Actions

## Running it

You just need Docker installed.

```bash
git clone https://github.com/simaofpereira/task-manager-api.git
cd task-manager-api
docker compose up --build
```

The API will be running at `http://localhost:8080`.
