package com.francelmofarias.vaccinectrl.service.impl;

import com.francelmofarias.vaccinectrl.exception.PacienteNaoEncontradoException;
import com.francelmofarias.vaccinectrl.exception.ProfissionalNaoEncontradoException;
import com.francelmofarias.vaccinectrl.exception.VacinacaoNaoEncontradaException;
import com.francelmofarias.vaccinectrl.model.Paciente;
import com.francelmofarias.vaccinectrl.model.ProfissionalSaude;
import com.francelmofarias.vaccinectrl.model.Vacinacao;
import com.francelmofarias.vaccinectrl.repository.PacienteRepository;
import com.francelmofarias.vaccinectrl.repository.ProfissionalSaudeRepository;
import com.francelmofarias.vaccinectrl.repository.VacinacaoRepository;
import com.francelmofarias.vaccinectrl.rest.dto.VacinacaoDTO;
import com.francelmofarias.vaccinectrl.service.VacinacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VacinacaoServiceImpl implements VacinacaoService {

    private final VacinacaoRepository vacinacaoRepository;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalSaudeRepository profissionalSaudeRepository;

    @Override
    public Vacinacao salvar(VacinacaoDTO vacinacaoDTO) {
        validarVacinacaoDTO(vacinacaoDTO);

        Paciente paciente = pacienteRepository.findById(vacinacaoDTO.getPacienteId())
                .orElseThrow(() -> new PacienteNaoEncontradoException("Paciente não encontrado"));

        ProfissionalSaude profissionalSaude = profissionalSaudeRepository.findById(vacinacaoDTO.getProfissionalSaudeId())
                .orElseThrow(() -> new ProfissionalNaoEncontradoException("Profissional de saúde não encontrado"));

        Vacinacao vacinacao = new Vacinacao();
        vacinacao.setDataAplicacao(vacinacaoDTO.getDataAplicacao());
        vacinacao.setPaciente(paciente);
        vacinacao.setProfissionalSaude(profissionalSaude);
        vacinacao.setDose(vacinacaoDTO.getDose());

        return vacinacaoRepository.save(vacinacao);
    }

    @Override
    public Optional<Vacinacao> obterPorId(Long id) {
        return vacinacaoRepository.findById(id);
    }

    @Override
    public List<Vacinacao> listarTodos() {
        return vacinacaoRepository.findAll();
    }

    @Override
    public void remover(Long id) {
        if (!vacinacaoRepository.existsById(id)) {
            throw new VacinacaoNaoEncontradaException("Vacinação não encontrada");
        }
        vacinacaoRepository.deleteById(id);
    }

    @Override
    public void atualizaDadosVacinacao(Long id, VacinacaoDTO vacinacaoDTO) {
        validarVacinacaoDTO(vacinacaoDTO);

        Vacinacao vacinacao = vacinacaoRepository.findById(id)
                .orElseThrow(() -> new VacinacaoNaoEncontradaException("Vacinação não encontrada"));

        Paciente paciente = pacienteRepository.findById(vacinacaoDTO.getPacienteId())
                .orElseThrow(() -> new PacienteNaoEncontradoException("Paciente não encontrado"));

        ProfissionalSaude profissionalSaude = profissionalSaudeRepository.findById(vacinacaoDTO.getProfissionalSaudeId())
                .orElseThrow(() -> new ProfissionalNaoEncontradoException("Profissional de saúde não encontrado"));

        vacinacao.setPaciente(paciente);
        vacinacao.setProfissionalSaude(profissionalSaude);
        vacinacao.setDataAplicacao(vacinacaoDTO.getDataAplicacao());
        vacinacao.setDose(vacinacaoDTO.getDose());

        vacinacaoRepository.save(vacinacao);
    }

    private void validarVacinacaoDTO(VacinacaoDTO dto) {
        if (dto.getDataAplicacao() == null || dto.getDataAplicacao().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de aplicação deve ser uma data passada ou presente");
        }

        if (dto.getPacienteId() == null) {
            throw new IllegalArgumentException("ID do paciente é obrigatório");
        }

        if (dto.getProfissionalSaudeId() == null) {
            throw new IllegalArgumentException("ID do profissional de saúde é obrigatório");
        }

        if (dto.getDose() == null) {
            throw new IllegalArgumentException("Dose é obrigatória");
        }
    }
}
