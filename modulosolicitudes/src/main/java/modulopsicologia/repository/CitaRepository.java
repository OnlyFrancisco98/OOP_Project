package modulopsicologia.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import modulopsicologia.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long>{

    // List<Cita> findByPaciente_EmailAndPaciente_Nombre(String email, String nombre);

    // Forzar la forma manual
    @Query("SELECT c FROM Cita c WHERE c.paciente.email = :email AND c.paciente.nombre = :nombre")
    List<Cita> buscarPorEmailYNombreDePaciente(
        @Param("email") String email, 
        @Param("nombre") String nombre
    );

    
}
