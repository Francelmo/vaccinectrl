package com.francelmofarias.vaccinectrl.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Paciente extends Pessoa {
    private int qtdDoses;
    private boolean imunizacaoCompleta;
}
