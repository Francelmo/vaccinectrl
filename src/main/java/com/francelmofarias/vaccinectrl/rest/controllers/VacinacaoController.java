package com.francelmofarias.vaccinectrl.rest.controllers;

import com.francelmofarias.vaccinectrl.model.Vacinacao;
import com.francelmofarias.vaccinectrl.rest.dto.VacinacaoDTO;
import com.francelmofarias.vaccinectrl.service.VacinacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vacinacoes")
@Validated
public class VacinacaoController {

    @Autowired
    private VacinacaoService vacinacaoService;

    @GetMapping
    public ResponseEntity<List<VacinacaoDTO>> listarTodos() {
        List<VacinacaoDTO> vacinacoes = vacinacaoService.listarTodos().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vacinacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacinacaoDTO> buscarPorId(@PathVariable Long id) {
        return vacinacaoService.obterPorId(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VacinacaoDTO> salvar(@Valid @RequestBody VacinacaoDTO vacinacaoDTO) {
        Vacinacao vacinacao = vacinacaoService.salvar(vacinacaoDTO);
        VacinacaoDTO dto = convertToDTO(vacinacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody VacinacaoDTO vacinacaoDTO) {
        vacinacaoService.atualizaDadosVacinacao(id, vacinacaoDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vacinacaoService.remover(id);
        return ResponseEntity.noContent().build();
    }

    private VacinacaoDTO convertToDTO(Vacinacao vacinacao) {
        VacinacaoDTO dto = new VacinacaoDTO();
        dto.setId(vacinacao.getId());
        dto.setPacienteId(vacinacao.getPaciente().getId());
        dto.setProfissionalSaudeId(vacinacao.getProfissionalSaude().getId());
        //dto.setEnderecoId(vacinacao.getEndereco().getId());
        dto.setDataAplicacao(vacinacao.getDataAplicacao());
        dto.setDose(vacinacao.getDose());
        return dto;
    }
}
