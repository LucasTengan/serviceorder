package com.algaworks.os.api.service;

import com.algaworks.os.api.repository.ClienteRepository;
import com.algaworks.os.domain.dto.ClienteDTO;
import com.algaworks.os.domain.dto.ClientePutDTO;
import com.algaworks.os.domain.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not Found"));
    }

    public Cliente save(ClienteDTO clienteDTO) {
        Cliente cliente = Cliente.builder()
                .nome(clienteDTO.getNome())
                .email(clienteDTO.getEmail())
                .telefone(clienteDTO.getTelefone()).build();

        return clienteRepository.save(cliente);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    public void replace(ClientePutDTO clienteputDTO) {
        Cliente savedClient = buscaPorIdOuJogaBadRequestException(clienteputDTO.getId());
        Cliente cliente = Cliente.builder()
                .id(savedClient.getId())
                .nome(clienteputDTO.getNome())
                .email(clienteputDTO.getEmail())
                .telefone(clienteputDTO.getTelefone()).build();

        clienteRepository.save(cliente);
    }
}
