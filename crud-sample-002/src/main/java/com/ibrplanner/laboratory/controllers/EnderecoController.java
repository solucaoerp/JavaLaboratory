package com.ibrplanner.laboratory.controllers;

import com.ibrplanner.laboratory.entities.EnderecoModel;
import com.ibrplanner.laboratory.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository repo;

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEndereco(@PathVariable Long id) {
        Optional<EnderecoModel> endereco = repo.findById(id);
        if (endereco.isPresent()) {
            return new ResponseEntity<>(endereco.get(), HttpStatus.OK);
        } else {
            String mensagem = String.format("Endereço [ %d ] não foi localizado!", id);
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<EnderecoModel> listarEnderecos() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<String> adicionarEndereco(@RequestBody EnderecoModel endereco) {
        EnderecoModel novoEndereco = repo.save(endereco);
        Long id = novoEndereco.getId();
        String mensagem = String.format("Endereço [%d] foi inserido com sucesso.", id);
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoModel endereco) {
        Optional<EnderecoModel> enderecoExistente = repo.findById(id);
        if (enderecoExistente.isPresent()) {
            endereco.setId(id);
            repo.save(endereco);
            return ResponseEntity.ok("Endereço " + id + " atualizado com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("ID inválido!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerEndereco(@PathVariable Long id) {
        Optional<EnderecoModel> endereco = repo.findById(id);
        if (endereco.isPresent()) {
            try {
                repo.delete(endereco.get());
                return new ResponseEntity<>("Endereço " + id + " excluído com sucesso", HttpStatus.OK);
            } catch (DataIntegrityViolationException e) {
                return new ResponseEntity<>("Não foi possível excluir o endereço " + id + " devido à integridade referencial.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Endereço " + id + " não encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
