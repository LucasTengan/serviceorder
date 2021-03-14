package com.algaworks.os.api.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ComentarioInput {

    @NotBlank
    private String descricao;
}
