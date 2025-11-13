package com.uady.psicologia.citas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailServicio {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private Environment env;

    @Value("${app.email.mode:file}")
    private String emailMode; // "smtp" or "file"

    @Value("${app.email.output-dir:target/emails}")
    private String outputDir;

    public void requestAppointment(String userName, String email, String appointmentDetails) {
        System.out.println("Procesando solicitud de cita para " + userName + "...");
        System.out.println("¡Cita registrada exitosamente!");
        System.out.println("Detalles de la cita: " + appointmentDetails);

        sendConfirmationEmail(email, userName, appointmentDetails);
    }

    public void sendConfirmationEmail(String to, String userName, String appointmentDetails) {
        String subject = "Confirmación de Cita";
        String text = "Hola " + userName + ",\n\n" +
                "Tu cita ha sido registrada exitosamente.\n\n" +
                "Detalles:\n" + appointmentDetails + "\n\n" +
                "Por favor confirma tu asistencia respondiendo a este correo.\n\n" +
                "Gracias.";

        sendEmailWithFallback(to, subject, text);
    }

    /**
     * Envía un correo simple con asunto y cuerpo personalizado. Usar para notificaciones de éxito/fracaso.
     */
    public void sendSimpleEmail(String to, String subject, String body) {
        sendEmailWithFallback(to, subject, body);
    }

    private void sendEmailWithFallback(String to, String subject, String body) {
        if ("file".equalsIgnoreCase(emailMode)) {
            writeEmailToFile(to, subject, body);
            System.out.println("[EMAIL-FILE] Guardado correo en modo FILE para " + to);
            return;
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            String from = env.getProperty("spring.mail.username", "noreply@example.com");
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            emailSender.send(message);
            System.out.println("Correo enviado a " + to + " (" + subject + ")");
        } catch (Exception e) {
            // Si falla por autenticación o cualquier error de transporte, guardamos el correo para inspección
            System.err.println("Fallo al enviar por SMTP: " + e.getMessage());
            if (e instanceof MailAuthenticationException || e.getCause() instanceof MailAuthenticationException) {
                System.err.println("Fallo de autenticación SMTP. Guardando correo en disco como fallback.");
            }
            writeEmailToFile(to, subject, body);
        }
    }

    private void writeEmailToFile(String to, String subject, String body) {
        try {
            Path dir = Paths.get(outputDir);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
            String fileName = "email_" + timestamp + ".txt";
            Path file = dir.resolve(fileName);

            StringBuilder sb = new StringBuilder();
            sb.append("From: ").append(env.getProperty("spring.mail.username", "noreply@example.com")).append('\n');
            sb.append("To: ").append(to).append('\n');
            sb.append("Subject: ").append(subject).append('\n');
            sb.append("Date: ").append(LocalDateTime.now().toString()).append('\n');
            sb.append('\n');
            sb.append(body).append('\n');

            Files.write(file, sb.toString().getBytes(StandardCharsets.UTF_8));
            System.out.println("[EMAIL-FILE] Correo guardado en: " + file.toAbsolutePath());
        } catch (IOException io) {
            System.err.println("No se pudo guardar el correo en disco: " + io.getMessage());
        }
    }
}