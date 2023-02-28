package com.ibrplanner.pedidos.repositories;

import com.ibrplanner.pedidos.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
}
