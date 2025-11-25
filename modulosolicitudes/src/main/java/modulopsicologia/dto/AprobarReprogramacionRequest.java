package modulopsicologia.dto;

import lombok.Data;

@Data
public class AprobarReprogramacionRequest {
    private Long solicitudId;
    private Long nuevoHorarioId;
}
