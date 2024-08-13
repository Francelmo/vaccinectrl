package com.francelmofarias.vaccinectrl.rest.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Future;
import java.time.LocalDate;

@Data
public class VacinacaoDTO {
    private Long id;

    @NotNull(message = "ID do paciente é obrigatório")
    @Positive(message = "ID do paciente deve ser um número positivo")
    private Long pacienteId;

    @NotNull(message = "ID do profissional de saúde é obrigatório")
    @Positive(message = "ID do profissional de saúde deve ser um número positivo")
    private Long profissionalSaudeId;

    @NotNull(message = "ID do endereço é obrigatório")
    @Positive(message = "ID do endereço deve ser um número positivo")
    private Long enderecoId;

    @NotNull(message = "Data de aplicação é obrigatória")
    private LocalDate dataAplicacao;

    @NotNull(message = "Dose é obrigatória")
    @Positive(message = "Dose deve ser um número positivo")
    private Long dose;
}
