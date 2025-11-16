package modulopsicologia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import modulopsicologia.model.Horario;
import modulopsicologia.service.HorarioService;

@RestController
@RequestMapping("/api/horarios")
@CrossOrigin(origins = "*")
public class HorarioController {
    @Autowired
    private HorarioService horarioService;

    @GetMapping("/disponibles") 
    public ResponseEntity<List<Horario>> getHorariosDisponibles() {
        List<Horario> horarios = horarioService.getHorariosDisponibles();
        return ResponseEntity.ok(horarios);
    }
}
