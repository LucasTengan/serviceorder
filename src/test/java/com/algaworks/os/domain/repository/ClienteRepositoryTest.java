package com.algaworks.os.domain.repository;

import com.algaworks.os.domain.model.Cliente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Testes para o Cliente Repository")
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    @DisplayName("Salva cliente persistido quando deu sucesso")
    void salva_PersistidoCliente_QuandoSucesso() {
        Cliente clienteASerSalvo = criaCliente();
        Cliente clienteSalvo = this.clienteRepository.save(clienteASerSalvo);

        Assertions.assertThat(clienteSalvo).isNotNull();
        Assertions.assertThat(clienteSalvo.getId()).isNotNull();
        Assertions.assertThat(clienteSalvo.getNome()).isEqualTo(clienteASerSalvo.getNome());
    }
    
    @Test
    @DisplayName("Salva cliente atualizado quando deu sucesso")
    void salva_AtualizadoCliente_QuandoSucesso() {
        Cliente clienteASerSalvo = criaCliente();
        Cliente clienteSalvo = this.clienteRepository.save(clienteASerSalvo);
        clienteSalvo.setNome("Lucas Ikeda");
        Cliente clienteAtualizado = this.clienteRepository.save(clienteSalvo);

        Assertions.assertThat(clienteAtualizado).isNotNull();
        Assertions.assertThat(clienteAtualizado.getId()).isNotNull();
        Assertions.assertThat(clienteAtualizado.getNome()).isEqualTo(clienteSalvo.getNome());
    }

    private Cliente criaCliente() {
        return Cliente.builder()
                .nome("Lucas Tengan")
                .telefone("11 976936359")
                .email("lucastengan@usp.br")
                .build();
    }
}