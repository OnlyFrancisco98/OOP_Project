package com.citas.psicologia.repository;

import com.citas.psicologia.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Buscar paciente por email para evitar duplicados
    Optional<Paciente> findByEmail(String email);
}