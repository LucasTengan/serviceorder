package com.algaworks.os.api.controller;

import com.algaworks.os.api.model.ClienteModel;
import com.algaworks.os.api.service.ClienteService;
import com.algaworks.os.api.model.ClienteInput;
import com.algaworks.os.domain.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return new ResponseEntity<>(clienteService.lista(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(clienteService.buscaPorIdOuJogaBadRequestException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteModel> cadastraCliente(@RequestBody @Valid ClienteInput clienteInput) {
        var cliente = toEntity(clienteInput);

        return new ResponseEntity<>(toModel(clienteService.save(cliente)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> replace(@PathVariable Long id, @RequestBody @Valid ClienteInput clienteInput) {
        return new ResponseEntity<>(
                clienteService.replace(id, clienteInput), HttpStatus.OK
        );
    }

    private ClienteModel toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteModel.class);
    }

    private Cliente toEntity(ClienteInput clienteInput) {
        return modelMapper.map(clienteInput, Cliente.class);
    }
}
