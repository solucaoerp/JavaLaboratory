package com.ibrplanner.logistica.controllers;

import com.ibrplanner.logistica.entities.Entrega;
import com.ibrplanner.logistica.repositories.EntregaRepository;
import com.ibrplanner.logistica.services.EntregaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/entregas")
public class EntregaController {
    @Autowired
    private EntregaService serviceService;
    @Autowired
    private EntregaRepository entregaRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
        return serviceService.solicitar(entrega);
    }

    @GetMapping
    public List<Entrega> list() {
        return entregaRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Entrega> findById(@PathVariable Long id) {
        return entregaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
