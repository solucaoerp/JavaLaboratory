package com.ibrplanner.pedidos.services;

import com.ibrplanner.pedidos.domain.Categoria;
import com.ibrplanner.pedidos.domain.Produto;
import com.ibrplanner.pedidos.repositories.CategoriaRepository;
import com.ibrplanner.pedidos.repositories.ProdutoRepository;
import com.ibrplanner.pedidos.services.exeptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repo;

    @Autowired
    private CategoriaRepository repoCategoria;

    public Produto findById(Long id) {
        Optional<Produto> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrato! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public Page<Produto> search(String nome, List<Long> ids, Integer numPage, Integer linesPerPage, String orderBy, String direction) {

        PageRequest pageRequest = PageRequest.of(numPage, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = repoCategoria.findAllById(ids);

        // chamando o método tradicional no repository
        //return repo.search(nome, categorias, pageRequest);

        // chamando o método com JPQL
        return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);

    }

}
