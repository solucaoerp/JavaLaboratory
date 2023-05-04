package com.ibrplanner.logistica.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaInput {
    /* como deve vir na requisição */
    @NotBlank
    private String descricao;
}
