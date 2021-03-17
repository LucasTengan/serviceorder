package com.algaworks.os.api.controller;

import com.algaworks.os.api.model.ClienteInput;
import com.algaworks.os.api.service.ClienteService;
import com.algaworks.os.domain.model.Cliente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
class ClienteControllerTest {

    @InjectMocks    // classe em si que quero testar
    private ClienteController clienteController;
    @Mock           // classes que estão sendo usadas pelo InjectMocks
    private ClienteService clienteServiceMock;  // um mock de algo, é algo que é um "duble" da classe, ele simula seus comportamentos

    // o mock significa fazer o comportamento semelhante ao da classe

    @BeforeEach // precisamos definir o comportamento de cada método do Mock para representá-lo
    void setUp() {
        ArrayList<Cliente> clienteList = new ArrayList<>(List.of(criaCliente()));
        BDDMockito.when(clienteServiceMock.lista())
                .thenReturn(clienteList);

        BDDMockito.when(clienteServiceMock.buscaPorIdOuJogaBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(criaCliente());

        BDDMockito.when(clienteServiceMock.save(ArgumentMatchers.any()))
                .thenReturn(criaCliente());

        BDDMockito.doNothing().when(clienteServiceMock).delete(ArgumentMatchers.anyLong());
    }

    private Cliente criaCliente() {
        return Cliente.builder()
                .id(1l)
                .nome("Lucas Tengan")
                .telefone("11 976936359")
                .email("lucastengan@usp.br")
                .build();
    }

    private ClienteInput criaClienteInput() {
        return ClienteInput.builder()
                .nome("Lucas Tengan")
                .telefone("11 976936359")
                .email("lucastengan@usp.br")
                .build();
    }

    @Test
    @DisplayName("List returns list of client when sucessful")
    void list_ReturnsListOfClientsInsideListObject_WhenSucessful() {
        String expectedName = criaCliente().getNome();  // só serve de comparação
        List<Cliente> clienteList = clienteController.listarTodos().getBody();// precisamos usar o getBody pois retorna um responseentity

        Assertions.assertThat(clienteList).isNotNull();
        Assertions.assertThat(clienteList.get(0).getNome()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("FindById returns client when sucessful")
    void findById_ReturnsClient_WhenSucessful() {
        Long expectedId = criaCliente().getId();    // só serve de comparação
        Cliente cliente = clienteController.buscaPorId(1L).getBody();  // precisamos do getBody pois retorna um ResponseEntity

        Assertions.assertThat(cliente).isNotNull();
        Assertions.assertThat(cliente.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Save returns client when sucessful")
    void save_ReturnsClient_WhenSucessful() {
        var cliente = clienteController.cadastraCliente(criaClienteInput()).getBody();  // precisamos do getBody pois retorna um ResponseEntity

        Assertions.assertThat(cliente).isNotNull();
        Assertions.assertThat(cliente).isEqualTo(criaCliente());
    }

    @Test
    @DisplayName("Delete removes client when sucessful")
    void delete_ReturnsClient_WhenSucessful() {

        Assertions.assertThatCode(() -> clienteController.deletar(1L))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = clienteController.deletar(1L);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }
}