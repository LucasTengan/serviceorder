package com.algaworks.os.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionProblem {

    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;
    private List<CamposInvalidos> campos;
}
