package com.ibrplanner.logistica.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {
    private Long id;
    private String nome;
    private String telefone;
    private String email;

}
