package modulopsicologia.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import modulopsicologia.dto.AgendarCitaRequest;
import modulopsicologia.model.Cita;
import modulopsicologia.model.Horario;
import modulopsicologia.model.Paciente;
import modulopsicologia.repository.CitaRepository;

public class CitaServiceTest {

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private PacienteService pacienteService;

    @Mock
    private HorarioService horarioService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private CitaService citaService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void agendarCita_shouldCreateCita_andSendEmail(){
        AgendarCitaRequest req = new AgendarCitaRequest();
        req.setNombre("Test User");
        req.setEmail("test.user@example.com");
        req.setTelefono("12345678");
        req.setHorarioId(1L);
        req.setMotivoConsulta("Consulta de prueba");

        Paciente paciente = new Paciente();
        paciente.setPacienteId(10L);
        paciente.setNombre(req.getNombre());
        paciente.setEmail(req.getEmail());

        Horario horario = new Horario();
        horario.setHorarioId(1L);
        horario.setEstaDisponible(true);
        horario.setFechaHoraInicio(OffsetDateTime.now().plusDays(1));

        Cita saved = new Cita();
        saved.setCitaId(100L);
        saved.setPaciente(paciente);
        saved.setHorario(horario);
        saved.setMotivoConsulta(req.getMotivoConsulta());

        when(pacienteService.crearPaciente(any())).thenReturn(paciente);
        when(horarioService.validarHorario(1L)).thenReturn(horario);
        when(citaRepository.save(any(Cita.class))).thenReturn(saved);
        when(pacienteService.asegurarContrasenaTemporal(paciente)).thenReturn("TempPass123$");

        Cita result = citaService.agendarCita(req);

        // verify interactions
        verify(horarioService).marcarHorarioComoOcupado(horario);
        verify(emailService).enviarConfirmacionCita(paciente, saved, "TempPass123$");

        assert result != null;
        assert result.getCitaId().equals(100L);
    }
}
