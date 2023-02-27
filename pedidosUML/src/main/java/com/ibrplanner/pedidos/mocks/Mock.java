package com.ibrplanner.pedidos.mocks;

import com.ibrplanner.pedidos.domain.*;
import com.ibrplanner.pedidos.enums.EstadoPagamento;
import com.ibrplanner.pedidos.enums.TipoCliente;
import com.ibrplanner.pedidos.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
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

    @Autowired
    private ClienteRepository repoCliente;
    @Autowired
    private EnderecoRepository repoEndereco;

    @Autowired
    private PedidoRepository repoPedido;

    @Autowired
    private PagamentoRepository repoPagamento;

    @Autowired
    private ItemPedidoRepository repoItemPedido;

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
        Categoria cat3 = new Categoria(null, "CAMA MESA E BANHO");
        Categoria cat4 = new Categoria(null, "ELETRÔNICOS");
        Categoria cat5 = new Categoria(null, "JARDINAGEM");
        Categoria cat6 = new Categoria(null, "DECORAÇÃO");
        Categoria cat7 = new Categoria(null, "PERFUMARIA");

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
        repoCatetoria.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
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

        // Insert Cliente e seus telefones associados
        Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com",
                "36378912377", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

        // Insert Endereco
        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303",
                "Jardim", "38220834", cli1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800",
                "Centro", "38777012", cli1, c2);

        // Associando Endereco ao Cliente
        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        // montando a lista final com suas associações Cliente/Endereco
        repoCliente.saveAll(Arrays.asList(cli1));
        repoEndereco.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        // Insert Pedido
        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        // Inser Pagamento
        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);

        // Seta o pagamento no ped1
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,
                sdf.parse("20/10/2017 00:00"), null);

        // Seta o pagamento no ped2
        ped2.setPagamento(pagto2);

        // Setar os pedidos referente ao Cliente
        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        // montando a lista final com suas associações Pedido/Pagamento
        repoPedido.saveAll(Arrays.asList(ped1, ped2));
        repoPagamento.saveAll(Arrays.asList(pagto1, pagto2));

        // Insert ItemPedido
        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        // Associando os Itens de Pedido ao Pedido (Pedido conhecendo seus Itens)
        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        // Associando os Itens de Pedido ao Produto (produto conhecendo seus Itens)
        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        // montando a lista final de ItemPedido com suas associações Produto/ItemPedido/Pedido
        repoItemPedido.saveAll(Arrays.asList(ip1, ip2, ip3));
    }
}
