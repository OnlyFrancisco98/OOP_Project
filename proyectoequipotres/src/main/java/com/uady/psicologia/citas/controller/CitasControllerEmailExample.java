package com.uady.psicologia.citas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uady.psicologia.citas.services.AppointmentNotification;

/**
 * Ejemplo de integración del servicio de correos.
 * 
 * Este archivo es un EJEMPLO. Usa como referencia para integrar el EmailServicio
 * en tu aplicación existente.
 */
@RestController
@RequestMapping("/api/correos")
public class CitasControllerEmailExample {

    @Autowired
    private AppointmentNotification appointmentNotification;

    /**
     * Ejemplo 1: Enviar correo de confirmación de cita
     * 
     * POST /api/correos/confirmacion
     * 
     * Parámetros:
     * - email: Email del usuario
     * - nombre: Nombre del usuario
     * - detalles: Detalles de la cita
     */
    @PostMapping("/confirmacion")
    public ResponseEntity<?> enviarConfirmacion(
            @RequestParam String email,
            @RequestParam String nombre,
            @RequestParam String detalles) {
        try {
            System.out.println("Enviando confirmación a: " + email);

            appointmentNotification.requestAppointment(nombre, email, detalles);

            System.out.println("✓ Correo de confirmación enviado a: " + email);
            return ResponseEntity.ok("Confirmación enviada exitosamente");

        } catch (Exception e) {
            System.err.println("✗ Error al enviar confirmación: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error al enviar correo: " + e.getMessage());
        }
    }

    /**
     * Ejemplo 2: Enviar correo de éxito en cita
     * 
     * POST /api/correos/exito
     * 
     * Parámetros:
     * - email: Email del usuario
     * - nombre: Nombre del usuario
     */
    @PostMapping("/exito")
    public ResponseEntity<?> enviarNotificacionExito(
            @RequestParam String email,
            @RequestParam String nombre) {
        try {
            appointmentNotification.notifySuccess(email, nombre);
            return ResponseEntity.ok("Notificación de éxito enviada a: " + email);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al enviar notificación: " + e.getMessage());
        }
    }

    /**
     * Ejemplo 3: Enviar correo de error en cita
     * 
     * POST /api/correos/error
     * 
     * Parámetros:
     * - email: Email del usuario
     * - nombre: Nombre del usuario
     * - razon: Razón del error
     */
    @PostMapping("/error")
    public ResponseEntity<?> enviarNotificacionError(
            @RequestParam String email,
            @RequestParam String nombre,
            @RequestParam String razon) {
        try {
            appointmentNotification.notifyFailure(email, nombre, razon);
            return ResponseEntity.ok("Notificación de error enviada a: " + email);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al enviar notificación: " + e.getMessage());
        }
    }

    /**
     * Ejemplo 4: Enviar correo personalizado
     * 
     * POST /api/correos/personalizado
     * 
     * Parámetros:
     * - email: Email del destinatario
     * - asunto: Asunto del correo
     * - mensaje: Cuerpo del mensaje
     */
    @PostMapping("/personalizado")
    public ResponseEntity<?> enviarCorreoPersonalizado(
            @RequestParam String email,
            @RequestParam String asunto,
            @RequestParam String mensaje) {
        try {
            // Para usar EmailServicio directamente, inyéctalo en tu controlador:
            // @Autowired private EmailServicio emailServicio;
            // emailServicio.sendSimpleEmail(email, asunto, mensaje);
            
            return ResponseEntity.ok("Endpoint de ejemplo. Ver documentación de uso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al enviar correo: " + e.getMessage());
        }
    }
}
