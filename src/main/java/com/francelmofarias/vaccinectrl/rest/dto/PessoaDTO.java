package com.francelmofarias.vaccinectrl.rest.dto;

import lombok.Data;

@Data
public class PessoaDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String rg;
    private String email;
    private String contato;
    private EnderecoDTO endereco;
}
