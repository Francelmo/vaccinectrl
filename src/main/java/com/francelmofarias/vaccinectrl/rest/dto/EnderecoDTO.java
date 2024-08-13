package com.francelmofarias.vaccinectrl.rest.dto;

import lombok.Data;

@Data
public class EnderecoDTO {
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
    private String comentario;
}
