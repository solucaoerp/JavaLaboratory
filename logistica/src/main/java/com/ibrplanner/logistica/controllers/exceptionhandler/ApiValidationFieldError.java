package com.ibrplanner.logistica.controllers.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiValidationFieldError {
    private String campo;
    private String mensagem;
}
