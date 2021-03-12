package com.algaworks.os.domain.dto;

import lombok.Data;

@Data
public class ClientePutDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
}
