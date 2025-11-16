package modulopsicologia.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import modulopsicologia.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long>{

    List<Cita> findByPaciente_EmailAndPaciente_Nombre(String email, String nombre);

    
}
