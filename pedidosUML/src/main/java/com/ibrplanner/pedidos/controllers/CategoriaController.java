package com.ibrplanner.pedidos.controllers;

import com.ibrplanner.pedidos.entities.CategoriaModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "categoria")
public class CategoriaController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<CategoriaModel> listar() {

        CategoriaModel cat1 = new CategoriaModel(1L, "Informática");
        CategoriaModel cat2 = new CategoriaModel(2L, "Escritório");

        List<CategoriaModel> list = new ArrayList<>();

        list.add(cat1);
        list.add(cat2);

        return list;
    }

}
