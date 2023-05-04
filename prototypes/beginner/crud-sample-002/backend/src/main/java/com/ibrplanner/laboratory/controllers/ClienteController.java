package com.ibrplanner.laboratory.controllers;

import com.ibrplanner.laboratory.entities.ClienteModel;
import com.ibrplanner.laboratory.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository repo;

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCliente(@PathVariable Long id) {
        Optional<ClienteModel> cliente = repo.findById(id);
        if (cliente.isPresent()) {
            return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
        } else {
            String mensagem = "Cliente [ " + id + " ] não encontrado.";
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> listarClientes() {
        List<ClienteModel> clientes = repo.findAll();
        if (clientes.isEmpty()) {
            String mensagem = "Cliente(s) não encontrado(s).";
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<String> adicionarCliente(@RequestBody ClienteModel cliente) {
        ClienteModel novoCliente = repo.save(cliente);
        Long id = novoCliente.getId();
        String mensagem = String.format("Cliente [ %d ] adicionado com sucesso.", id);
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarCliente(@PathVariable Long id, @RequestBody ClienteModel cliente) {
        Optional<ClienteModel> clienteExistente = repo.findById(id);
        if (clienteExistente.isPresent()) {
            cliente.setId(id);
            repo.save(cliente);
            String mensagem = String.format("Cliente [ %d ] atualizado com sucesso.", id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } else {
            String mensagem = String.format("Cliente [ %d ] não encontrado.", id);
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerCliente(@PathVariable Long id) {
        Optional<ClienteModel> cliente = repo.findById(id);
        if (cliente.isPresent()) {
            repo.delete(cliente.get());
            String mensagem = "Cliente [ " + id + " ] deletado com sucesso.";
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } else {
            String mensagem = "Cliente [ " + id + " ] não encontrado.";
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/batch")
    public ResponseEntity<String> removerClientes(@RequestBody Map<String, List<Long>> requestBody) {
        List<Long> ids = requestBody.get("ids");
        if (ids == null || ids.isEmpty()) {
            return ResponseEntity.badRequest().body("ID(s) não especificado(s).");
        }

        List<Long> idsDeletados = new ArrayList<>();
        List<Long> idsNaoEncontrados = new ArrayList<>();

        for (Long id : ids) {
            try {
                Optional<ClienteModel> cliente = repo.findById(id);
                if (cliente.isPresent()) {
                    repo.delete(cliente.get());
                    idsDeletados.add(id);
                } else {
                    idsNaoEncontrados.add(id);
                }
            } catch (IllegalArgumentException e) {
                ResponseEntity.badRequest().body(e);
            }
        }

        StringBuilder mensagem = new StringBuilder();
        if (!idsNaoEncontrados.isEmpty()) {
            mensagem.append("Não encontrado(s): ");
            mensagem.append(idsNaoEncontrados.toString());
            mensagem.append(". \n");
        }

        if (!idsDeletados.isEmpty()) {
            mensagem.append("Encontrado(s) e deletado(s): ");
            mensagem.append(idsDeletados.toString());
            mensagem.append(".");
        }

        return ResponseEntity.ok(mensagem.toString().trim());
    }
}
