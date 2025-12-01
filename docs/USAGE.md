# Usage — Application Interaction

## Main Frontend
- `src/main/resources/view/solicitud.html` is the page used to request an appointment.
- The calendar on the page loads available schedules from `GET /api/horarios/disponibles` and displays the time slots.
- When selecting a schedule and completing the form, the frontend sends `POST /api/citas` with the `horarioId` and patient information.

## Typical Quick Verification Flow
1. Start the application (`mvn spring-boot:run`).
2. Open `src/main/resources/view/solicitud.html` in your browser (you can open the file locally or serve it from the backend if you place it under `resources/static`).
3. Check in the browser console that the request to `http://localhost:8081/api/horarios/disponibles` responds correctly.
4. Select a schedule and submit the form.
5. Review backend logs for email-sending attempts and persistence events.

## Email Test Endpoint
- `POST /api/test/email` allows testing the email template without persisting any data.

## Error Behavior
- If the schedule is no longer available, the backend responds with `409 Conflict` and a helpful message; the frontend displays an alert with that backend message.
- If `JavaMailSender` is not configured, the service logs a warning and continues with the main operation (the appointment is still created).

## Recommendation for Frontend Development
- Serve the views through a static server or copy the HTML to `src/main/resources/static` so they are served by the backend, avoiding CORS issues in more restrictive environments.
