# chat-app


The DEVICE-APP is a simple application that allows maintenance of devices.

It is a Spring Boot application that uses Hibernate JPA to store messages in a PostgreSQL database, aided by Flyway

jar:
    ./mvnw package -DskipTests

build:
    docker compose build

up:
    docker compose up

Flyway will create the necessary tables in the database

Swagger: http://localhost:8080/swagger-ui/index.html