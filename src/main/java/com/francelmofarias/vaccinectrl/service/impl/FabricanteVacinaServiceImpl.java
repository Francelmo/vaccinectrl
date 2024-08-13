package com.francelmofarias.vaccinectrl.service.impl;

import com.francelmofarias.vaccinectrl.exception.FabricanteVacinaNaoEncontradoException;
import com.francelmofarias.vaccinectrl.model.FabricanteVacina;
import com.francelmofarias.vaccinectrl.repository.FabricanteVacinaRepository;
import com.francelmofarias.vaccinectrl.rest.dto.FabricanteVacinaDTO;
import com.francelmofarias.vaccinectrl.service.FabricanteVacinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FabricanteVacinaServiceImpl implements FabricanteVacinaService {

    private final FabricanteVacinaRepository fabricanteVacinaRepository;

    @Override
    public FabricanteVacina salvar(FabricanteVacinaDTO fabricanteDTO) {
        validarFabricanteVacinaDTO(fabricanteDTO);

        FabricanteVacina fabricanteVacina = new FabricanteVacina();
        fabricanteVacina.setDescricao(fabricanteDTO.getDescricao());
        fabricanteVacina.setLote(fabricanteDTO.getLote());
        fabricanteVacina.setValidade(fabricanteDTO.getValidade());
        fabricanteVacina.setQuantidade(fabricanteDTO.getQuantidade());

        return fabricanteVacinaRepository.save(fabricanteVacina);
    }

    @Override
    public Optional<FabricanteVacina> getFabricanteById(Long id) {
        return fabricanteVacinaRepository.findById(id)
                .or(() -> {
                    throw new FabricanteVacinaNaoEncontradoException("Fabricante não encontrado com ID: " + id);
                });
    }

    @Override
    public List<FabricanteVacina> getAllFabricantes() {
        return fabricanteVacinaRepository.findAll();
    }

    @Override
    public void atualizaDadosFabricante(Long id, FabricanteVacinaDTO fabricanteDTO) {
        validarFabricanteVacinaDTO(fabricanteDTO);

        FabricanteVacina fabricanteVacina = fabricanteVacinaRepository.findById(id)
                .orElseThrow(() -> new FabricanteVacinaNaoEncontradoException("Fabricante não encontrado com ID: " + id));

        fabricanteVacina.setDescricao(fabricanteDTO.getDescricao());
        fabricanteVacina.setLote(fabricanteDTO.getLote());
        fabricanteVacina.setValidade(fabricanteDTO.getValidade());
        fabricanteVacina.setQuantidade(fabricanteDTO.getQuantidade());

        fabricanteVacinaRepository.save(fabricanteVacina);
    }

    @Override
    public void deletarFabricante(Long id) {
        if (!fabricanteVacinaRepository.existsById(id)) {
            throw new FabricanteVacinaNaoEncontradoException("Fabricante não encontrado com ID: " + id);
        }
        fabricanteVacinaRepository.deleteById(id);
    }

    private void validarFabricanteVacinaDTO(FabricanteVacinaDTO dto) {
        if (dto.getDescricao() == null || dto.getDescricao().isBlank()) {
            throw new IllegalArgumentException("Descrição é obrigatória");
        }

        if (dto.getLote() == null || dto.getLote().isBlank()) {
            throw new IllegalArgumentException("Lote é obrigatório");
        }

        if (dto.getValidade() == null || dto.getValidade().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Validade deve ser uma data futura");
        }

        if (dto.getQuantidade() == null || dto.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser um número positivo");
        }
    }
}
