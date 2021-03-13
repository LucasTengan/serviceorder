package com.algaworks.os.api.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        ExceptionProblem exceptionProblem = ExceptionProblem.builder()
                .status(status.value())
                .titulo(ex.getMessage())
                .dataHora(LocalDateTime.now())
                .build();

        return handleExceptionInternal(ex, exceptionProblem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var campos = new ArrayList<CamposInvalidos>();

        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();

            campos.add(new CamposInvalidos(nome, mensagem));
        }

        var exProblem = ExceptionProblem.builder()
                .status(status.value())
                .titulo("Um ou mais campos estão inválidos. " +
                        "Faça o preenchimento correto e tente novamente")
                .dataHora(LocalDateTime.now())
                .campos(campos)
                .build();

        return handleExceptionInternal(ex, exProblem, headers, status,request);
    }

}
