package com.ibrplanner.logistica.controllers;

import com.ibrplanner.logistica.entities.Cliente;
import com.ibrplanner.logistica.repositories.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repo;

    @GetMapping
    private List<?> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Cliente save(@Valid @RequestBody Cliente cliente) {
        return repo.save(cliente);
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<Cliente> update(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(id);
        cliente = repo.save(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
