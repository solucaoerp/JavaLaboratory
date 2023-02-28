package com.ibrplanner.pedidos.services;

import com.ibrplanner.pedidos.domain.Categoria;
import com.ibrplanner.pedidos.dtos.CategoriaDTO;
import com.ibrplanner.pedidos.helpers.DTOUtils;
import com.ibrplanner.pedidos.repositories.CategoriaRepository;
import com.ibrplanner.pedidos.services.exeptions.DataIntegrityException;
import com.ibrplanner.pedidos.services.exeptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        return DTOUtils.toDTOList(list, CategoriaDTO.class);
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null); /* garante que seja um novo registro, do contrário é um update */
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {
        Categoria newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
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
        return DTOUtils.convertToDTO(categoria, CategoriaDTO.class);
    }

    public Page<CategoriaDTO> findPage(Integer numPage, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(numPage, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<Categoria> page = repo.findAll(pageRequest);
        return DTOUtils.toDTOPage(page, CategoriaDTO.class);
    }

    private void updateData(Categoria newObj, Categoria obj) {
        newObj.setNome(obj.getNome());
    }
}