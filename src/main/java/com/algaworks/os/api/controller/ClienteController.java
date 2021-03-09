package com.algaworks.os.api.controller;

import com.algaworks.os.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @GetMapping
    public List<Cliente> listar() {
        var cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Lucas");
        cliente1.setTelefone("11 912345678");
        cliente1.setEmail("lucastengan@usp.br");

        var cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Maria");
        cliente2.setTelefone("11 43218765");
        cliente2.setEmail("mariadasilva@usp.br");

        return Arrays.asList(cliente1, cliente2);
    }
}
