package com.citas.psicologia.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long citaId;

    @Column(columnDefinition = "TEXT")
    private String motivoConsulta;

    @Column(nullable = false)
    private String estadoCita = "Agendada";

    private OffsetDateTime fechaCreacion = OffsetDateTime.now();

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @OneToOne
    @JoinColumn(name = "horario_id", nullable = false, unique = true)
    private Horario horario;

    @OneToMany(mappedBy = "cita")
    private Set<SolicitudReprogramacion> solicitudes;
}