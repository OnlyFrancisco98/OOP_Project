# Correspondence with Functional and Non-Functional Requirements

This section explains how the project aligns with the functional requirements (FR) and non-functional requirements (NFR) defined for the second delivery.

## Functional Requirements (FR)

The functional requirements are documented in the file `FunctionalRequirements.md` and describe the expected features of the system. These include user registration, appointment scheduling, success notifications, and confirmation emails. Each requirement is implemented in the source code located in the `Code` directory. For example:

- The user registration functionality is implemented in the `UserRegistration.java` file.
- The appointment request and success notification are handled in `AppointmentSystem.java`.
- The confirmation email is sent using the `sendConfirmationEmail` method.

These implementations demonstrate a clear connection between the documented requirements and the actual system behavior.

## Non-Functional Requirements (NFR)

The non-functional requirements are listed in `NonFunctional.md` and include aspects such as performance, usability, and security. These requirements are addressed through design decisions and code structure:

- The project uses a modular structure with separate packages for models and controllers, supporting maintainability and scalability.
- Input validation and error handling are implemented to improve reliability and security.
- The use of the MVC pattern is evident in the organization of the code, although the view layer is planned for future development.

## Design Artifacts

The class diagram in the `Design` folder shows how the system components relate to each other and to the requirements. Each class corresponds to a specific functionality, making it easier to trace requirements to implementation.

## Verification

The presence of design documents, source code, and execution examples allows for verification of both functional and non-functional requirements. This ensures that the system meets the defined specifications and supports future enhancements.

