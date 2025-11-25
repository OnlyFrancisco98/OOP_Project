package modulopsicologia.dto;

import lombok.Data;
import java.time.OffsetDateTime;
import java.util.Set;

// ¡ESTE ES EL DTO CLAVE!
// Es lo que el controlador devolverá. No tiene bucles.
@Data
public class CitaResponse {

    // Campos de la Cita
    private Long citaId;
    private String motivoConsulta;
    private String estadoCita;
    private OffsetDateTime fechaCreacion;
    
    // ¡Aquí está la magia!
    // No devolvemos la Entidad 'Paciente', solo los campos que necesitamos.
    private Long pacienteId;
    private String pacienteNombre;
    private String pacienteEmail;

    // Usamos nuestros otros DTOs
    private HorarioResponse horario;
    private Set<SolicitudResponse> solicitudes;
}