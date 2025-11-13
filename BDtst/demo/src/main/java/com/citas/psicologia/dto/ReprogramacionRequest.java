package com.citas.psicologia.dto;
import lombok.Data;

@Data
public class ReprogramacionRequest {
    private Long citaId;
    private String motivo;
    private String explicacion;
}