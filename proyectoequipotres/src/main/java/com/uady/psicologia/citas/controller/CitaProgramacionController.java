package com.uady.psicologia.citas.controller;

import com.uady.psicologia.citas.services.EmailServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CitaProgramacionController {

    @Autowired
    private EmailServicio emailServicio;

    @PostMapping("/citas/programar")
    public ResponseEntity<?> programarCita(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");

            if (email == null || email.trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("mensaje", "El correo es requerido");
                return ResponseEntity.badRequest().body(error);
            }

            // Enviar correo de confirmación
            emailServicio.sendSimpleEmail(
                email,
                "✓ Cita Programada",
                "¡Hola!\n\n" +
                "Tu cita ha sido programada exitosamente.\n\n" +
                "Detalles:\n" +
                "- Fecha: " + java.time.LocalDate.now() + "\n" +
                "- Correo de confirmación: " + email + "\n\n" +
                "Si tienes dudas, contacta con nosotros.\n\n" +
                "Facultad de Psicología UADY"
            );

            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Cita programada exitosamente");
            response.put("correo", email);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error al programar la cita: " + e.getMessage());
            return ResponseEntity.status(500).body(error);
        }
    }
}
