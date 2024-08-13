package com.francelmofarias.vaccinectrl.rest.controllers;

import com.francelmofarias.vaccinectrl.model.ProfissionalSaude;
import com.francelmofarias.vaccinectrl.rest.dto.ProfissionalSaudeDTO;
import com.francelmofarias.vaccinectrl.service.ProfissionalSaudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/profissionais")
@Validated
public class ProfissionalSaudeController {

    @Autowired
    private ProfissionalSaudeService profissionalSaudeService;

    @GetMapping
    public ResponseEntity<List<ProfissionalSaudeDTO>> listarTodos() {
        List<ProfissionalSaudeDTO> profissionais = profissionalSaudeService.listarTodos().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalSaudeDTO> buscarPorId(@PathVariable Long id) {
        return profissionalSaudeService.obterPorId(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProfissionalSaudeDTO> salvar(@Valid @RequestBody ProfissionalSaudeDTO profissionalDTO) {
        ProfissionalSaude profissional = profissionalSaudeService.salvar(profissionalDTO);
        ProfissionalSaudeDTO dto = convertToDTO(profissional);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody ProfissionalSaudeDTO profissionalDTO) {
        profissionalSaudeService.atualizaDadosProfissional(id, profissionalDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        profissionalSaudeService.remover(id);
        return ResponseEntity.noContent().build();
    }

    private ProfissionalSaudeDTO convertToDTO(ProfissionalSaude profissional) {
        ProfissionalSaudeDTO dto = new ProfissionalSaudeDTO();
        dto.setId(profissional.getId());
        dto.setNome(profissional.getNome());
        dto.setSobrenome(profissional.getSobrenome());
        dto.setCpf(profissional.getCpf());
        dto.setRg(profissional.getRg());
        dto.setEmail(profissional.getEmail());
        dto.setContato(profissional.getContato());
        //dto.setEndereco(new EnderecoDTO(profissional.getEndereco()));
        dto.setRegistroConselho(profissional.getRegistroConselho());
        dto.setProfissao(profissional.getProfissao());
        return dto;
    }
}
