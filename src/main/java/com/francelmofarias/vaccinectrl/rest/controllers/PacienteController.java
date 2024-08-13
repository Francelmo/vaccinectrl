package com.francelmofarias.vaccinectrl.rest.controllers;

import com.francelmofarias.vaccinectrl.model.Paciente;
import com.francelmofarias.vaccinectrl.rest.dto.PacienteDTO;
import com.francelmofarias.vaccinectrl.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pacientes")
@Validated
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> getAllPacientes() {
        List<PacienteDTO> pacientes = pacienteService.getAllPacientes().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getPacienteById(@PathVariable Long id) {
        return pacienteService.obterPacienteCompleto(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PacienteDTO> savePaciente(@Valid @RequestBody PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteService.salvar(pacienteDTO);
        PacienteDTO dto = convertToDTO(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePaciente(@PathVariable Long id, @Valid @RequestBody PacienteDTO pacienteDTO) {
        pacienteService.atualizaDadosPaciente(id, pacienteDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteService.deletarPaciente(id);
        return ResponseEntity.noContent().build();
    }

    private PacienteDTO convertToDTO(Paciente paciente) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.getId());
        dto.setNome(paciente.getNome());
        dto.setSobrenome(paciente.getSobrenome());
        dto.setCpf(paciente.getCpf());
        dto.setRg(paciente.getRg());
        dto.setEmail(paciente.getEmail());
        dto.setContato(paciente.getContato());
        //dto.setEndereco(new EnderecoDTO(paciente.getEndereco()));
        dto.setQtdDoses(paciente.getQtdDoses());
        dto.setImunizacaoCompleta(paciente.isImunizacaoCompleta());
        return dto;
    }
}
