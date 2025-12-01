# Uso — Interacción con la aplicación

## Frontend principal
- `src/main/resources/view/solicitud.html` es la página para solicitar una cita.
- El calendario en la página carga horarios disponibles desde `GET /api/horarios/disponibles` y muestra los slots.
- Al seleccionar un horario y completar el formulario, el frontend realiza `POST /api/citas` con el `horarioId` y datos del paciente.

## Flujo típico de verificación rápida
1. Levanta la aplicación (`mvn spring-boot:run`).
2. Abre `src/main/resources/view/solicitud.html` en el navegador (puedes abrir el archivo localmente o servirlo desde el backend si lo agregas a `resources/static`).
3. Verifica en consola del navegador que la petición a `http://localhost:8081/api/horarios/disponibles` responde correctamente.
4. Selecciona un horario y envía el formulario.
5. Revisa en la consola del backend logs sobre envío de correo y persistencia.

## Endpoint de pruebas de correo
- `POST /api/test/email` permite probar el template de correo sin persistir datos.

## Comportamiento de errores
- Si el horario ya no está disponible, el backend responde `409 Conflict` con un mensaje útil; el frontend muestra un alert con el mensaje proveniente del backend.
- Si el `JavaMailSender` no está configurado, el servicio registra un warning y continúa con la operación principal (la cita sigue siendo creada).

## Recomendación para desarrollo del frontend
- Sirve las vistas a través de un servidor estático o copia el HTML a `src/main/resources/static` para que se sirvan desde el mismo backend, evitando problemas de CORS en entornos más restrictivos.
