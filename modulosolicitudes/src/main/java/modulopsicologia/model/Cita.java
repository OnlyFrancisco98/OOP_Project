package modulopsicologia.model;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

// import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cita_id")
    private Long citaId;

    @Column(name = "motivo_consulta", columnDefinition = "TEXT") 
    private String motivoConsulta;

    @Column(name = "estado_cita", nullable = false) 
    private String estadoCita = "Agendada";

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private OffsetDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    @JsonManagedReference
    private Paciente paciente;

    @OneToOne
    @JoinColumn(name = "horario_id", nullable = false, unique = true)
    @JsonManagedReference
    private Horario horario;

    @OneToMany(
        mappedBy = "cita",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonManagedReference
    private Set<Reprogramacion> solicitudes = new HashSet<>();
    
    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = OffsetDateTime.now();
    }
}   
