package com.ibrplanner.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibrplanner.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    
}
