# RegisterApp

User registration and authentication project developed with Spring Boot, Spring Security, Thymeleaf, and BCrypt.

## Table of Contents

- [Description](#description)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [How to Run](#how-to-run)
- [Important Configurations](#important-configurations)
- [Contributions](#contributions)
- [License](#license)

## Description

This project implements a user management system with registration, login, email and password update, and user deletion functionalities. It uses Spring Boot for the backend, Spring Security for authentication and authorization, Thymeleaf for server-side templating, and BCrypt for secure password hashing. The system ensures secure handling of user data and session management.

## Technologies

- Java 17+
- Spring Boot
- Spring Security
- Thymeleaf
- Spring Data JPA
- BCrypt Password Encoder
- Maven

## Prerequisites

- Java 17 or higher installed
- Maven 3.6 or higher installed
- Docker and Docker Compose installed (for containerized execution)
- GitHub account (to use CI/CD pipeline with Secrets configured)

## How to Run

Docker allows you to run the entire application including the database in containers, isolating dependencies and environment setup.

1) Create a .env file in the project root (and add it to .gitignore) with:

```SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/x```
```SPRING_DATASOURCE_USERNAME=postgres ```
```SPRING_DATASOURCE_PASSWORD=yourpassword ```

2) Run Docker Compose to build and start the app and PostgreSQL database:

```docker compose up --build```

3) Access the app at http://localhost:8080/swagger-ui/index.html

4) To stop and clean containers: 

```docker compose down -v```

## Important Configurations

1) Spring Security is configured to protect routes and manage sessions.
2) Passwords are encoded with BCryptPasswordEncoder.
3) Database credentials and URL must be set properly in application.properties or application.yml.
4) Thymeleaf templates are used for rendering the frontend views.
5) Session invalidation occurs on email or password change for security.
6) Dockerfile and Docker Compose files are provided for containerized builds and deployments.
7) GitHub Actions workflow is configured to build and deploy using Docker Compose. 

## Contributions

This is a personal project; external contributions are not planned.

## License

This project is private, and all rights are reserved by the author.

No part of this code may be copied, modified, distributed, or used without the express permission of the author.

© 2025 [Mariana Ramacciotti]. All rights reserved.
