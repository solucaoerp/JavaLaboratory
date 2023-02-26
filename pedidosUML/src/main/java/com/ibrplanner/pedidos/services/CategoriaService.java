package com.ibrplanner.pedidos.services;

import com.ibrplanner.pedidos.domain.Categoria;
import com.ibrplanner.pedidos.exeptions.ObjectNotFoundException;
import com.ibrplanner.pedidos.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repo;

    public Categoria findById(Long id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrato! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj){
        obj.setId(null); /* garante que seja um novo registro, do contrário é um update */
        return repo.save(obj);
    }


}
