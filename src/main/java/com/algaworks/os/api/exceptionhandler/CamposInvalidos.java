package com.algaworks.os.api.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CamposInvalidos {

    private String nome;
    private String mensagem;
}
