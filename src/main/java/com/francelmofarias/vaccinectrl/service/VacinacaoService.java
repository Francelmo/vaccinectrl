package com.francelmofarias.vaccinectrl.service;

import com.francelmofarias.vaccinectrl.model.Vacinacao;
import com.francelmofarias.vaccinectrl.rest.dto.VacinacaoDTO;

import java.util.List;
import java.util.Optional;

public interface VacinacaoService {
    Vacinacao salvar(VacinacaoDTO vacinacaoDTO);
    Optional<Vacinacao> obterPorId(Long id);
    List<Vacinacao> listarTodos();
    void remover(Long id);
    void atualizaDadosVacinacao(Long id, VacinacaoDTO vacinacaoDTO); // Adicione esta linha
}
