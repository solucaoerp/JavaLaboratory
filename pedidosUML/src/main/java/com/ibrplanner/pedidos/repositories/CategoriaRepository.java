package com.ibrplanner.pedidos.repositories;

import com.ibrplanner.pedidos.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
