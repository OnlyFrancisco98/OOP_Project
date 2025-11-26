package modulopsicologia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import modulopsicologia.dto.ReprogramacionRequest;
import modulopsicologia.model.Cita;
import modulopsicologia.model.Reprogramacion;
import modulopsicologia.model.Horario;
import modulopsicologia.repository.CitaRepository;
import modulopsicologia.repository.ReprogramacionRepository;

@Service
public class ReprogramacionService {
    
    @Autowired
    private ReprogramacionRepository reprogramacionRepository;
    
    @Autowired
    private CitaRepository citaRepository;
    
    @Autowired
    private HorarioService horarioService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PacienteService pacienteService;

    @Transactional
    public Reprogramacion solicitarReprogramacion(ReprogramacionRequest request){
        Cita cita = validarCita(request.getCitaId());
        actualizarEstadoCita(cita, "Reprogramacion_Pendiente");        
        return guardarSolicitud(request, cita);
    }

    @Transactional
    public Reprogramacion aprobarReprogramacion(Long solicitudId, Long nuevoHorarioId){
        Reprogramacion solicitud = reprogramacionRepository.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("Solicitud de reprogramación no encontrada: " + solicitudId));

        Cita cita = solicitud.getCita();
        if(cita == null) throw new RuntimeException("La solicitud no está vinculada a una cita válida");

        // Liberar el horario antiguo
        Horario horarioAntiguo = cita.getHorario();
        if(horarioAntiguo != null){
            horarioService.marcarHorarioComoDisponible(horarioAntiguo);
        }

        // Validar y reservar el nuevo horario
        Horario nuevoHorario = horarioService.validarHorario(nuevoHorarioId);
        horarioService.marcarHorarioComoOcupado(nuevoHorario);

        // Actualizar cita
        cita.setHorario(nuevoHorario);
        cita.setEstadoCita("Agendada");
        citaRepository.save(cita);

        // Actualizar solicitud
        solicitud.setEstadoSolicitud("Aprobada");
        reprogramacionRepository.save(solicitud);

        // Enviar correo de reprogramación
        String contrasena = pacienteService.asegurarContrasenaTemporal(cita.getPaciente());
        try{
            emailService.enviarReprogramacionCita(cita.getPaciente(), cita, contrasena);
        } catch(RuntimeException e){
            System.err.println("[EmailService] Error al enviar correo de reprogramacion: " + e.getMessage());
        }

        return solicitud;
    }


    private Cita validarCita(Long citaId){
        Optional<Cita> optionalCita = citaRepository.findById(citaId);
        if(optionalCita.isPresent()){
            return optionalCita.get();
        } else {
            throw  new RuntimeException("Cita no encontrada para reprogramación");
        }
    }

    private void actualizarEstadoCita(Cita cita, String nuevoEstado){
        cita.setEstadoCita(nuevoEstado);
        citaRepository.save(cita);
    }

    private Reprogramacion guardarSolicitud(ReprogramacionRequest request, Cita cita){
        Reprogramacion solicitud = new Reprogramacion();
        solicitud.setCita(cita);
        solicitud.setMotivo(request.getMotivo());
        solicitud.setExplicacion(request.getExplicacion());
        return reprogramacionRepository.save(solicitud);
    }
    
    
}
 