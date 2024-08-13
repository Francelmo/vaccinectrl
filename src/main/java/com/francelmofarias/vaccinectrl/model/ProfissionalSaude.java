package com.francelmofarias.vaccinectrl.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ProfissionalSaude extends Pessoa {
    private String registroConselho;
    private String profissao;
}
