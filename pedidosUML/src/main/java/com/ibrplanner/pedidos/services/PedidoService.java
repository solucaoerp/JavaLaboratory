package com.ibrplanner.pedidos.services;

import com.ibrplanner.pedidos.domain.*;
import com.ibrplanner.pedidos.enums.EstadoPagamento;
import com.ibrplanner.pedidos.repositories.*;
import com.ibrplanner.pedidos.services.exeptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repo;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmailService emailService;

    public Pedido findById(Long id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());

        // Verifica se o Cliente existe e retorna
        Cliente cliente = clienteRepository.findById(obj.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Não foi possível encontrar o cliente com o ID "));

        obj.setCliente(cliente);

        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);

        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);

            // Verifica se o Produto existe e retorna
            Optional<Produto> produtoOptional = produtoRepository.findById(ip.getProduto().getId());

            if (produtoOptional.isPresent()) {
                Produto produto = produtoOptional.get();
                ip.setProduto(produto);
                ip.setPreco(produto.getPreco());
                ip.setPedido(obj);
            } else {
                throw new RuntimeException("Não foi possível encontrar o produto com o ID " + ip.getProduto().getId());
            }
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());

        // envio do e-mail
        emailService.sendOrderConfirmationEmail(obj);

        //System.out.println(obj);


        return obj;
    }

}
