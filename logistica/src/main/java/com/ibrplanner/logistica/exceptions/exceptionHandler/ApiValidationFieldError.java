package com.ibrplanner.logistica.exceptions.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiValidationFieldError {
    private String campo;
    private String mensagem;
}
