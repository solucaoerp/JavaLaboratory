package com.ibrplanner.logistica.controllers;

import com.ibrplanner.logistica.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repo;

    @GetMapping
    private List<?> listar() {
        return repo.findAll();
    }

}
