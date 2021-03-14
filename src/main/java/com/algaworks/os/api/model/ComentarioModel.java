package com.algaworks.os.api.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ComentarioModel {

    private Long id;
    private String descricao;
    private OffsetDateTime dataEnvio;
}
