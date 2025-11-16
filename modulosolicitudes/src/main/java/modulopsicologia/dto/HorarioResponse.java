package modulopsicologia.dto;

import lombok.Data;
import java.time.OffsetDateTime;

// Un DTO simple para mostrar el horario
@Data
public class HorarioResponse {
    private Long horarioId;
    private OffsetDateTime fechaHoraInicio;
    private Integer duracionMinutos;
    private boolean estaDisponible;
}