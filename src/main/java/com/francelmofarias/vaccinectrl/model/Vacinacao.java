package com.francelmofarias.vaccinectrl.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Vacinacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fabricante_vacina_id")
    private FabricanteVacina vacina;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "profissional_saude_id")
    private ProfissionalSaude profissionalSaude;

    private LocalDate dataAplicacao;
    private Long dose;

    @Embedded
    private Endereco local;
}
