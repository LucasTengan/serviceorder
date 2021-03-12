package com.algaworks.os.api.controller;

import com.algaworks.os.api.service.ClienteService;
import com.algaworks.os.domain.dto.ClienteDTO;
import com.algaworks.os.domain.dto.ClientePutDTO;
import com.algaworks.os.domain.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(clienteService.lista());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscaPorIdOuJogaBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastraCliente(@RequestBody ClienteDTO clienteDTO) {
        return new ResponseEntity<>(clienteService.save(clienteDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody ClientePutDTO clientePutDTO) {
        clienteService.replace(clientePutDTO);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
