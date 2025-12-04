package modulopsicologia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import modulopsicologia.dto.TestEmailRequest;
import modulopsicologia.model.Cita;
import modulopsicologia.model.Horario;
import modulopsicologia.model.Paciente;
import modulopsicologia.service.EmailService;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestEmailController {

    @Autowired
    private EmailService emailService;

    // Not using persistence here — the test endpoint constructs a transient Paciente

    @PostMapping("/email")
    public ResponseEntity<?> sendTestEmail(@RequestBody TestEmailRequest req){
        if(req.getEmail() == null || req.getEmail().isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falta el campo email");
        }

        try{
            // Build a transient Paciente object (do not persist) so we can send a test email
            Paciente paciente = new Paciente();
            paciente.setNombre(req.getNombre() != null ? req.getNombre() : "Prueba Usuario");
            paciente.setEmail(req.getEmail());
            paciente.setTelefono("0000000000");

            // generate a temporary password (12 chars) — keep local, do not attempt DB
            String contrasena = generateTempPassword();

            // Build a lightweight Cita object for email body
            Cita cita = new Cita();
            cita.setPaciente(paciente);
            Horario h = new Horario();
            if(req.getFecha() != null){
                try{
                    h.setFechaHoraInicio(OffsetDateTime.parse(req.getFecha()));
                } catch(Exception ex){
                    // ignore parse and leave null
                }
            }
            cita.setHorario(h);
            cita.setMotivoConsulta(req.getMotivo() != null ? req.getMotivo() : "Prueba de confirmación");

            // Send confirmation email
            emailService.enviarConfirmacionCita(paciente, cita, contrasena);

            return ResponseEntity.ok("Correo de confirmación intentado a: " + req.getEmail());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error enviando correo: " + e.getMessage());
        }
    }

    private String generateTempPassword(){
        java.security.SecureRandom sr = new java.security.SecureRandom();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%&*-_";
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<12;i++){
            sb.append(chars.charAt(sr.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
