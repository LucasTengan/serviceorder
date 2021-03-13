package com.algaworks.os.api.controller;

import com.algaworks.os.api.service.ClienteService;
import com.algaworks.os.domain.dto.ClienteDTO;
import com.algaworks.os.domain.dto.ClientePutDTO;
import com.algaworks.os.domain.model.Cliente;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return new ResponseEntity<>(clienteService.lista(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(clienteService.buscaPorIdOuJogaBadRequestException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastraCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        return new ResponseEntity<>(clienteService.save(clienteDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> replace(@PathVariable Long id, @RequestBody @Valid ClientePutDTO clientePutDTO) {
        return new ResponseEntity<>(
                clienteService.replace(id, clientePutDTO), HttpStatus.OK
        );
    }
}
