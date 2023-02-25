package com.ibrplanner.pedidos.repositories;

import com.ibrplanner.pedidos.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
