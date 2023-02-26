package com.ibrplanner.pedidos.controllers;

import com.ibrplanner.pedidos.domain.Categoria;
import com.ibrplanner.pedidos.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> findById(@PathVariable Long id) {
        Categoria obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categoria obj){
        obj = service.insert(obj);

        /* Boa Prática: devolve a URI do novo recurso inserido no Header */
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build(); /* Success: 201 Created */
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Categoria obj){
        obj.setId(id); /* garante a atualização da Categoria correta */
        obj = service.update(obj);

        return ResponseEntity.noContent().build(); /* Success: 204 No Content */
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); /* Success: 204 No Content */
    }

}
