package com.ibrplanner.logistica.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ApiValidationResponseError {
    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;
    private List<ApiValidationFieldError> campos;
}
