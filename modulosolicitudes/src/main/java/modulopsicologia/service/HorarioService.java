package modulopsicologia.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import modulopsicologia.model.Horario;
import modulopsicologia.repository.HorarioRepository;

@Service
public class HorarioService {
    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> getHorariosDisponibles(){
        return horarioRepository.findByEstaDisponibleTrueAndFechaHoraInicioAfter(OffsetDateTime.now());
    }

    public Horario validarHorario(Long horarioId){
        Optional<Horario> optionalHorario = horarioRepository.findById(horarioId);
        Horario horario;
        if(optionalHorario.isPresent()){
            horario = optionalHorario.get();
        } else {
            throw new RuntimeException("Horario no encontrado con ID: " + horarioId);
        }

        if (!horario.isEstaDisponible()) {
            throw new RuntimeException("El horario seleccionado no está disponible");
        }

        return horario;
    }

    public void marcarHorarioComoOcupado(Horario horario){
        horario.setEstaDisponible(false);
        horarioRepository.save(horario);
    }
}
