package com.citas.psicologia.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "solicitudes_reprogramacion")
public class SolicitudReprogramacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long solicitudId;

    @Column(nullable = false)
    private String motivo;
    
    @Column(columnDefinition = "TEXT")
    private String explicacion;

    private OffsetDateTime fechaSolicitud = OffsetDateTime.now();
    
    @Column(nullable = false)
    private String estadoSolicitud = "Pendiente";
    
    private String respuestaAdmin = "Hemos recibido la solicitud.";

    @ManyToOne
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;
}