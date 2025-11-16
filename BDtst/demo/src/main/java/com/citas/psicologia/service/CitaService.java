package com.citas.psicologia.service;

import com.citas.psicologia.dto.*;
import com.citas.psicologia.entity.*;
import com.citas.psicologia.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class CitaService {

    @Autowired private PacienteRepository pacienteRepository;
    @Autowired private HorarioRepository horarioRepository;
    @Autowired private CitaRepository citaRepository;
    @Autowired private SolicitudRepository solicitudRepository;
    /**
     * Muestra todos los horarios que están disponibles y son en el futuro.
     */
    public List<Horario> getHorariosDisponibles() {
        return horarioRepository.findByEstaDisponibleTrueAndFechaHoraInicioAfter(OffsetDateTime.now());
    }

    /**
     * Lógica clave: Agendar una cita.
     * La anotación @Transactional asegura que si algo falla,
     * no se guarda ni la cita NI el cambio en el horario (hace rollback).
     */
    @Transactional
    public Cita agendarCita(AgendarCitaRequest request) {
        
        // 1. Buscar o crear al paciente
        // .orElseGet() es una forma limpia de:
        //   "Busca por email. Si no lo encuentras, ejecuta el código 
        //    para crear uno nuevo y devuélvelo."
        Paciente paciente = pacienteRepository.findByEmail(request.getEmail())
            .orElseGet(() -> {
                Paciente nuevoPaciente = new Paciente();
                nuevoPaciente.setNombre(request.getNombre());
                nuevoPaciente.setEmail(request.getEmail());
                nuevoPaciente.setTelefono(request.getTelefono());
                nuevoPaciente.setFechaNacimiento(request.getFechaNacimiento());
                // Guardamos y retornamos el nuevo paciente
                return pacienteRepository.save(nuevoPaciente);
            });
        
        // (Opcional: Si el paciente ya existía, podrías querer 
        // actualizar su nombre o teléfono si son diferentes.
        // paciente.setNombre(request.getNombre());
        // paciente.setTelefono(request.getTelefono());
        // pacienteRepository.save(paciente);
        // )

        // 2. Validar que el horario exista y esté disponible
        Horario horario = horarioRepository.findById(request.getHorarioId())
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        if (!horario.isEstaDisponible()) {
            throw new RuntimeException("El horario seleccionado ya no está disponible.");
        }

        // 3. Marcar el horario como NO disponible
        horario.setEstaDisponible(false);
        
        // 4. Crear la nueva cita
        Cita nuevaCita = new Cita();
        nuevaCita.setPaciente(paciente);
        nuevaCita.setHorario(horario);
        nuevaCita.setMotivoConsulta(request.getMotivoConsulta());
        nuevaCita.setEstadoCita("Agendada");

        try {
            // 5. Guardar los cambios (horario y cita)
            horarioRepository.save(horario);
            return citaRepository.save(nuevaCita);
            
        } catch (DataIntegrityViolationException e) {
            // Esto captura si alguien más agendó al mismo tiempo
            throw new RuntimeException("Error: El horario acaba de ser tomado. Intenta con otro.", e);
        }
    }

    /**
     * Busca las citas programadas de un paciente.
     */
    public List<Cita> buscarMisCitas(BuscarCitasRequest request) {
        return citaRepository.findByPacienteEmailAndPacienteNombre(request.getEmail(), request.getNombre());
    }

    /**
     * Crea una solicitud de reprogramación.
     */
    public SolicitudReprogramacion solicitarReprogramacion(ReprogramacionRequest request) {
        
        // 1. Validar que la cita exista
        Cita cita = citaRepository.findById(request.getCitaId())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        // 2. (Opcional) Cambiar el estado de la cita
        cita.setEstadoCita("Reprogramacion_Pendiente");
        citaRepository.save(cita);

        // 3. Crear y guardar la solicitud
        SolicitudReprogramacion solicitud = new SolicitudReprogramacion();
        solicitud.setCita(cita);
        solicitud.setMotivo(request.getMotivo());
        solicitud.setExplicacion(request.getExplicacion());
        // El resto de campos (estado, respuesta) toman sus valores por defecto.

        return solicitudRepository.save(solicitud);
    }
}