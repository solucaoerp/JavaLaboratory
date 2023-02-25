package com.ibrplanner.pedidos.mocks;

import com.ibrplanner.pedidos.domain.Categoria;
import com.ibrplanner.pedidos.domain.Produto;
import com.ibrplanner.pedidos.repositories.CategoriaRepository;
import com.ibrplanner.pedidos.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Para desativar a execução deste Mock, comente o método específico da classe, ou,
 * remova a Anotation @Component.
 * Para determinar a ordem de execução das classes Mock, acrescente a Anotation @DependsOn("categoriaMock")
 */
@Component
public class CategoriaMock implements CommandLineRunner {

    @Autowired
    private CategoriaRepository repoCatetoria;
    @Autowired
    private ProdutoRepository repoProduto;

    /**
     * Insere uma lista de Categoria (Mock)
     *
     * @param args argumentos do método principal de entrada
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        // Insert Categoria
        Categoria cat1 = new Categoria(null, "INFORMÁTICA");
        Categoria cat2 = new Categoria(null, "ESCRITÓRIO");
        // Insert Produto
        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        // Associando Produtos as Castegorias (Neste momento as Categorias passa a conhecem seus Produtos)
        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        // Associando Categorias aos Produtos (Neste momento os Produtos passa a conhecer suas Categorias)
        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        repoCatetoria.saveAll(Arrays.asList(cat1, cat2));
        repoProduto.saveAll(Arrays.asList(p1, p2, p3));
    }
}
