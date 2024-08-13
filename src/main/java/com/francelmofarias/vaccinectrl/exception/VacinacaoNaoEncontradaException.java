package com.francelmofarias.vaccinectrl.exception;

public class VacinacaoNaoEncontradaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public VacinacaoNaoEncontradaException(String message) {
        super(message);
    }
}
