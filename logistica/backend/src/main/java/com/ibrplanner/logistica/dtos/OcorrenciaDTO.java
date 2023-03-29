package com.ibrplanner.logistica.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
public class OcorrenciaDTO {
    private Long id;
    private String descricao;
    private OffsetDateTime dataRegistro;
}
