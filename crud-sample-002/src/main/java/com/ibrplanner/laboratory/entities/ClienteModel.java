/**
 * A classe ClienteModel utiliza agregação, uma vez que possui uma referência a um objeto da classe EnderecoModel por
 * meio da propriedade "endereco". A agregação é um tipo de associação entre objetos que indica que um objeto contém
 * outro objeto como parte de si mesmo. No exemplo apresentado, a classe ClienteModel contém um objeto da classe
 * EnderecoModel como uma de suas propriedades, e esse objeto é criado em outro lugar (provavelmente em uma classe de
 * serviço) e passado para a classe ClienteModel por meio de seu construtor. O objeto de endereço não é destruído
 * quando o objeto de cliente é destruído, ou seja, o objeto de endereço pode ser compartilhado entre várias instâncias
 * de cliente. Esse é um exemplo de agregação, que é um tipo de associação mais fraca do que a composição, onde um
 * objeto é criado dentro de outro objeto e não pode ser compartilhado entre diferentes instâncias.
 */
package com.ibrplanner.laboratory.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_cliente")
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    /**
     * OnteToOne: Isso significa que um cliente tem apenas um endereço, e um endereço pertence a apenas um cliente.
     */
    @OneToOne
    private EnderecoModel endereco;

    public ClienteModel() {
    }

    public ClienteModel(String nome, EnderecoModel endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }
}
