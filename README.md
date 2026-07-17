# Task Manager API

![CI](https://github.com/simaofpereira/task-manager-api/actions/workflows/ci.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen)

A REST API for managing personal tasks, with JWT-based authentication so each user can only access their own data. 

**Live demo:** [task-manager-api-production-edb6.up.railway.app](https://task-manager-api-production-edb6.up.railway.app)

**API docs (Swagger):** [task-manager-api-production-edb6.up.railway.app/swagger-ui.html](https://task-manager-api-production-edb6.up.railway.app/swagger-ui.html)


## Features

- User registration and login with JWT authentication
- Full CRUD operations for tasks
- Per-user data isolation — users can only access their own tasks
- PostgreSQL database with automatic schema management via Hibernate
- Interactive API documentation with Swagger UI
- Containerized with Docker and Docker Compose
- Automated tests (unit tests with JUnit and Mockito)
- CI pipeline via GitHub Actions
- Deployed on Railway

## Tech Stack

| Category | Technologies |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot, Spring Security, Spring Data JPA |
| Database | PostgreSQL |
| API Docs | Swagger / OpenAPI |
| Testing | JUnit 5, Mockito |
| DevOps | Docker, Docker Compose, GitHub Actions |
| Deployment | Railway |

## Running Locally

**Requirements:** Docker and Docker Compose

```bash
git clone https://github.com/simaofpereira/task-manager-api.git
cd task-manager-api
docker compose up --build
```

The API will be available at `http://localhost:8080`, and the Swagger docs at `http://localhost:8080/swagger-ui.html`.

## API Reference

Base URL (local): `http://localhost:8080`
Base URL (live): `https://task-manager-api-production-edb6.up.railway.app`

The easiest way to explore and test every endpoint is through the [Swagger UI](https://task-manager-api-production-edb6.up.railway.app/swagger-ui.html). A summary is also provided below.
