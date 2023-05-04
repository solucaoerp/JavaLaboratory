package com.ibrplanner.laboratory.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_pedido")
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ClienteModel cliente;
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedidoModel> itens;

    public PedidoModel(ClienteModel cliente, List<ItemPedidoModel> itens) {
        this.cliente = cliente;
        this.itens = itens;
    }
}
