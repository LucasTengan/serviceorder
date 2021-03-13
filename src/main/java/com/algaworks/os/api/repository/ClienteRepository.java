package com.algaworks.os.api.repository;

import com.algaworks.os.domain.dto.ClienteDTO;
import com.algaworks.os.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByEmail(String email);
}
