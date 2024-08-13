package com.francelmofarias.vaccinectrl.service;

import com.francelmofarias.vaccinectrl.model.Paciente;
import com.francelmofarias.vaccinectrl.rest.dto.PacienteDTO;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    Paciente salvar(PacienteDTO dto);

    Optional<Paciente> obterPacienteCompleto(Long id);

    void atualizaDadosPaciente(Long id, PacienteDTO dto);

    void deletarPaciente(Long id);

    List<Paciente> getAllPacientes();
}
