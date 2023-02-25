package com.ibrplanner.pedidos.repositories;

import com.ibrplanner.pedidos.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
