package com.ibrplanner.logistica.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Destinatario {
    @Column(name = "destino_nome")
    private String nome;
    @Column(name = "destino_logradouro")
    private String logradouro;
    @Column(name = "destino_numero")
    private String numero;
    @Column(name = "destino_complemento")
    private String complemento;
    @Column(name = "destino_bairro")
    private String bairro;
}
