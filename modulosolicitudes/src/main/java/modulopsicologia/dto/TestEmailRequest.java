package modulopsicologia.dto;

import lombok.Data;

@Data
public class TestEmailRequest {
    private String nombre;
    private String email;
    private String fecha; // ISO string
    private String motivo;
}
