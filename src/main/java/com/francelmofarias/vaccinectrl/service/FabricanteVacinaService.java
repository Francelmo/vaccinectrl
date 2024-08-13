package com.francelmofarias.vaccinectrl.service;

import com.francelmofarias.vaccinectrl.model.FabricanteVacina;
import com.francelmofarias.vaccinectrl.rest.dto.FabricanteVacinaDTO;

import java.util.List;
import java.util.Optional;

public interface FabricanteVacinaService {
    FabricanteVacina salvar(FabricanteVacinaDTO fabricanteDTO);
    Optional<FabricanteVacina> getFabricanteById(Long id);
    List<FabricanteVacina> getAllFabricantes();
    void atualizaDadosFabricante(Long id, FabricanteVacinaDTO fabricanteDTO);
    void deletarFabricante(Long id);
}
