package com.uady.psicologia.citas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Clase de prueba para verificar el envío de correos.
 * Se ejecutará al iniciar la aplicación si está activa.
 */
@Component
public class EmailTestRunner implements CommandLineRunner {

    @Autowired
    private EmailServicio emailServicio;

    @Override
    public void run(String... args) throws Exception {
        // Descomentar la siguiente línea para probar el envío de correos
        // testEmailSending();
    }

    /**
     * Método para probar el envío de correos
     */
    public void testEmailSending() {
        System.out.println("\n========== INICIANDO PRUEBA DE ENVÍO DE CORREOS ==========\n");

        try {
            // Prueba 1: Enviar correo de confirmación de cita
            System.out.println("Prueba 1: Enviando correo de confirmación de cita...");
            emailServicio.sendConfirmationEmail(
                "mercurialdominator@gmail.com",
                "Juan Pérez",
                "Fecha: 15/11/2025\nHora: 10:00 AM\nConsultorio: 301\nPsicólogo: Dr. López"
            );
            System.out.println("✓ Correo de confirmación enviado exitosamente\n");

            // Prueba 2: Enviar correo simple de éxito
            System.out.println("Prueba 2: Enviando correo simple de éxito...");
            emailServicio.sendSimpleEmail(
                "mercurialdominator@gmail.com",
                "Cita creada con éxito",
                "Hola,\n\nTu cita ha sido creada correctamente en nuestro sistema.\n\nSaludos,\nFacultad de Psicología UADY"
            );
            System.out.println("✓ Correo simple enviado exitosamente\n");

            System.out.println("========== PRUEBAS COMPLETADAS EXITOSAMENTE ==========\n");

        } catch (Exception e) {
            System.out.println("✗ Error durante las pruebas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
