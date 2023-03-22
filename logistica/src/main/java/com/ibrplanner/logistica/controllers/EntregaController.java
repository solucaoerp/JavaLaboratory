package com.ibrplanner.logistica.controllers;

import com.ibrplanner.logistica.common.ObjectConverter;
import com.ibrplanner.logistica.dtos.EntregaDTO;
import com.ibrplanner.logistica.dtos.EntregaInput;
import com.ibrplanner.logistica.entities.Entrega;
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

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaDTO salvar(@RequestBody @Valid EntregaDTO entregaDTO) {
        return entregaService.salvar(entregaDTO);
    }

    @PostMapping(value = "/solicitar")
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaDTO solicitar(@RequestBody @Valid EntregaInput entregaInput) {
        Entrega novaEntrega = ObjectConverter.toEntity(entregaInput);
        Entrega entregaSolicitada = entregaService.solicitar(novaEntrega);

        return ObjectConverter.toModel(entregaSolicitada);
    }

}
