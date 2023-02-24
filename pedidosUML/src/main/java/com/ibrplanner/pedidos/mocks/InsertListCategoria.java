package com.ibrplanner.pedidos.mocks;

import com.ibrplanner.pedidos.domain.Categoria;
import com.ibrplanner.pedidos.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Para desativar a execução deste Mock, comente o método específico da classe, ou,
 * remova a Anotation @Component
 */
@Component
public class InsertListCategoria implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Insere uma lista de Categoria (Mock)
     * @param args argumentos do método principal de entrada
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "INFORMÁTICA");
        Categoria cat2 = new Categoria(null, "ESCRITÓRIO");
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
    }
}
