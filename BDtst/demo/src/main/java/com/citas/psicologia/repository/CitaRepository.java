package com.citas.psicologia.repository;

import com.citas.psicologia.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    // Query custom: Buscar citas por el email y nombre del paciente
    List<Cita> findByPacienteEmailAndPacienteNombre(String email, String nombre);
}