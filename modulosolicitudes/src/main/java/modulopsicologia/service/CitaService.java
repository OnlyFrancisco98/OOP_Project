package modulopsicologia.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import modulopsicologia.dto.AgendarCitaRequest;
import modulopsicologia.dto.BuscarCitaRequest;
import modulopsicologia.dto.CitaResponse;
import modulopsicologia.dto.HorarioResponse;
import modulopsicologia.dto.SolicitudResponse;
import modulopsicologia.model.Cita;
import modulopsicologia.model.Horario;
import modulopsicologia.model.Paciente;
import modulopsicologia.repository.CitaRepository;

@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private HorarioService horarioService;
    @Autowired
    private EmailService emailService;

    @Transactional
    public Cita agendarCita(AgendarCitaRequest request){
        Paciente paciente = pacienteService.crearPaciente(request);
        Horario horario = horarioService.validarHorario(request.getHorarioId());
        Cita citaGuardada = crearCita(request, paciente, horario);
        horarioService.marcarHorarioComoOcupado(horario);
        // Ensure patient has a temporary password and send confirmation email
        String contrasenaTemporal = pacienteService.asegurarContrasenaTemporal(paciente);
        try{
            emailService.enviarConfirmacionCita(paciente, citaGuardada, contrasenaTemporal);
        } catch(RuntimeException e){
            System.err.println("[EmailService] Error al enviar correo de confirmacion: " + e.getMessage());
        }
        return citaGuardada;
    }

    private Cita crearCita(AgendarCitaRequest request, Paciente paciente, Horario horario){
        Cita nuevaCita = new Cita();
        nuevaCita.setPaciente(paciente);
        nuevaCita.setHorario(horario);
        nuevaCita.setMotivoConsulta(request.getMotivoConsulta());
        System.out.println("INTENTANDO GUARDAR CITA..." + nuevaCita.toString());
        return citaRepository.save(nuevaCita);
    }

    public List<CitaResponse> buscarMisCitas(BuscarCitaRequest request){

        // 1. Obtenemos las Entidades (como antes)
        List<Cita> citasDesdeDB = citaRepository.buscarPorEmailYNombreDePaciente(request.getEmail(), request.getNombre());

        // 2. Las convertimos a DTOs de Respuesta
        return citasDesdeDB.stream()
                .map(this::convertirACitaResponse) // Llama al método de arriba
                .collect(Collectors.toList());

        // Orignial       
        //return citaRepository.findByPaciente_EmailAndPaciente_Nombre(request.getEmail(), request.getNombre());
    }

    public Cita encontrarCitaPorId(Long id){
        Optional<Cita> optionalCita = citaRepository.findById(id);
        if(optionalCita.isPresent()){
            return optionalCita.get();
        } else {
            throw new RuntimeException("Cita no encontrada con ID: " + id);
        }
    }
    
    private CitaResponse convertirACitaResponse(Cita cita) {
        CitaResponse dto = new CitaResponse();

        // Mapeo simple de Cita
        dto.setCitaId(cita.getCitaId());
        dto.setMotivoConsulta(cita.getMotivoConsulta());
        dto.setEstadoCita(cita.getEstadoCita());
        dto.setFechaCreacion(cita.getFechaCreacion());

        // Mapeo del Paciente (evitando el bucle)
        dto.setPacienteId(cita.getPaciente().getPacienteId());
        dto.setPacienteNombre(cita.getPaciente().getNombre());
        dto.setPacienteEmail(cita.getPaciente().getEmail());

        // Mapeo del Horario
        HorarioResponse horarioDto = new HorarioResponse();
        horarioDto.setHorarioId(cita.getHorario().getHorarioId());
        horarioDto.setFechaHoraInicio(cita.getHorario().getFechaHoraInicio().toString());
        horarioDto.setDuracionMinutos(cita.getHorario().getDuracionMinutos());
        horarioDto.setEstaDisponible(cita.getHorario().isEstaDisponible());
        dto.setHorario(horarioDto);

        // Mapeo de la lista de Solicitudes
        Set<SolicitudResponse> solicitudDtos = cita.getSolicitudes().stream()
                .map(solicitud -> {
                    SolicitudResponse sDto = new SolicitudResponse();
                    sDto.setSolicitudId(solicitud.getSolicitudId());
                    sDto.setMotivo(solicitud.getMotivo());
                    sDto.setExplicacion(solicitud.getExplicacion());
                    sDto.setFechaSolicitud(solicitud.getFechaSolicitud());
                    sDto.setEstadoSolicitud(solicitud.getEstadoSolicitud());
                    sDto.setRespuestaAdmin(solicitud.getRespuestaAdmin());
                    return sDto;
                }).collect(Collectors.toSet());
        dto.setSolicitudes(solicitudDtos);

        return dto;
    }
}
