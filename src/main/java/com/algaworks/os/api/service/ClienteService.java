package com.algaworks.os.api.service;

import com.algaworks.os.api.exceptionhandler.EntidadeNaoEncontradaException;
import com.algaworks.os.api.exceptionhandler.NegocioException;
import com.algaworks.os.domain.repository.ClienteRepository;
import com.algaworks.os.api.model.ClienteInput;
import com.algaworks.os.domain.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> lista() {
        return clienteRepository.findAll();
    }

    public Cliente buscaPorIdOuJogaBadRequestException(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
    }

    public Cliente save(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
        if(clienteExistente != null) throw new NegocioException("Cliente já existente");

        //Cliente cliente = clienteInput.conveter();    // convertendo na mão
        return clienteRepository.save(cliente); // já seta o ID
    }

    public void delete(Long id) {
        Cliente clienteProcurado = buscaPorIdOuJogaBadRequestException(id);

        clienteRepository.delete(clienteProcurado);
    }

    public Cliente replace(Long id, ClienteInput clienteInput) {
        Cliente savedClient = buscaPorIdOuJogaBadRequestException(id);
        Cliente cliente = clienteInput.conveter();
        cliente.setId(savedClient.getId());

        return clienteRepository.save(cliente);
    }
}
