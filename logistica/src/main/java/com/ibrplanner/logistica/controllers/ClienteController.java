package com.ibrplanner.logistica.controllers;

import com.ibrplanner.logistica.entities.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    // doc HTTP/Code: https://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml
    // doc HTTP/Code: https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status
    // doc HTTP/Code: https://www.webfx.com/web-development/glossary/http-status-codes/

    @GetMapping
    private List<Cliente> listar() {

        var cli1 = new Cliente();
        var cli2 = new Cliente();
        var cli3 = new Cliente();

        cli1.setId(1L);
        cli1.setNome("João");
        cli1.setTelefone("55 6899-2255");
        cli1.setEmail("joao@gmail.com");

        cli2.setId(2L);
        cli2.setNome("Maria");
        cli2.setTelefone("55 99988-1322");
        cli2.setEmail("maria@gmail.com");

        cli3.setId(3L);
        cli3.setNome("José");
        cli3.setTelefone("19 11992-2526");
        cli3.setEmail("jose@gmail.com");

        return Arrays.asList(cli1, cli2, cli3);
    }

}
