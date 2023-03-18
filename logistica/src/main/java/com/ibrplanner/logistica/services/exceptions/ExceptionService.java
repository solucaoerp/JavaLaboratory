package com.ibrplanner.logistica.services.exceptions;

public class ExceptionService extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExceptionService(String mensagem) {
        super(mensagem);
    }

}
