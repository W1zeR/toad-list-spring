# toad-list-spring

## Running

1. Create role with name `me` and password `123` in PostgreSQL (or change it in `src/main/resources/application.yml`)
2. Create DB `toadlist-spring` with owner from the previous point
3. Start app
4. Go to `http://localhost:8081/swagger-ui/`

## Architecture

- Spring
- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL
- Liquibase
- Springfox Swagger UI

## Features

- Auth with JWT
- Data validation with annotations
- Logging with SLF4J
