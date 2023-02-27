package com.ibrplanner.pedidos.services;

import com.ibrplanner.pedidos.domain.Categoria;
import com.ibrplanner.pedidos.dtos.CategoriaDTO;
import com.ibrplanner.pedidos.repositories.CategoriaRepository;
import com.ibrplanner.pedidos.services.exeptions.DataIntegrityException;
import com.ibrplanner.pedidos.services.exeptions.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<CategoriaDTO> findAll() {
        List<Categoria> list = repo.findAll();
        List<CategoriaDTO> listDTO = new ArrayList<>();
        for (Categoria categoria : list) {
            CategoriaDTO categoriaDTO = toCategoriaDTO(categoria);
            listDTO.add(categoriaDTO);
        }
        return listDTO;
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null); /* garante que seja um novo registro, do contrário é um update */
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {
        findById(obj.getId());
        return repo.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
        }
    }

    private CategoriaDTO toCategoriaDTO(Categoria categoria) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        BeanUtils.copyProperties(categoria, categoriaDTO);
        return categoriaDTO;
    }
}