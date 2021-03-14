package com.algaworks.os.api.controller;

import com.algaworks.os.api.model.OrdemServicoModel;
import com.algaworks.os.api.service.OrdemServicoService;
import com.algaworks.os.api.model.OrdemServicoInput;
import com.algaworks.os.domain.model.OrdemServico;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ordens-servico")
@RequiredArgsConstructor
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<OrdemServicoModel> criarOrdemServico(@RequestBody @Valid OrdemServicoInput ordemServicoInput) {
        OrdemServico ordemServico = toEntity(ordemServicoInput);

        return new ResponseEntity<>(toModel(ordemServicoService.criar(ordemServico)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrdemServico>> listarTodos() {
        return new ResponseEntity<>(ordemServicoService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoModel> buscaPorId(@PathVariable Long id) {
        OrdemServico ordemServico = ordemServicoService.buscaPorIdOuJogaUmaBadRequestException(id);
        OrdemServicoModel model = toModel(ordemServico);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PutMapping("/{ordemServicoId}/finalizacao")
    public ResponseEntity<Void> finalizar(@PathVariable Long ordemServicoId) {
        ordemServicoService.finalizar(ordemServicoId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private OrdemServicoModel toModel(OrdemServico ordemServico) {
        return modelMapper.map(ordemServico, OrdemServicoModel.class);
    }

    public OrdemServico toEntity(OrdemServicoInput ordemServicoInput) {
        return modelMapper.map(ordemServicoInput, OrdemServico.class);
    }
}
