package com.ibrplanner.laboratory.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_item_pedido")
public class ItemPedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private double preco;
    private int quantidade;
    @ManyToOne
    private PedidoModel pedido;

    public ItemPedidoModel(String descricao, double preco, int quantidade) {
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}
