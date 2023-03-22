package com.ibrplanner.logistica.dtos.outputs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DestinatarioDTO {
    private String nome;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
}
