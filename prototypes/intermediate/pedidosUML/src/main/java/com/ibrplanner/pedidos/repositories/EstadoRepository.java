package com.ibrplanner.pedidos.repositories;

import com.ibrplanner.pedidos.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
