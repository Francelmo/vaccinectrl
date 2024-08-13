package com.francelmofarias.vaccinectrl.rest.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Future;
import java.time.LocalDate;

@Data
public class FabricanteVacinaDTO {
    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "Lote é obrigatório")
    private String lote;

    @NotNull(message = "Validade é obrigatória")
    @Future(message = "A validade deve ser uma data futura")
    private LocalDate validade;

    @NotNull(message = "Quantidade é obrigatória")
    @Positive(message = "Quantidade deve ser um número positivo")
    private Integer quantidade;
}
