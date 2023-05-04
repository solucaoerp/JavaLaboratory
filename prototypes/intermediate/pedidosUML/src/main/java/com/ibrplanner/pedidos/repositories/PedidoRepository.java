package com.ibrplanner.pedidos.repositories;

import com.ibrplanner.pedidos.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
