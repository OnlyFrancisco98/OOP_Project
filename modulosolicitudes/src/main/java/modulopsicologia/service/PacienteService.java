package modulopsicologia.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modulopsicologia.dto.AgendarCitaRequest;
import modulopsicologia.model.Paciente;
import modulopsicologia.repository.PacienteRepository;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente crearPaciente(AgendarCitaRequest request){
        Optional<Paciente> optionalPaciente = pacienteRepository.findByEmail(request.getEmail());
        if(optionalPaciente.isPresent()){
            return optionalPaciente.get();
        } else {
            Paciente nuevoPaciente = new Paciente();
            nuevoPaciente.setNombre(request.getNombre());
            nuevoPaciente.setEmail(request.getEmail());
            nuevoPaciente.setTelefono(request.getTelefono());
            nuevoPaciente.setFechaNacimiento(request.getFechaNacimiento());
            return pacienteRepository.save(nuevoPaciente);
        }
    }
}
