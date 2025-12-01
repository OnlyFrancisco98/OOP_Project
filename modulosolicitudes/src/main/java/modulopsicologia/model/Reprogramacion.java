package modulopsicologia.model;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "solicitudes_reprogramacion")
public class Reprogramacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solicitud_id")
    private Long solicitudId;

    @Column(name = "motivo", nullable = false)
    private String motivo;

    @Column(name = "explicacion", columnDefinition = "TEXT")
    private String explicacion;

    @Column(name = "fecha_solicitud", nullable = false, updatable = false)
    private OffsetDateTime fechaSolicitud;

    @Column(name = "estado_solicitud", nullable = false)
    private String estadoSolicitud = "Pendiente"; // Valor por defecto

    @Column(name = "respuesta_admin", columnDefinition = "TEXT")
    private String respuestaAdmin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cita_id", nullable = false)
    @JsonBackReference
    private Cita cita;

    @PrePersist
    protected void onCreate() {
        this.fechaSolicitud = OffsetDateTime.now();
    }
}
