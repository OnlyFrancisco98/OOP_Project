# MVC Architecture Implementation in Spring Boot

## Project: Psychology Appointment Module

This document outlines how the **Model-View-Controller (MVC)** architectural pattern is applied to the `modulopsicologia` project. In this specific implementation, the project operates as a **RESTful API** (Backend) serving a decoupled **Client-Side View** (Frontend).

---

### 1. The Model (M)

**Role:** The Model represents the data layer and the business rules. It is responsible for managing the state of the application and interacting with the database.

In this project, the Model is divided into two distinct sub-layers: **Entities** (Persistence) and **DTOs** (Data Transfer Objects).

* **Entities (JPA/Hibernate):**
    These classes map directly to the database tables.
    * **`Paciente.java`**: Represents the user receiving therapy. It maps to the `pacientes` table and holds data like `nombre`, `email`, and `contrasenaTemporal`.
    * **`Cita.java`**: Represents the core transaction. It links a `Paciente` with a `Horario` and contains the status (`Agendada`) and the `motivoConsulta`.
    * **`Horario.java`**: Manages time slots. It includes logic fields like `estaDisponible` and `fechaHoraInicio`.
    * **`Reprogramacion.java`**: Handles the logic for changing appointment times, tracking the `estadoSolicitud` and `explicacion`.

* **DTOs (Data Transfer Objects):**
    These classes define the shape of the data sent to and from the API, decoupled from the database structure to prevent infinite recursion and hide sensitive data.
    * **`AgendarCitaRequest.java`**: Captures incoming data from the form (name, email, selected schedule ID).
    * **`CitaResponse.java`**: Formats the outgoing data for the frontend, flattening the structure (e.g., sending `pacienteNombre` instead of the full Patient object).

**Justification:** Separating DTOs from Entities ensures that internal database schemas (like the `contrasenaTemporal` in `Paciente`) are never exposed to the client, while Entities handle the integrity of relational data using annotations like `@OneToMany` and `@ManyToOne`.

---

### 2. The View (V)
**Role:** The View is responsible for the User Interface (UI). It displays data to the user and captures user commands.

In this architecture, the View is **decoupled**. The Spring Boot application does not render HTML on the server (like Thymeleaf or JSP). Instead, it serves static HTML files that consume the API via JavaScript (`fetch`).

* **`solicitud.html`**:
    * **Display:** Renders available time slots dynamically inside `<div class="time-slots">`.
    * **Interaction:** Captures user input via `#appointmentForm` and sends it as JSON to the controller.
    * **Feedback:** Uses a modal (`#confirmationModal`) to show success messages based on the Controller's response.
* **`reprogramar.html`**:
    * **Display:** Fetches appointment details and displays them in the `#appointmentInfo` div.
    * **Interaction:** Allows the user to select a new reason for rescheduling and submit a request via `#reprogramForm`.

**Justification:** Decoupling the View allows the frontend to be developed independently. The use of JavaScript to consume JSON ensures the page doesn't need to reload entirely to update data (e.g., loading time slots via `cargarHorariosDisponibles()`), providing a smoother user experience.

---

### 3. The Controller (C)
**Role:** The Controller acts as the entry point for the application. It handles incoming HTTP requests, invokes the business logic (Service layer), and determines the response sent back to the View.

The controllers in this project are annotated with `@RestController`, meaning they strictly return data (JSON), not HTML views.

* **`CitaController.java`**:
    * **Endpoint:** `POST /api/citas`
    * **Action:** Receives `AgendarCitaRequest`, calls `citaService.agendarCita()`, and returns the created object or an error message.
* **`HorarioController.java`**:
    * **Endpoint:** `GET /api/horarios/disponibles`
    * **Action:** Returns a list of `HorarioResponse` objects for the frontend to render the calendar grid.
* **`ReprogramacionController.java`**:
    * **Endpoint:** `POST /api/solicitudes/reprogramar`
    * **Action:** Handles the logic for requesting a change of date.

**Justification:** The Controller isolates the HTTP transport layer (status codes like 200 OK or 409 CONFLICT) from the business logic. This ensures that if the transport method changes (e.g., to GraphQL or a different API structure), the core logic remains untouched.

---

### 4. The Service Layer (The "Glue")
While not a letter in "MVC", the Service layer is critical in Spring architectures. It sits between the Controller and the Model.

* **Logic Implementation:**
    * **`CitaService.java`**: Contains the logic to create a patient if they don't exist, validate if a schedule is available, save the appointment, and trigger the email.
    * **`EmailService.java`**: Handles the construction of HTML email templates (`buildConfirmacionHtml`) and sends them via `JavaMailSender`.
    * **`HorarioService.java`**: Contains logic to filter schedules that are strictly `estaDisponible=true` and in the future.

**Justification:** Putting logic in Services rather than Controllers makes the code reusable and testable. For example, `ReprogramacionService` reuses `HorarioService` to validate new time slots, preventing code duplication.

---

### 5. Data Flow Example: Booking an Appointment

To understand how these components interact, here is the flow of the "Agendar Cita" process:

1.  **View:** The user fills out the form in `solicitud.html` and clicks "Agendar". The JavaScript constructs a JSON object.
2.  **Controller:** The request hits `CitaController.agendarCita(@RequestBody AgendarCitaRequest request)`.
3.  **Service:** The controller passes the DTO to `CitaService`.
    * The service checks `PacienteRepository` to see if the user exists.
    * It checks `HorarioRepository` to ensure the slot is free.
    * It creates a **Model** entity (`Cita`).
    * It calls `EmailService` to notify the user.
4.  **Repository:** `CitaRepository` saves the new entity to the SQL database.
5.  **Controller:** Receives the saved entity, converts it to a response, and sends a `201 CREATED` status with JSON back to the frontend.
6.  **View:** The JavaScript in `solicitud.html` receives the JSON and displays the Success Modal with the generated temporary password.

---

### Summary of Benefits for this Project

1.  **Separation of Concerns:** The HTML (frontend) cares only about display. The Java (backend) cares only about logic and data.
2.  **Scalability:** You can easily add a Mobile App view later that consumes the exact same `CitaController` endpoints without changing the backend code.
3.  **Maintainability:** Database changes (e.g., adding a column to `pacientes`) only require updates to the **Model** (`Paciente.java`) and potentially the **DTO**, without necessarily breaking the **Controller** logic.

## Visual Resume

```mermaid
graph TD
    User["Usuario / Frontend"] -->|"JSON Limpio"| Controller["Controlador (API)"]
    Controller -->|DTO| Service["Servicio (Lógica)"]
    Service -->|Entidad| Repository["Repositorio (Datos)"]
    Repository -->|SQL| DB[("Base de Datos")]
