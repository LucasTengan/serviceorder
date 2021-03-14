package com.algaworks.os.api.model;

import com.algaworks.os.domain.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteInput {

    @NotBlank(message = "O nome do cliente não pode ser nulo ou vazio")
    private String nome;
    @NotBlank(message = "O email do cliente não pode ser nulo ou vazio")
    private String email;
    @NotBlank(message = "O telefone do cliente não pode ser nulo ou vazio") @Size(max = 20)
    private String telefone;

    public Cliente conveter() { // convertendo na mão o DTO
        return new Cliente(nome, email, telefone);
    }
}
