package com.ibrplanner.logistica.dtos.inputs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class EntregaInput {
    @Valid
    @NotNull
    private ClienteInput cliente;
    @Valid
    @NotNull
    private DestinatarioInput destinatario;
    @NotNull
    private BigDecimal taxa;
}
