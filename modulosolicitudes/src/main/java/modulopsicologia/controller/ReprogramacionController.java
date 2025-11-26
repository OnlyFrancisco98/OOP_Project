package modulopsicologia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import modulopsicologia.dto.ReprogramacionRequest;
import modulopsicologia.model.Reprogramacion;
import modulopsicologia.service.ReprogramacionService;
import modulopsicologia.dto.AprobarReprogramacionRequest;

@RestController
@RequestMapping("/api/solicitudes") // Prefijo para todas las URLs de solicitudes
@CrossOrigin(origins = "*")
public class ReprogramacionController {
    @Autowired
    private ReprogramacionService reprogramacionService;

    @PostMapping("/reprogramar")
    public ResponseEntity<?> solicitarReprogramacion(@RequestBody ReprogramacionRequest request){
        try{
            Reprogramacion solicitud = reprogramacionService.solicitarReprogramacion(request);
            return new ResponseEntity<>(solicitud, HttpStatus.CREATED);
        } catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/aprobar")
    public ResponseEntity<?> aprobarReprogramacion(@RequestBody AprobarReprogramacionRequest request){
        try{
            Reprogramacion result = reprogramacionService.aprobarReprogramacion(request.getSolicitudId(), request.getNuevoHorarioId());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
