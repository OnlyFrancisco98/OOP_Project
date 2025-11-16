package com.citas.psicologia.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long horarioId;

    @Column(nullable = false, unique = true)
    private OffsetDateTime fechaHoraInicio;

    private int duracionMinutos = 60;
    
    @Column(nullable = false)
    private boolean estaDisponible = true;

    @OneToOne(mappedBy = "horario")
    private Cita citaAsignada;
}