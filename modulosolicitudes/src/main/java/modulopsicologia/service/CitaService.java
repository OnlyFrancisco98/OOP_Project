package modulopsicologia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import modulopsicologia.dto.AgendarCitaRequest;
import modulopsicologia.dto.BuscarCitaRequest;
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

    @Transactional
    public Cita agendarCita(AgendarCitaRequest request){
        Paciente paciente = pacienteService.crearPaciente(request);
        Horario horario = horarioService.validarHorario(request.getHorarioId());
        Cita citaGuardada = crearCita(request, paciente, horario);
        horarioService.marcarHorarioComoOcupado(horario);
        return citaGuardada;
    }

    private Cita crearCita(AgendarCitaRequest request, Paciente paciente, Horario horario){
        Cita nuevaCita = new Cita();
        nuevaCita.setPaciente(paciente);
        nuevaCita.setHorario(horario);
        nuevaCita.setMotivoConsulta(request.getMotivoConsulta());
        return citaRepository.save(nuevaCita);
    }

    public List<Cita> buscarMisCitas(BuscarCitaRequest request){
        return citaRepository.findByPaciente_EmailAndPaciente_Nombre(request.getEmail(), request.getNombre());
    }

    public Cita encontrarCitaPorId(Long id){
        Optional<Cita> optionalCita = citaRepository.findById(id);
        if(optionalCita.isPresent()){
            return optionalCita.get();
        } else {
            throw new RuntimeException("Cita no encontrada con ID: " + id);
        }
    }
}
