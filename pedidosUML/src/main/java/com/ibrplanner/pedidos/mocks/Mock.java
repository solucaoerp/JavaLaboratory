package com.ibrplanner.pedidos.mocks;

import com.ibrplanner.pedidos.domain.Categoria;
import com.ibrplanner.pedidos.domain.Cidade;
import com.ibrplanner.pedidos.domain.Estado;
import com.ibrplanner.pedidos.domain.Produto;
import com.ibrplanner.pedidos.repositories.CategoriaRepository;
import com.ibrplanner.pedidos.repositories.CidadeRepository;
import com.ibrplanner.pedidos.repositories.EstadoRepository;
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
public class Mock implements CommandLineRunner {

    @Autowired
    private CategoriaRepository repoCatetoria;
    @Autowired
    private ProdutoRepository repoProduto;

    @Autowired
    private EstadoRepository repoEstado;

    @Autowired
    private CidadeRepository repoCidade;

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

        // montando a lista final com as associações Produto/Categoria/Produto
        repoCatetoria.saveAll(Arrays.asList(cat1, cat2));
        repoProduto.saveAll(Arrays.asList(p1, p2, p3));

        // Insert Estado
        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        // Insert Cidade com associação ao Estado no construtor
        // quando a associação for M:1, pode ser feito no próprio construtor
        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        // Associando Cidades ao Estado (Neste momento o Estado passa a conhecer suas Cidades)
        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        // montando a lista final com as associações Estado/Cidade
        repoEstado.saveAll(Arrays.asList(est1, est2));
        repoCidade.saveAll(Arrays.asList(c1, c2, c3));
    }
}
