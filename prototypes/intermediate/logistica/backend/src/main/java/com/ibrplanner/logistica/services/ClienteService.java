package com.ibrplanner.logistica.services;

import com.ibrplanner.logistica.entities.Cliente;
import com.ibrplanner.logistica.exceptions.exceptionService.ExceptionService;
import com.ibrplanner.logistica.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Cliente findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ExceptionService("Cliente não encontrato"));
    }

    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Transactional
    public Cliente save(Cliente cliente) {

        boolean emailEmUso = repo.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if (emailEmUso) {
            throw new ExceptionService("Este e-mail já existe.");
        }

        return repo.save(cliente);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
