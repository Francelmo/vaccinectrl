package com.francelmofarias.vaccinectrl.exception;

public class PacienteNaoEncontradoException extends RuntimeException {

    public PacienteNaoEncontradoException() {
        super("Paciente não encontrado.");
    }

    public PacienteNaoEncontradoException(String message) {
        super(message);
    }
}