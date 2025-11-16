package com.citas.psicologia.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AgendarCitaRequest {

    // Campos del formulario del paciente
    private String nombre;
    private String email;
    private String telefono;
    private LocalDate fechaNacimiento;
    
    // Campos de la cita
    private Long horarioId;
    private String motivoConsulta; 
    
}