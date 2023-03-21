package com.ibrplanner.logistica.exceptions.exceptionService;

public class ExceptionService extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExceptionService(String mensagem) {
        super(mensagem);
    }

}
