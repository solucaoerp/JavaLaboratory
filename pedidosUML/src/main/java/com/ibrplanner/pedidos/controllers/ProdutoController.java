package com.ibrplanner.pedidos.controllers;

import com.ibrplanner.pedidos.controllers.utils.URL;
import com.ibrplanner.pedidos.domain.Produto;
import com.ibrplanner.pedidos.dtos.ProdutoDTO;
import com.ibrplanner.pedidos.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        Produto obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "numPage", defaultValue = "0") Integer numPage,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        String nomeDecoded = URL.decodeParam(nome);
        List<Long> ids = URL.decodeLongList(categorias);
        Page<Produto> list = service.search(nomeDecoded, ids, numPage, linesPerPage, orderBy, direction);

        Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));

        return ResponseEntity.ok().body(listDTO);
    }
}
