package com.algaworks.os.api.service;

import com.algaworks.os.api.exceptionhandler.BadRequest;
import com.algaworks.os.api.exceptionhandler.NegocioException;
import com.algaworks.os.api.repository.ClienteRepository;
import com.algaworks.os.domain.dto.ClienteDTO;
import com.algaworks.os.domain.dto.ClientePutDTO;
import com.algaworks.os.domain.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
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
                .orElseThrow(() -> new BadRequest("Cliente não encontrado"));
    }

    public Cliente save(ClienteDTO clienteDTO) {
        Cliente clienteExistente = clienteRepository.findByEmail(clienteDTO.getEmail());
        if(clienteExistente != null) throw new NegocioException("Cliente já existente");
        Cliente cliente = Cliente.builder()
                .nome(clienteDTO.getNome())
                .email(clienteDTO.getEmail())
                .telefone(clienteDTO.getTelefone()).build();

        return clienteRepository.save(cliente);
    }

    public void delete(Long id) {
        Cliente clienteProcurado = buscaPorIdOuJogaBadRequestException(id);

        clienteRepository.delete(clienteProcurado);
    }

    public Cliente replace(Long id, @Valid ClientePutDTO clienteputDTO) {
        Cliente savedClient = buscaPorIdOuJogaBadRequestException(id);
        Cliente cliente = Cliente.builder()
                .id(savedClient.getId())    // é necessário fazer isso, caso contrário criará um novo cliente, e não irá atualizar
                .nome(clienteputDTO.getNome())
                .email(clienteputDTO.getEmail())
                .telefone(clienteputDTO.getTelefone()).build();

        return clienteRepository.save(cliente);
    }
}
