package com.ibrplanner.logistica.controllers.dtos;

import com.ibrplanner.logistica.entities.Cliente;
import com.ibrplanner.logistica.entities.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaDTO {
    private Long id;
    //private String nomeCliente;
    private Cliente cliente;
    private DestinatarioDTO destinatario;
    private BigDecimal taxaEntrega;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
}
