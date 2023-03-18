package com.ibrplanner.logistica.services;

import com.ibrplanner.logistica.entities.Cliente;
import com.ibrplanner.logistica.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return repo.findById(id);
    }

    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Transactional
    public Cliente save(Cliente cliente) {
        return repo.save(cliente);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
