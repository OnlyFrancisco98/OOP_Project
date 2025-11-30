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

import modulopsicologia.model.Cita;
import modulopsicologia.model.Horario;
import modulopsicologia.model.Paciente;
import modulopsicologia.model.Reprogramacion;
import modulopsicologia.repository.CitaRepository;
import modulopsicologia.repository.ReprogramacionRepository;

public class ReprogramacionServiceTest {

    @Mock
    private ReprogramacionRepository reprogramacionRepository;

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private HorarioService horarioService;

    @Mock
    private EmailService emailService;

    @Mock
    private PacienteService pacienteService;

    @InjectMocks
    private ReprogramacionService reprogramacionService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void aprobarReprogramacion_shouldMoveHorario_andSendEmail(){
        // existing entities
        Paciente paciente = new Paciente();
        paciente.setPacienteId(20L);
        paciente.setNombre("Reprog User");
        paciente.setEmail("reprog@example.com");

        Horario oldHorario = new Horario();
        oldHorario.setHorarioId(5L);
        oldHorario.setEstaDisponible(false);
        oldHorario.setFechaHoraInicio(OffsetDateTime.now().plusDays(2));

        Cita cita = new Cita();
        cita.setCitaId(200L);
        cita.setPaciente(paciente);
        cita.setHorario(oldHorario);

        Reprogramacion solicitud = new Reprogramacion();
        solicitud.setSolicitudId(300L);
        solicitud.setCita(cita);

        Horario nuevoHorario = new Horario();
        nuevoHorario.setHorarioId(99L);
        nuevoHorario.setEstaDisponible(true);
        nuevoHorario.setFechaHoraInicio(OffsetDateTime.now().plusDays(10));

        when(reprogramacionRepository.findById(300L)).thenReturn(java.util.Optional.of(solicitud));
        when(horarioService.validarHorario(99L)).thenReturn(nuevoHorario);
        when(citaRepository.save(any(Cita.class))).thenReturn(cita);
        when(pacienteService.asegurarContrasenaTemporal(paciente)).thenReturn("ReprogPass$1");

        //reprogramacionService.aprobarReprogramacion(300L, 99L);

        // verify that old horario was released and new horario reserved
        verify(horarioService).marcarHorarioComoDisponible(oldHorario);
        verify(horarioService).marcarHorarioComoOcupado(nuevoHorario);
        verify(reprogramacionRepository).save(solicitud);
        verify(emailService).enviarReprogramacionCita(paciente, cita, "ReprogPass$1");
    }
}
