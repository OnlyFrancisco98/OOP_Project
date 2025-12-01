# API — Appointment Requests Module

Documentation of the endpoints exposed by the `modulosolicitudes` module.

Base URL (default for this project): `http://localhost:8081/api`

## Endpoints

### GET /horarios/disponibles
- **Description:** Returns a list of available schedules (future ones with `estaDisponible=true`).
- **Response:** `200 OK` with `List<HorarioResponse>`.

**HorarioResponse (JSON):**
```json
{
  "horarioId": 123,
  "fechaHoraInicio": "2025-12-05T09:00:00+00:00",
  "duracionMinutos": 50,
  "estaDisponible": true
}
```

----

### POST /citas
- **Description:** Creates a new appointment (booking). Validates schedule and patient.
- **Request:** `AgendarCitaRequest` (JSON)

**Example:**
```json
{
  "nombre": "María Pérez",
  "email": "maria@example.com",
  "telefono": "9981234567",
  "fechaNacimiento": "1990-05-20",
  "horarioId": 123,
  "motivoConsulta": "Psicoterapia"
}
```

- **Response:**
  - `201 Created` with the `Cita` entity (mapped by the service) on success.
  - `409 Conflict` with a message if the schedule is not available or a conflict occurs.

**Note:** The service attempts to send a confirmation email but does *not* abort the operation if email sending fails.

----

### POST /citas/buscar
- **Description:** Search appointments by `email` and `nombre`.
- **Request:** `BuscarCitaRequest`:
```json
{ "email": "maria@example.com", "nombre": "María Pérez" }
```
- **Response:** `200 OK` with `List<CitaResponse>`.

----

### POST /solicitudes/reprogramar
- **Description:** Create a rescheduling request for an existing appointment.
- **Request:** `ReprogramacionRequest`:
```json
{ "citaId": 1, "motivo": "Cambio de horario", "explicacion": "Motivo..." }
```
- **Response:** `201 Created` with the `Reprogramacion` entity.

----

### POST /solicitudes/aprobar
- **Description:** Approve a rescheduling request and assign a new schedule.
- **Request:** `AprobarReprogramacionRequest`:
```json
{ "solicitudId": 10, "nuevoHorarioId": 200 }
```
- **Response:**
  - `200 OK` with the updated `Reprogramacion` entity.
  - `409 Conflict` if the new schedule cannot be reserved.

----

### POST /test/email
- **Description:** Test endpoint to send an email with example data. Does not persist anything.
- **Request:** `TestEmailRequest`:
```json
{ "nombre": "Test", "email": "test@example.com", "fecha": "2025-12-05T09:00:00+00:00", "motivo": "Confirmation" }
```
- **Response:** `200 OK` or `400 Bad Request` if `email` is missing.

----

## Notes on Formats
- Dates in frontend DTOs: `fechaHoraInicio` is an ISO string with offset (`OffsetDateTime.toString()` in Java).
- `fechaNacimiento` in `AgendarCitaRequest` uses `YYYY-MM-DD` (LocalDate).

**End of API summary.**
