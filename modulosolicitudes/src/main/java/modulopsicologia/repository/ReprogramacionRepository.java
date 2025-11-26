package modulopsicologia.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import modulopsicologia.model.Reprogramacion;


@Repository
public interface ReprogramacionRepository extends JpaRepository<Reprogramacion, Long>{
    
}
