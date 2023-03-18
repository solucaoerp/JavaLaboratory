package com.ibrplanner.logistica.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ApiValidationResponseError {
    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;
    private List<ApiValidationFieldError> campos;
}
