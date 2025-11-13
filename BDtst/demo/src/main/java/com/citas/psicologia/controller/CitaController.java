package com.citas.psicologia.controller;

import com.citas.psicologia.dto.*;
import com.citas.psicologia.entity.*;
import com.citas.psicologia.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // Todas las URLs empezarán con /api
@CrossOrigin(origins = "*") // 👈 2. Agrega esta línea
public class CitaController {

    @Autowired
    private CitaService citaService;

    // ----- Flujo 1: Agendar Cita -----

    /**
     * Endpoint para que el paciente VEA los horarios disponibles.
     */
    @GetMapping("/horarios")
    public ResponseEntity<List<Horario>> getHorariosDisponibles() {
        List<Horario> horarios = citaService.getHorariosDisponibles();
        return ResponseEntity.ok(horarios);
    }

    /**
     * Endpoint para que el paciente AGENTE la cita.
     */
    @PostMapping("/agendar")
    public ResponseEntity<?> agendarCita(@RequestBody AgendarCitaRequest request) { // Cambiado a ResponseEntity<?>
        try {
            Cita nuevaCita = citaService.agendarCita(request);
            // Si tiene éxito, devuelve la cita (HTTP 201 Creado)
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCita);
        } catch (RuntimeException e) {
            // Si falla, ahora devuelve el MENSAJE DE ERROR REAL
            // con un estado HTTP 409 (Conflicto).
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // ----- Flujo 2: Mis Citas y Reprogramación -----

    /**
     * Endpoint para BUSCAR las citas de un paciente.
     */
    @PostMapping("/mis-citas")
    public ResponseEntity<List<Cita>> buscarMisCitas(@RequestBody BuscarCitasRequest request) {
        List<Cita> citas = citaService.buscarMisCitas(request);
        return ResponseEntity.ok(citas);
    }

    /**
     * Endpoint para SOLICITAR una reprogramación.
     */
    @PostMapping("/reprogramar")
    public ResponseEntity<SolicitudReprogramacion> solicitarReprogramacion(@RequestBody ReprogramacionRequest request) {
        try {
            SolicitudReprogramacion solicitud = citaService.solicitarReprogramacion(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(solicitud);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // HTTP 404 No encontrado
        }
    }
}