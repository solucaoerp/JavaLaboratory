package com.ibrplanner.logistica.dtos;

import com.ibrplanner.logistica.entities.StatusEntregaEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Setter
@Getter
public class EntregaDTO {
    private Long id;
    private ClienteResumoDTO cliente;
    private DestinatarioDTO destinatario;
    private BigDecimal taxaEntrega;
    private StatusEntregaEnum status;
    private OffsetDateTime dataPedido;
}
