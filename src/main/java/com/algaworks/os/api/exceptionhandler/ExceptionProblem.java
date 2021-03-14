package com.algaworks.os.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionProblem {

    private Integer status;
    private OffsetDateTime dataHora;
    private String titulo;
    private List<CamposInvalidos> campos;
}
