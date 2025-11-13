package com.uady.psicologia.citas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentNotification {

    @Autowired
    private EmailServicio emailServicio;

    /**
     * Procesa la solicitud de una cita y envía una notificación por correo si se proporciona email.
     */
    public void requestAppointment(String userName, String email, String appointmentDetails) {
        // Lógica de negocio mínima (registro, persistencia, etc. se implementaría en otros servicios)
        System.out.println("Procesando solicitud de cita para " + userName + "...");
        System.out.println("✅ ¡Cita registrada exitosamente!");
        System.out.println("Detalles de la cita: " + appointmentDetails);
        // Enviar solo un mensaje de éxito breve si hay email
        if (email != null && !email.isBlank()) {
            notifySuccess(email, userName);
        }
    }

    /**
     * Envía una notificación breve de éxito al usuario cuando la cita se creó correctamente.
     */
    public void notifySuccess(String email, String userName) {
        String subject = "Cita creada con éxito";
        String body = "Hola " + userName + ",\n\nTu cita ha sido creada correctamente.\n\nSaludos,\nFacultad de Psicología UADY";
        emailServicio.sendSimpleEmail(email, subject, body);
    }

    /**
     * Envía una notificación breve de fracaso al usuario cuando ocurrió un error al crear la cita.
     */
    public void notifyFailure(String email, String userName, String reason) {
        String subject = "Error al crear la cita";
        String body = "Hola " + userName + ",\n\nNo fue posible crear tu cita. Motivo: " + reason + "\n\nPor favor intenta de nuevo o contacta al soporte.";
        emailServicio.sendSimpleEmail(email, subject, body);
    }
}
