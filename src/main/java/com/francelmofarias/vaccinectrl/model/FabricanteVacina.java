package com.francelmofarias.vaccinectrl.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class FabricanteVacina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String lote;
    private LocalDate validade;
    private int quantidade;
}

