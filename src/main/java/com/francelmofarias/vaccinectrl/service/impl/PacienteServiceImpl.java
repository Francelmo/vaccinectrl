package com.francelmofarias.vaccinectrl.service.impl;

import com.francelmofarias.vaccinectrl.exception.PacienteNaoEncontradoException;
import com.francelmofarias.vaccinectrl.model.Paciente;
import com.francelmofarias.vaccinectrl.repository.PacienteRepository;
import com.francelmofarias.vaccinectrl.rest.dto.PacienteDTO;
import com.francelmofarias.vaccinectrl.service.PacienteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    @Override
    @Transactional
    public Paciente salvar(PacienteDTO dto) {
        validarPacienteDTO(dto); // Validação do DTO

        Paciente paciente = new Paciente();
        paciente.setId(dto.getId());
        paciente.setNome(dto.getNome());
        paciente.setSobrenome(dto.getSobrenome());
        paciente.setCpf(dto.getCpf());
        paciente.setRg(dto.getRg());
        paciente.setEmail(dto.getEmail());
        paciente.setContato(dto.getContato());
        //paciente.setEndereco(new Endereco(dto.getEndereco()));
        paciente.setQtdDoses(dto.getQtdDoses());
        paciente.setImunizacaoCompleta(dto.isImunizacaoCompleta());

        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> obterPacienteCompleto(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    @Transactional
    public void atualizaDadosPaciente(Long id, PacienteDTO dto) {
        validarPacienteDTO(dto);

        Paciente paciente = pacienteRepository
                .findById(id)
                .orElseThrow(() -> new PacienteNaoEncontradoException("Paciente não encontrado"));

        paciente.setNome(dto.getNome());
        paciente.setSobrenome(dto.getSobrenome());
        paciente.setCpf(dto.getCpf());
        paciente.setRg(dto.getRg());
        paciente.setEmail(dto.getEmail());
        paciente.setContato(dto.getContato());
        //paciente.setEndereco(new Endereco(dto.getEndereco()));
        paciente.setQtdDoses(dto.getQtdDoses());
        paciente.setImunizacaoCompleta(dto.isImunizacaoCompleta());

        pacienteRepository.save(paciente);
    }

    @Override
    public void deletarPaciente(Long id) {
        Paciente paciente = pacienteRepository
                .findById(id)
                .orElseThrow(() -> new PacienteNaoEncontradoException("Paciente não encontrado"));
        pacienteRepository.delete(paciente);
    }

    @Override
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    private void validarPacienteDTO(PacienteDTO dto) {
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

        if (dto.getQtdDoses() <= 0) {
            throw new IllegalArgumentException("Quantidade de doses deve ser maior que zero");
        }
    }
}
