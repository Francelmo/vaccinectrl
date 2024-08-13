package com.francelmofarias.vaccinectrl.service.impl;

import com.francelmofarias.vaccinectrl.exception.ProfissionalNaoEncontradoException;
import com.francelmofarias.vaccinectrl.model.ProfissionalSaude;
import com.francelmofarias.vaccinectrl.repository.ProfissionalSaudeRepository;
import com.francelmofarias.vaccinectrl.rest.dto.ProfissionalSaudeDTO;
import com.francelmofarias.vaccinectrl.service.ProfissionalSaudeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ProfissionalSaudeServiceImpl implements ProfissionalSaudeService {

    private final ProfissionalSaudeRepository repository;

    @Override
    public ProfissionalSaude salvar(ProfissionalSaudeDTO dto) {
        validarProfissionalSaudeDTO(dto);

        ProfissionalSaude profissional = new ProfissionalSaude();
        profissional.setId(dto.getId());
        profissional.setNome(dto.getNome());
        profissional.setSobrenome(dto.getSobrenome());
        profissional.setCpf(dto.getCpf());
        profissional.setRg(dto.getRg());
        profissional.setEmail(dto.getEmail());
        profissional.setContato(dto.getContato());
        //profissional.setEndereco(new Endereco(dto.getEndereco()));
        profissional.setRegistroConselho(dto.getRegistroConselho());
        profissional.setProfissao(dto.getProfissao());

        return repository.save(profissional);
    }

    @Override
    public Optional<ProfissionalSaude> obterPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ProfissionalSaude> listarTodos() {
        return repository.findAll();
    }

    @Override
    public void remover(Long id) {
        ProfissionalSaude profissional = repository
                .findById(id)
                .orElseThrow(() -> new ProfissionalNaoEncontradoException("Profissional não encontrado"));
        repository.delete(profissional);
    }

    @Override
    public void atualizaDadosProfissional(Long id, ProfissionalSaudeDTO dto) {
        validarProfissionalSaudeDTO(dto); // Validação do DTO

        ProfissionalSaude profissional = repository
                .findById(id)
                .orElseThrow(() -> new ProfissionalNaoEncontradoException("Profissional não encontrado"));

        profissional.setNome(dto.getNome());
        profissional.setSobrenome(dto.getSobrenome());
        profissional.setCpf(dto.getCpf());
        profissional.setRg(dto.getRg());
        profissional.setEmail(dto.getEmail());
        profissional.setContato(dto.getContato());
        //profissional.setEndereco(new Endereco(dto.getEndereco()));
        profissional.setRegistroConselho(dto.getRegistroConselho());
        profissional.setProfissao(dto.getProfissao());

        repository.save(profissional);
    }

    private void validarProfissionalSaudeDTO(ProfissionalSaudeDTO dto) {
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        if (dto.getSobrenome() == null || dto.getSobrenome().isBlank()) {
            throw new IllegalArgumentException("Sobrenome é obrigatório");
        }

        if (dto.getCpf() == null || !Pattern.matches("\\d{11}", dto.getCpf())) {
            throw new IllegalArgumentException("CPF deve ter 11 dígitos numéricos");
        }

        if (dto.getRg() == null || dto.getRg().isBlank()) {
            throw new IllegalArgumentException("RG é obrigatório");
        }

        if (dto.getEmail() == null || !Pattern.matches("^.+@.+\\..+$", dto.getEmail())) {
            throw new IllegalArgumentException("Email deve ser válido");
        }

        if (dto.getContato() == null || !Pattern.matches("\\d{10,11}", dto.getContato())) {
            throw new IllegalArgumentException("Contato deve ter 10 ou 11 dígitos numéricos");
        }

        if (dto.getRegistroConselho() == null || dto.getRegistroConselho().isBlank()) {
            throw new IllegalArgumentException("Registro de Conselho é obrigatório");
        }

        if (dto.getProfissao() == null || dto.getProfissao().isBlank()) {
            throw new IllegalArgumentException("Profissão é obrigatória");
        }
    }
}
