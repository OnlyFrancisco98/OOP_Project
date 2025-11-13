package com.uady.psicologia.citas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServicio {
    @Autowired
    private JavaMailSender emailSender;

    public void requestAppointment(String userName, String email, String appointmentDetails) {
        System.out.println("Procesando solicitud de cita para " + userName + "...");
        System.out.println("¡Cita registrada exitosamente!");
        System.out.println("Detalles de la cita: " + appointmentDetails);

        sendConfirmationEmail(email, userName, appointmentDetails);
    }

    public void sendConfirmationEmail(String to, String userName, String appointmentDetails) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("facultad.psicologia@uady.mx");
            message.setTo(to);
            message.setSubject("Confirmación de Cita");
            message.setText("Hola " + userName + ",\n\n" +
                          "Tu cita ha sido registrada exitosamente.\n\n" +
                          "Detalles:\n" + appointmentDetails + "\n\n" +
                          "Por favor confirma tu asistencia respondiendo a este correo.\n\n" +
                          "Gracias.");

            emailSender.send(message);
            System.out.println(" Correo de confirmación enviado a " + to);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al enviar el correo", e);
        }
    }

    /**
     * Envía un correo simple con asunto y cuerpo personalizado. Usar para notificaciones de éxito/fracaso.
     */
    public void sendSimpleEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("facultad.psicologia@uady.mx");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            emailSender.send(message);
            System.out.println("Correo enviado a " + to + " (" + subject + ")");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al enviar el correo", e);
        }
    }
}