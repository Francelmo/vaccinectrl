package com.francelmofarias.vaccinectrl.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class Endereco {
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
    private String comentario;
}

