package com.ibrplanner.pedidos.controllers;

import com.ibrplanner.pedidos.domain.Pedido;
import com.ibrplanner.pedidos.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Pedido obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
