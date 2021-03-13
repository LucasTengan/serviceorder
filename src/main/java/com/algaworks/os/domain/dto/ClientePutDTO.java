package com.algaworks.os.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ClientePutDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String telefone;
}
