package modulopsicologia.dto;

import lombok.Data;

// Un DTO simple para mostrar el horario al frontend
@Data
public class HorarioResponse {
    private Long horarioId;
    // Enviar fecha como string ISO para evitar problemas de serialización/parseo en JS
    private String fechaHoraInicio;
    private Integer duracionMinutos;
    private boolean estaDisponible;
}