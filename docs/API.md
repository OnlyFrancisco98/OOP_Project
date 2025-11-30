# API — Módulo de Solicitudes de Cita

Documentación de los endpoints expuestos por el módulo `modulosolicitudes`.

Base URL (por defecto en este proyecto): `http://localhost:8081/api`

## Endpoints

### GET /horarios/disponibles
- Descripción: Devuelve una lista de horarios disponibles (futuros y con `estaDisponible=true`).
- Respuesta: `200 OK` con `List<HorarioResponse>`.

HorarioResponse (JSON):
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
- Descripción: Crea una nueva cita (agendar). Valida horario y paciente.
- Request: `AgendarCitaRequest` (JSON)

Ejemplo:
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

- Respuesta:
  - `201 Created` con la entidad `Cita` (mapeada por el servicio) en caso de éxito.
  - `409 Conflict` con mensaje en caso de que el horario no esté disponible u ocurra un conflicto.

Nota: el servicio intenta enviar un correo de confirmación pero NO aborta la operación si falla el envío.

----

### POST /citas/buscar
- Descripción: Buscar citas por `email` y `nombre`.
- Request: `BuscarCitaRequest`:
```json
{ "email": "maria@example.com", "nombre": "María Pérez" }
```
- Respuesta: `200 OK` con `List<CitaResponse>`.

----

### POST /solicitudes/reprogramar
- Descripción: Crear una solicitud de reprogramación para una cita existente.
- Request: `ReprogramacionRequest`:
```json
{ "citaId": 1, "motivo": "Cambio de horario", "explicacion": "Motivo..." }
```
- Respuesta: `201 Created` con la entidad `Reprogramacion`.

----

### POST /solicitudes/aprobar
- Descripción: Aprobar una solicitud de reprogramación y asignar un nuevo horario.
- Request: `AprobarReprogramacionRequest`:
```json
{ "solicitudId": 10, "nuevoHorarioId": 200 }
```
- Respuesta:
  - `200 OK` con la entidad `Reprogramacion` actualizada.
  - `409 Conflict` si el nuevo horario no puede reservarse.

----

### POST /test/email
- Descripción: Endpoint de pruebas para enviar un correo con datos de ejemplo. No persiste nada.
- Request: `TestEmailRequest`:
```json
{ "nombre": "Prueba", "email": "test@example.com", "fecha": "2025-12-05T09:00:00+00:00", "motivo": "Confirmación" }
```
- Respuesta: `200 OK` o `400 Bad Request` si falta `email`.

----

## Notas sobre formatos
- Fechas en DTOs del frontend: `fechaHoraInicio` es un string ISO con zona (`OffsetDateTime.toString()` en Java).
- `fechaNacimiento` en `AgendarCitaRequest` es `YYYY-MM-DD` (LocalDate).

**Fin del resumen API.**
