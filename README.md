# device-app


The DEVICE-APP is a simple application that allows maintenance of devices.

It is a Spring Boot application that uses Hibernate JPA to store messages in a PostgreSQL database, aided by Flyway

A Makefile allows easy dockerization:
jar:
    mvn package -DskipTests

build:
    docker compose build

up:
    docker compose up

Flyway will create the necessary tables in the database

Springdoc: http://localhost:8080/v3/api-docs