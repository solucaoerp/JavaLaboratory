package com.ibrplanner.logistica.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Destinatario {
    @NotBlank
    @Column(name = "destino_nome")
    private String nome;
    @NotBlank
    @Column(name = "destino_logradouro")
    private String logradouro;
    @NotBlank
    @Column(name = "destino_numero")
    private String numero;
    @NotBlank
    @Column(name = "destino_complemento")
    private String complemento;
    @NotBlank
    @Column(name = "destino_bairro")
    private String bairro;
}
