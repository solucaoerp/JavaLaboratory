package com.ibrplanner.orm.repositories;

import com.ibrplanner.orm.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
