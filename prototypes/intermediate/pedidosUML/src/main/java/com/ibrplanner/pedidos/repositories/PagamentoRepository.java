package com.ibrplanner.pedidos.repositories;

import com.ibrplanner.pedidos.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
