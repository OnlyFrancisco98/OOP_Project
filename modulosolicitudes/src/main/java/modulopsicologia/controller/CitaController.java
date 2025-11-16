package modulopsicologia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import modulopsicologia.dto.AgendarCitaRequest;
import modulopsicologia.dto.BuscarCitaRequest;
import modulopsicologia.model.Cita;
import modulopsicologia.service.CitaService;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping
    public ResponseEntity<?> agendarCita(@RequestBody AgendarCitaRequest request){
        try{
            Cita nuevaCita = citaService.agendarCita(request);
            return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
        } catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<Cita>> buscarMisCitas(@RequestBody BuscarCitaRequest request){
        List<Cita> citas = citaService.buscarMisCitas(request);
        return ResponseEntity.ok(citas);
    }
}
