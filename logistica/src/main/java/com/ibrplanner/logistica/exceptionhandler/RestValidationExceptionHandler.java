package com.ibrplanner.logistica.exceptionhandler;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@AllArgsConstructor
public class RestValidationExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        List<ApiValidationFieldError> camposError = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {

            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale()); // error.getDefaultMessage();

            camposError.add(new ApiValidationFieldError(nome, mensagem));
        }

        ApiValidationResponseError error = new ApiValidationResponseError();
        error.setStatus(status.value());
        error.setDataHora(LocalDateTime.now());
        error.setTitulo("Um ou mais campos foram preenchidos incorretamente.");
        error.setCampos(camposError);

        return handleExceptionInternal(ex, error, headers, status, request);
    }
}