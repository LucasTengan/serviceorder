package com.algaworks.os.api.model;

import com.algaworks.os.api.service.ClienteService;
import com.algaworks.os.domain.model.Cliente;
import com.algaworks.os.domain.model.OrdemServico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdemServicoInput {

    @NotNull
    private Long clienteId;
    @NotBlank(message = "A descrição não pode estar vazia ou nula")
    private String descricao;
    @NotNull
    private BigDecimal preco;

    public OrdemServico converter(ClienteService service) {
        Cliente cliente = service.buscaPorIdOuJogaBadRequestException(clienteId);
        return new OrdemServico(clienteId, descricao, cliente);
    }
}
