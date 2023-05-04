package com.ibrplanner.pedidos.repositories;

import com.ibrplanner.pedidos.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
