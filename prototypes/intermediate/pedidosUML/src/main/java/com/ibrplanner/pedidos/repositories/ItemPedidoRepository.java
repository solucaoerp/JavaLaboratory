package com.ibrplanner.pedidos.repositories;

import com.ibrplanner.pedidos.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
