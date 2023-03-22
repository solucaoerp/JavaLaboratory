package com.ibrplanner.logistica.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Entity
public class Entrega {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cliente cliente;
    @Embedded
    private Destinatario destinatario;
    private BigDecimal taxaEntrega;
    @Enumerated(EnumType.STRING)
    private StatusEntregaEnum status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
}
