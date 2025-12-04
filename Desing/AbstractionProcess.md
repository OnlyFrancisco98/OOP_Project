# The Abstraction Process in the Psychological Appointment System

In the context of this project (Spring Boot + JPA), the process of **abstraction** consists of hiding the complexity of lower layers to expose clean, secure, and easy-to-use interfaces to the upper layers.

Below are the details of how this principle has been applied layer by layer:

---

## 1. Data Level: ORM (Object-Relational Mapping)

**What is abstracted (hidden)?**
Relational SQL tables, foreign keys (FK), complex `JOINs`, and database rows.

**What is exposed?**
Java Objects (`@Entity`) with object-oriented relationships.

* **Before abstraction:** To get a patient's name from an appointment, you would have to think in SQL:
    `SELECT p.nombre FROM citas c JOIN pacientes p ON c.paciente_id = p.id WHERE...`
* **Your Abstraction (`Cita.java`):**
    Hibernate converts that relational complexity into simple object navigation:

    ```java
    cita.getPaciente().getNombre();
    ```

    **Result:** We transform a Relational Database problem into an Object-Oriented Programming model.

---

## 2. Data Access Level: Repositories

**What is abstracted (hidden)?**
Manual writing of SQL queries (`SELECT * FROM...`), JDBC connection handling (`Connection`, `Statement`, `ResultSet`), and SQL error handling.

**What is exposed?**
Simple interfaces with semantic methods (`JpaRepository`).

* **Before abstraction:** You would have to write hundreds of lines of "boilerplate" code to open connections, execute queries, and map results manually.
* **Your Abstraction (`CitaRepository`):**
    You define *what* you want to do, and Spring Data implements *how* to do it:

    ```java
    // You declare the intention, Spring generates the SQL automatically
    List<Cita> buscarPorEmailYNombreDePaciente(String email, String nombre);
    ```

    **Result:** You access the database as if it were a simple in-memory collection, forgetting about the SQL language.

---

## 3. Business Level: Services (Orchestration)

**What is abstracted (hidden)?**
The complexity of business rules, multiple validations, transactions, and coordination between different entities.

**What is exposed?**
A single public method that performs an atomic operation.

* **The complex detail:** To properly schedule an appointment, the system must:

    1. Validate if the timeslot exists.
    2. Check if the timeslot is free.
    3. Find the patient or create them if they don't exist.
    4. Create the appointment.
    5. Mark the timeslot as taken.
    6. Save everything or *rollback* if something fails.
* **Your Abstraction (`CitaService`):**
    The Controller knows nothing about this logic. It only sees and uses a clean method:

    ```java
    citaService.agendarCita(request);
    ```

    **Result:** Total encapsulation of logic. If business rules change, the rest of the system (Controllers/Frontend) remains unaffected.

---

## 4. API / View Level: DTOs (Data Transfer Objects)

**What is abstracted (hidden)?**
The internal database structure, Hibernate's "Lazy Loading" proxies, interceptors (`ByteBuddyInterceptor`), and infinite recursion loops from circular references.

**What is exposed?**
A clean, flat, and secure JSON contract (`Request` and `Response`).

* **The problem (Without abstraction):** Exposing the `Cita` Entity directly to the outside caused `500 Internal Server Error` issues because the JSON tool (Jackson) could not serialize the complex Hibernate proxies.
* **Your Abstraction (`CitaResponse`):**
    We created an intermediate class that acts as the "Menu" for the client, separated from the "Inventory" (Entity).

    ```java
    public CitaResponse convertirACitaResponse(Cita cita) {
        // Translates from the complex Entity to the simple DTO
        // Removes sensitive data and resolves proxies
        return dto;
    }
    ```

    **Result:** Total decoupling between the Database and the User Interface (Frontend). This allows changing the internal DB structure without breaking the web application, as long as the DTO remains the same.