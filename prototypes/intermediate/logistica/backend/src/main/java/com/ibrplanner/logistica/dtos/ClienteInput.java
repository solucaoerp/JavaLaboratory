package com.ibrplanner.logistica.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInput {
    @NotNull
    private Long id;

}
