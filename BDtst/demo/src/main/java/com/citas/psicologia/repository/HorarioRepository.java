package com.citas.psicologia.repository;

import com.citas.psicologia.entity.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.OffsetDateTime;
import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    // Query custom: Buscar horarios disponibles después de la fecha/hora actual
    List<Horario> findByEstaDisponibleTrueAndFechaHoraInicioAfter(OffsetDateTime ahora);
}