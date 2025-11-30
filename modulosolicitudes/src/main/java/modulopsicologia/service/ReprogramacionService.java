package modulopsicologia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import modulopsicologia.dto.ReprogramacionRequest;
import modulopsicologia.model.Cita;
import modulopsicologia.model.Reprogramacion;
import modulopsicologia.repository.CitaRepository;
import modulopsicologia.repository.ReprogramacionRepository;

@Service
public class ReprogramacionService {
    
    @Autowired
    private ReprogramacionRepository reprogramacionRepository;
    
    @Autowired
    private CitaRepository citaRepository;
    
    @Transactional
    public Reprogramacion solicitarReprogramacion(ReprogramacionRequest request){
        Cita cita = validarCita(request.getCitaId());
        actualizarEstadoCita(cita, "Reprogramacion_Pendiente");        
        return guardarSolicitud(request, cita);
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
 