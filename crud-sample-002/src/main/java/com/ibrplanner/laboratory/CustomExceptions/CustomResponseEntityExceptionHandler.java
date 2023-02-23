package com.ibrplanner.laboratory.CustomExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @SuppressWarnings("unused")
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        String mensagem = "O valor informado é inválido. Por favor, informe um número válido.";
        return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    }
}
