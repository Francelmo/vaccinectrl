package com.francelmofarias.vaccinectrl.exception;

public class ProfissionalNaoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProfissionalNaoEncontradoException(String message) {
        super(message);
    }
}
