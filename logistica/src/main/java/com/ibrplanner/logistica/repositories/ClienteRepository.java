package com.ibrplanner.logistica.repositories;

import com.ibrplanner.logistica.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome);
    List<Cliente> findByNomeContaining(String nome); /* busca aproximada com Like %value% */
    @Override
    List<Cliente> findAll();
    Optional<Cliente> findByEmail(String email);
}
