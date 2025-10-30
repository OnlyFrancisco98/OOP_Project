package com.uady.psicologia.citas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uady.psicologia.citas.model.entidades.HorarioDisponible;
import com.uady.psicologia.citas.services.CitaServicio;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitasController {
    
    @Autowired
    private CitaServicio citaService;

    @GetMapping("/horarios")
    public ResponseEntity<List<HorarioDisponible>> obtenerHorarios() {
        return ResponseEntity.ok(citaService.getHorariosDisponibles());
    }
}
