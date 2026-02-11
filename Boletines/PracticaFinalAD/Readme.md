# PracticaFinalAD - Final Practice for AD

This repository contains a collection of Spring Boot applications developed for the "Acceso a Datos" (Data Access) course. These applications demonstrate interaction with PostgreSQL and MongoDB databases, expose RESTful APIs, and include a console-based data loader.

## Project Structure

The repository is organized into three main Spring Boot projects:

1.  **Executor4000**: A console application designed for one-time execution, likely for initial data loading or setup into a PostgreSQL database.
2.  **JoJosGres**: A Spring Boot web application exposing RESTful APIs for managing "Personaxes" (characters) and "Series" data, interacting with a PostgreSQL database.
3.  **MongoChamador**: A Spring Boot web application exposing RESTful APIs for managing "Personaxes" and "Series" data, interacting with a MongoDB database. It also includes functionality to load data from JSON files.

## Individual Projects

### 1. Executor4000

*   **Description**: This is a console-based Spring Boot application. It's configured to perform a sequence of operations and then terminate. Based on the file structure, it likely interacts with a PostgreSQL database to manage `Personaxe` and `Series` entities, possibly for an initial data population or specific batch processing.
*   **Database**: PostgreSQL
*   **Port**: Not applicable for a console application, but shares database configuration with JoJosGres.
*   **How to Run**:
    1.  Ensure you have a PostgreSQL database running and accessible with the credentials specified in `src/main/resources/application.properties`.
    2.  Navigate to the `Executor4000` directory.
    3.  Build the project using Maven: `mvn clean install`
    4.  Run the application: `mvn spring-boot:run` (or execute the generated JAR file). The application will perform its tasks and then exit.

### 2. JoJosGres

*   **Description**: A Spring Boot web application that provides RESTful APIs. It manages `Personaxe` and `Series` data, persisting it in a PostgreSQL database. It includes OpenAPI documentation for its endpoints.
*   **Database**: PostgreSQL
*   **Port**: `8081`
*   **API Documentation**: Accessible via Swagger UI at `http://localhost:8081/swagger-ui.html` (after the application is running).
*   **How to Run**:
    1.  Ensure you have a PostgreSQL database running and accessible with the credentials specified in `src/main/resources/application.properties`.
    2.  Navigate to the `JoJosGres` directory.
    3.  Build the project using Maven: `mvn clean install`
    4.  Run the application: `mvn spring-boot:run`
    5.  Access the API documentation at `http://localhost:8081/swagger-ui.html`.

### 3. MongoChamador

*   **Description**: A Spring Boot web application that exposes RESTful APIs for managing `Personaxe` and `Series` data, storing it in a MongoDB database. It also contains JSON files (`Sanrio.json`, `Series.json`) which might be used for initial data loading or as data sources. It includes an additional `apoiMoral` controller. This project also includes OpenAPI documentation.
*   **Database**: MongoDB
*   **Port**: `8095`
*   **API Documentation**: Accessible via Swagger UI at `http://localhost:8095/swagger-ui/index.html` (after the application is running).
*   **How to Run**:
    1.  Ensure you have a MongoDB instance running and accessible with the URI specified in `src/main/resources/application.properties`.
    2.  Navigate to the `MongoChamador` directory.
    3.  Build the project using Maven: `mvn clean install`
    4.  Run the application: `mvn spring-boot:run`
    5.  Access the API documentation at `http://localhost:8095/swagger-ui/index.html`.

## Common Setup

*   **Java Version**: All projects are configured to use Java 17.
*   **Maven**: Projects are built using Apache Maven. Ensure Maven is installed and configured on your system.
*   **Spring Boot**: All projects use Spring Boot 3.4.2.

---
**Note**: The `Readme.md` previously contained a link to an exam document. This has been preserved at the beginning of the file.
