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
            // Generate and store a temporary password at first creation
            String temp = generarContrasenaTemporal();
            nuevoPaciente.setContrasenaTemporal(temp);
            return pacienteRepository.save(nuevoPaciente);
        }
    }

    public String asegurarContrasenaTemporal(Paciente paciente){
        if(paciente.getContrasenaTemporal() == null || paciente.getContrasenaTemporal().isBlank()){
            String temp = generarContrasenaTemporal();
            paciente.setContrasenaTemporal(temp);
            pacienteRepository.save(paciente);
            return temp;
        }
        return paciente.getContrasenaTemporal();
    }

    private String generarContrasenaTemporal(){
        // Generador simple seguro usando SecureRandom
        java.security.SecureRandom sr = new java.security.SecureRandom();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%&*-_";
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<12;i++){
            int idx = sr.nextInt(chars.length());
            sb.append(chars.charAt(idx));
        }
        return sb.toString();
    }
}
