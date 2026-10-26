# Spring Boot Backend Learning Project

## About

This repository is a hands-on learning project focused on building a RESTful API with Java and Spring Boot. 
Its goal is to strengthen backend development fundamentals by implementing features incrementally and understanding the reasoning behind each design decision.

## Technologies

* Java 21
* Spring Boot 4
* Spring Data JPA
* MySQL
* Jakarta Bean Validation
* Maven
* JUnit and Mockito
* Postman

## Features

* RESTful CRUD operations for products
* Layered architecture (Controller, Service, Repository)
* Data Transfer Objects (DTOs) and entity mapping
* Request Validation
* Centralized exception handling
* MySQL Persistence with Spring Data JPA
* Unit testing with JUnit and Mockito

## Concepts Practiced

* REST API design and HTTP status codes
* Dependency injection
* Separation of concerns
* Java Streams and Lambdas
* Exception handling
* Unit testing and mocking
* Externalized application configuration

## Prerequisites
Before running the application, make sure you have:

* JDK 21 Installed
* MySQL installed and running
* An IDE preferably IntelliJ IDEA
* An SQL client such as MySQL Workbench (optional but useful for database setup)

## Database Setup

1. Connect to MySQL using an account with permissions to create databases and users
2. Create the database running the following command: 
CREATE DATABASE spring_jpa_api;

3. Create a dedicated database user. Replace the example password with a strong local password:
CREATE USER 'spring_api_user'@'localhost' IDENTIFIED BY 'CHANGE_ME_TO_A_LOCAL_PASSWORD';

4. Grant access to the project database:
GRANT ALL PRIVILEGES ON spring_jpa_api.* TO 'spring_api_user'@'localhost';

The application uses Hibernate's ddl-auto=update setting to update the schema based on the JPA entities. Database tables are therefore created or updated, provided the user has the required permissions.

## Project Status

Work in progress. New features and concepts will be added as the learning process continues.
