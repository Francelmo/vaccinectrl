package com.francelmofarias.vaccinectrl.rest.dto;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
public class ProfissionalSaudeDTO {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
    private String nome;

    @NotBlank(message = "Sobrenome é obrigatório")
    @Size(min = 2, max = 50, message = "Sobrenome deve ter entre 2 e 50 caracteres")
    private String sobrenome;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos numéricos")
    private String cpf;

    @NotBlank(message = "RG é obrigatório")
    @Size(min = 7, max = 14, message = "RG deve ter entre 7 e 14 caracteres")
    private String rg;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotBlank(message = "Contato é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "Contato deve ter 10 ou 11 dígitos numéricos")
    private String contato;

    //private EnderecoDTO endereco;

    @NotBlank(message = "Registro de Conselho é obrigatório")
    @Size(min = 1, max = 20, message = "Registro de Conselho deve ter entre 1 e 20 caracteres")
    private String registroConselho;

    @NotBlank(message = "Profissão é obrigatória")
    @Size(min = 2, max = 50, message = "Profissão deve ter entre 2 e 50 caracteres")
    private String profissao;
}
