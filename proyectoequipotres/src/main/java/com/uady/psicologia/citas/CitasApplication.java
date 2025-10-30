package com.uady.psicologia.citas;

import com.uady.psicologia.citas.model.entidades.HorarioDisponible;
import com.uady.psicologia.citas.repository.HorarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List; // Asegúrate de importar List

@SpringBootApplication
public class CitasApplication {

    public static void main(String[] args) {
        SpringApplication.run(CitasApplication.class, args);
    }


    @Bean
    public CommandLineRunner loadData(HorarioRepository horarioRepository) {
        return args -> {
            
            // Horario 1
            HorarioDisponible horario1 = new HorarioDisponible();
            horario1.setHoraInicio(LocalDateTime.of(2025, 11, 15, 10, 0)); // 15-Nov-2025 a las 10:00 AM
            horario1.setDisponible(true);
            
            // Horario 2
            HorarioDisponible horario2 = new HorarioDisponible();
            horario2.setHoraInicio(LocalDateTime.of(2025, 11, 15, 11, 0)); // 15-Nov-2025 a las 11:00 AM
            horario2.setDisponible(true);

            // Horario 3
            HorarioDisponible horario3 = new HorarioDisponible();
            horario3.setHoraInicio(LocalDateTime.of(2025, 11, 15, 12, 0)); // 15-Nov-2025 a las 12:00 PM
            horario3.setDisponible(true);

            // Horario 4
            HorarioDisponible horario4 = new HorarioDisponible();
            horario4.setHoraInicio(LocalDateTime.of(2025, 11, 15, 13, 0)); // 15-Nov-2025 a las 13:00 PM
            horario4.setDisponible(true);
            
            // Guardar ambos horarios en la base de datos
            horarioRepository.saveAll(List.of(horario1, horario2, horario3, horario4));
            
        };
    }
}