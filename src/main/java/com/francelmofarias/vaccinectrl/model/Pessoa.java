package com.francelmofarias.vaccinectrl.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sobrenome;
    private String cpf;
    private String rg;
    private String email;
    private String contato;

    @Embedded
    private Endereco endereco;
}

