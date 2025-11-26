package modulopsicologia.dto;

import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class SolicitudResponse {
    private Long solicitudId;
    private String motivo;
    private String explicacion;
    private OffsetDateTime fechaSolicitud;
    private String estadoSolicitud;
    private String respuestaAdmin;
}
