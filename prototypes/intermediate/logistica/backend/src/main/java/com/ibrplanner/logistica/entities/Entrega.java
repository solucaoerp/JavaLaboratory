package com.ibrplanner.logistica.entities;

import com.ibrplanner.logistica.exceptions.exceptionService.ExceptionService;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private StatusEntregaEnum status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;

    public Ocorrencia adicionarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this); /* relaciona a ocorrencia à própria entrega */

        this.getOcorrencias().add(ocorrencia);
        return ocorrencia;
    }

    public void finalizar() {
        if (naoPodeSerFinalizada()) {
            throw new ExceptionService("Entrega não pode ser finalizada.");
        }
        setStatus(StatusEntregaEnum.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }

    public boolean podeSerFinalizada() {
        return StatusEntregaEnum.PENDENTE.equals(getStatus());
    }

    public boolean naoPodeSerFinalizada() {
        return !podeSerFinalizada();
    }
}
