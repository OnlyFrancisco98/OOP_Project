# Setup and Execution — `modulosolicitudes` Module

Instructions for running the service locally.

## Requirements
- Java 17+ (or the version configured in `pom.xml`).
- Maven installed (`mvn`).
- An accessible PostgreSQL database (the project includes example configuration in `application.properties`).

## Main Configuration
Check `src/main/resources/application.properties` for the port and database connection. In this project, the default values are:

```
server.port=8081
spring.datasource.url=jdbc:postgresql://.../postgres
spring.datasource.username=...
spring.datasource.password=...
```

Optional: configure email settings in `src/main/resources/application-mail.properties` if you want to send real emails.

## Build and Run
From the `modulosolicitudes` folder, run:

```powershell
mvn clean package -DskipTests
# or for development
mvn spring-boot:run
```

Or run the JAR directly:

```powershell
java -jar target/modulosolicitudes-*.jar
```

The service will start at `http://localhost:8081` (according to `server.port`).

## Testing Endpoints
- Use `curl`, Postman, or the frontend located at `src/main/resources/view/solicitud.html`.
- Schedules endpoint: `GET http://localhost:8081/api/horarios/disponibles`.

## Deployment Notes
- The property `spring.jpa.hibernate.ddl-auto=update` is enabled to simplify development; for production consider setting it to `validate` or `none` and using migrations (Flyway/Liquibase).
- Make sure not to upload real credentials to the repository (the current repo contains example values for academic testing). Replace them with environment variables in real deployments.
