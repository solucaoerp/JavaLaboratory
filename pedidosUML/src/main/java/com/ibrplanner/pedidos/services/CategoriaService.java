package com.ibrplanner.pedidos.services;

import com.ibrplanner.pedidos.domain.Categoria;
import com.ibrplanner.pedidos.services.exeptions.DataIntegrityException;
import com.ibrplanner.pedidos.services.exeptions.ObjectNotFoundException;
import com.ibrplanner.pedidos.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Categoria insert(Categoria obj) {
        obj.setId(null); /* garante que seja um novo registro, do contrário é um update */
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {
        findById(obj.getId());
        return repo.save(obj);
    }
    //deleteById
    public void delete(Long id){
        findById(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
        }
    }

}
