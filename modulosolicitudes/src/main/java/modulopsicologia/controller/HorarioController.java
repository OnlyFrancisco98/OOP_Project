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
import modulopsicologia.dto.HorarioResponse;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/horarios")
@CrossOrigin(origins = "*")
public class HorarioController {
    @Autowired
    private HorarioService horarioService;

    @GetMapping("/disponibles") 
    public ResponseEntity<List<HorarioResponse>> getHorariosDisponibles() {
        List<Horario> horarios = horarioService.getHorariosDisponibles();
        // Map to DTO with ISO string date to avoid JS invalid date
        List<HorarioResponse> dtos = horarios.stream().map(h -> {
            HorarioResponse r = new HorarioResponse();
            r.setHorarioId(h.getHorarioId());
            r.setFechaHoraInicio(h.getFechaHoraInicio() == null ? null : h.getFechaHoraInicio().toString());
            r.setDuracionMinutos(h.getDuracionMinutos());
            r.setEstaDisponible(h.isEstaDisponible());
            return r;
        }).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtos);
    }
}
