# Arquitectura del Módulo de Solicitudes de Cita

Este documento resume la arquitectura del módulo `modulosolicitudes` (Spring Boot, JPA/Hibernate) incluido en este repositorio.

**Resumen en una línea:** API REST con capas bien definidas (Controller → Service → Repository) que expone endpoints consumidos por vistas estáticas (HTML) para crear, consultar y reprogramar citas.

## Capas y responsabilidades

- **Controladores (`modulopsicologia.controller`)**
  - Exponen la API REST, validan peticiones mínimas y delegan la lógica al servicio.
  - Ejemplos: `CitaController`, `HorarioController`, `ReprogramacionController`, `TestEmailController`.

- **Servicios (`modulopsicologia.service`)**
  - Contienen la lógica de negocio y orquestación entre repositorios y utilidades externas (correo).
  - Ejemplos: `CitaService`, `HorarioService`, `PacienteService`, `ReprogramacionService`, `EmailService`.

- **Repositorios (`modulopsicologia.repository`)**
  - Interfaz con la base de datos usando Spring Data JPA (`JpaRepository`).
  - Ejemplos: `CitaRepository`, `HorarioRepository`, `PacienteRepository`, `ReprogramacionRepository`.

- **Modelos/Entidades (`modulopsicologia.model`)**
  - Mapeo JPA de las tablas: `Cita`, `Horario`, `Paciente`, `Reprogramacion`.
  - Fechas principales usan `OffsetDateTime` y `LocalDate` según el contexto.

- **DTOs (`modulopsicologia.dto`)**
  - Contratos de entrada/salida para la API: `AgendarCitaRequest`, `CitaResponse`, `HorarioResponse`, etc.
  - Evitan exponer entidades JPA directamente al cliente.

- **Vistas estáticas**
  - Archivos HTML en `src/main/resources/view` (por ejemplo `solicitud.html`) consumen la API REST con `fetch`.

## Flujo típico (Agendar cita)

1. Frontend POST `/api/citas` con `AgendarCitaRequest`.
2. `CitaController` delega en `CitaService`.
3. `CitaService`:
   - crea/obtiene `Paciente` (o lo recupera por email usando `PacienteService`),
   - valida el `Horario` con `HorarioService` (verifica `estaDisponible`),
   - crea la `Cita` y marca el `Horario` como no disponible,
   - intenta enviar correo de confirmación usando `EmailService`.
4. Respuesta HTTP 201 con la cita o 409 en caso de conflicto.

## Consideraciones importantes

- La capa de correo (`EmailService`) está diseñada para no romper el flujo principal si falla (loggea y continúa).
- `HorarioRepository` filtra horarios válidos usando `findByEstaDisponibleTrueAndFechaHoraInicioAfter(OffsetDateTime.now())`.
- El formato de las fechas para la comunicación con el frontend es ISO (ej. `2025-12-05T09:00:00+00:00`) dentro del `HorarioResponse`.

## Diagramas

Revisa `Desing/AbstractionProcess.md` y los SVGs en `Requirements/AbstractionProcess/` para diagramas de clases y secuencia (si están presentes).

---

> Documento generado automáticamente a partir del análisis del código fuente y la documentación existente.
