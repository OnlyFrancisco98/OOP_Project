package com.citas.psicologia.repository;

import com.citas.psicologia.entity.SolicitudReprogramacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository<SolicitudReprogramacion, Long> {
    // No necesitamos métodos custom por ahora
}