package com.francelmofarias.vaccinectrl.exception;

public class FabricanteVacinaNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FabricanteVacinaNaoEncontradoException(String message) {
        super(message);
    }
}
