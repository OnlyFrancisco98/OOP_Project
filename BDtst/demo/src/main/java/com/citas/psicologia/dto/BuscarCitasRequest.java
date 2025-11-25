package com.citas.psicologia.dto;
import lombok.Data;

@Data
public class BuscarCitasRequest {
    private String nombre;
    private String email;
}