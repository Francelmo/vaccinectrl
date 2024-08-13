package com.francelmofarias.vaccinectrl.service;

import com.francelmofarias.vaccinectrl.model.ProfissionalSaude;
import com.francelmofarias.vaccinectrl.rest.dto.ProfissionalSaudeDTO;

import java.util.List;
import java.util.Optional;

public interface ProfissionalSaudeService {
    ProfissionalSaude salvar(ProfissionalSaudeDTO profissionalSaudeDTO);
    Optional<ProfissionalSaude> obterPorId(Long id);
    List<ProfissionalSaude> listarTodos();
    void remover(Long id);
    void atualizaDadosProfissional(Long id, ProfissionalSaudeDTO profissionalSaudeDTO);
}
