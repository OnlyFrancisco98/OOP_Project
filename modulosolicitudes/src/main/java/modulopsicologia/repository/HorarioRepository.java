package modulopsicologia.repository;

import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import modulopsicologia.model.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
    
    //EstaDisponible = TRUE y fechaHoraInicio > TiempoActual
    List<Horario> findByEstaDisponibleTrueAndFechaHoraInicioAfter(OffsetDateTime now);
    
}
