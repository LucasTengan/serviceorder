package com.algaworks.os.api.controller;

import com.algaworks.os.api.model.ComentarioInput;
import com.algaworks.os.api.model.ComentarioModel;
import com.algaworks.os.api.service.OrdemServicoService;
import com.algaworks.os.domain.model.Comentario;
import com.algaworks.os.domain.model.OrdemServico;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final OrdemServicoService ordemServicoService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ComentarioModel>> listar(@PathVariable Long ordemServicoId) {
        OrdemServico ordemServico = ordemServicoService.buscaPorIdOuJogaUmaBadRequestException(ordemServicoId);

        return new ResponseEntity<>(toCollectionModel(ordemServico.getComentarios()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ComentarioModel> adicionar(@PathVariable Long ordemServicoId, @RequestBody
            @Valid ComentarioInput comentarioInput) {

        Comentario comentario = ordemServicoService.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());

        return new ResponseEntity<>(toModel(comentario), HttpStatus.CREATED);
    }

    private ComentarioModel toModel(Comentario comentario) {
        return modelMapper.map(comentario, ComentarioModel.class);
    }

    private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios) {
        return comentarios.stream()
                    .map(this::toModel)
                    .collect(Collectors.toList());
    }
}
