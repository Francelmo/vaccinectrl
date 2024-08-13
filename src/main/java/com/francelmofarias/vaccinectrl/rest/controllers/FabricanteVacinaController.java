package com.francelmofarias.vaccinectrl.rest.controllers;

import com.francelmofarias.vaccinectrl.model.FabricanteVacina;
import com.francelmofarias.vaccinectrl.rest.dto.FabricanteVacinaDTO;
import com.francelmofarias.vaccinectrl.service.FabricanteVacinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/fabricantes")
@Validated
public class FabricanteVacinaController {

    @Autowired
    private FabricanteVacinaService fabricanteVacinaService;

    @GetMapping
    public ResponseEntity<List<FabricanteVacina>> listarTodos() {
        List<FabricanteVacina> fabricantes = fabricanteVacinaService.getAllFabricantes();
        return ResponseEntity.ok(fabricantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FabricanteVacina> buscarPorId(@PathVariable Long id) {
        return fabricanteVacinaService.getFabricanteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FabricanteVacina> salvar(@Valid @RequestBody FabricanteVacinaDTO fabricanteDTO) {
        FabricanteVacina novoFabricante = fabricanteVacinaService.salvar(fabricanteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFabricante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody FabricanteVacinaDTO fabricanteDTO) {
        fabricanteVacinaService.atualizaDadosFabricante(id, fabricanteDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        fabricanteVacinaService.deletarFabricante(id);
        return ResponseEntity.noContent().build();
    }
}
