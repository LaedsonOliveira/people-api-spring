# Person API

Simple REST API built with Spring Boot for managing people.

## Base URL

http://localhost:8080/api/v1/people

## Endpoints

- POST    /api/v1/people        -> Create a person
- GET     /api/v1/people        -> List all people
- GET     /api/v1/people/{id}   -> Find person by ID
- PUT     /api/v1/people/{id}   -> Update person
- DELETE  /api/v1/people/{id}   -> Delete person

## Run the Project

Build the project:

mvn clean install

Run the application:

mvn spring-boot:run

The application will start at:

http://localhost:8080

## Technologies

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Maven  
