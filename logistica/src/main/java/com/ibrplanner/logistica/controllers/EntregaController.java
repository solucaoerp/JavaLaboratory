package com.ibrplanner.logistica.controllers;

import com.ibrplanner.logistica.controllers.dtos.EntregaDTO;
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
    private EntregaService entregaService;

    @GetMapping
    public List<EntregaDTO> listarTodos() {
        return entregaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaDTO> buscarPorId(@PathVariable Long id) {
        EntregaDTO entregaDTO = entregaService.buscarPorId(id);
        return entregaDTO != null ? ResponseEntity.ok(entregaDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaDTO salvar(@RequestBody @Valid EntregaDTO entregaDTO) {
        return entregaService.salvar(entregaDTO);
    }
}
