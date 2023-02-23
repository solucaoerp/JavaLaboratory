package com.ibrplanner.pedidos.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "categoria")
public class CategoriaController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String listar(){
        return "CategoriaController em funcionamento!";
    }

}
