package com.ibrplanner.logistica.controllers;

import com.ibrplanner.logistica.entities.Entrega;
import com.ibrplanner.logistica.services.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/entregas")
public class EntregaController {
    @Autowired
    private EntregaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@RequestBody Entrega entrega) {
        return service.solicitar(entrega);
    }
}
