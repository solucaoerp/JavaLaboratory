package com.ibrplanner.logistica.controllers;

import com.ibrplanner.logistica.entities.Cliente;
import com.ibrplanner.logistica.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
@CrossOrigin("*")
public class ClienteController {
    @Autowired
    private ClienteService service;
    @GetMapping
    public List<?> findAll() {
        List<?> clientes = service.findAll();
        return clientes;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Cliente cliente = service.findById(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Cliente save(@Valid @RequestBody Cliente cliente) {
        return service.save(cliente);
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<Cliente> update(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(id);
        cliente = service.save(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
