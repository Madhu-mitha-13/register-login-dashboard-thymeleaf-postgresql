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
- H2 Database (or any other configured database)

## Prerequisites

- Java 17 or higher installed
- Maven 3.6 or higher installed
- A database configured and running (H2 by default, but can be changed)

## How to Run

1. Clone the repository:
   
   ```bash
   git clone <REPOSITORY_URL>
   cd register-login-dashboard-thymeleaf-postgresql
   ```
   
2. Configure the database properties in
 
 ```src/main/resources/application.properties ou application.yml. ```

3. Run the project using Maven:
  
  ```mvn clean install```
  ```mvn spring-boot:run```

## Important Configurations

1) Spring Security is configured to protect routes and manage sessions.
2) Passwords are encoded with BCryptPasswordEncoder.
3) Database credentials and URL must be set properly in application.properties or application.yml.
4) Thymeleaf templates are used for rendering the frontend views.
5) Session invalidation occurs on email or password change for security.

## Contributions

This is a personal project; external contributions are not planned.

## License

This project is private, and all rights are reserved by the author.

No part of this code may be copied, modified, distributed, or used without the express permission of the author.

© 2025 [Mariana Ramacciotti]. All rights reserved.
