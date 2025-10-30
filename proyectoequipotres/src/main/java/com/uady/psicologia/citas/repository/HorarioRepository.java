package com.uady.psicologia.citas.repository;
import com.uady.psicologia.citas.model.entidades.HorarioDisponible;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HorarioRepository extends JpaRepository<HorarioDisponible, Long> {
    List<HorarioDisponible> findByDisponibleTrue();

}
