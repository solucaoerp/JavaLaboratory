package com.ibrplanner.pedidos.repositories;

import com.ibrplanner.pedidos.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
