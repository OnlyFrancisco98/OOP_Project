package modulopsicologia.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class AgendarCitaRequest {
    private String nombre;
    private String email;
    private String telefono;
    private LocalDate fechaNacimiento;
    
    private Long horarioId;
    private String motivoConsulta;
}
