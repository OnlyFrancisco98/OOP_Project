package modulopsicologia.service;

import modulopsicologia.model.Cita;
import modulopsicologia.model.Horario;
import modulopsicologia.model.Paciente;
import modulopsicologia.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class SendReprogramEmailIntegrationTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void sendReprogramEmail_toKevincanto_shouldNotThrow() {
        Paciente paciente = new Paciente();
        paciente.setNombre("Test Usuario");
        paciente.setEmail("kevincanto21.7@gmail.com");
        paciente.setTelefono("0000000000");

        Cita cita = new Cita();
        Horario h = new Horario();
        h.setFechaHoraInicio(OffsetDateTime.now().plusDays(2));
        cita.setHorario(h);
        cita.setPaciente(paciente);
        cita.setMotivoConsulta("Prueba de reprogramación (integración)");

        String tempPass = "TestReprogPwd#1";

        // Ensure the email sending method executes without throwing an exception
        assertDoesNotThrow(() -> emailService.enviarReprogramacionCita(paciente, cita, tempPass));
    }
}
