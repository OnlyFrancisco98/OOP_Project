package com.uady.psicologia.citas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uady.psicologia.citas.model.entidades.HorarioDisponible;
import com.uady.psicologia.citas.repository.HorarioRepository;
import java.util.List;

@Service
public class CitaServicio {

    @Autowired private HorarioRepository horarioRepo;
    
    public List<HorarioDisponible> getHorariosDisponibles() {
        return horarioRepo.findByDisponibleTrue();
    }

}
