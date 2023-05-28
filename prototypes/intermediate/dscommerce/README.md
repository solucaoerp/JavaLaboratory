<div align="center">
  <h1>DSCommerce</h1>
  <img src="https://github.com/solucaoerp/JavaLaboratory/blob/main/prototypes/intermediate/dscommerce/assets/image/icon48_java.png" alt="Java">
  <img src="https://github.com/solucaoerp/JavaLaboratory/blob/main/prototypes/intermediate/dscommerce/assets/image/icon48_spring.png" alt="Spring">
  <img src="https://github.com/solucaoerp/JavaLaboratory/blob/main/prototypes/intermediate/dscommerce/assets/image/icon48_h2database.png" alt="H2Database">
</div>


DSCommerce é um sistema de comércio eletrônico desenvolvido em Java utilizando o framework `Spring Boot (v3.1.0)` com banco de dados `H2 (v2.1.214)`.


## Índice

- [Índice](#indice)
- [Visão Geral](#visao-geral)
- [Diagrama de Classes](#diagrama-de-classes)
- [Mapeamento de Entidades e Relacionamentos](#mapeamento-entidades)
- [Tecnologias](#tecnologias)
- [Recursos](#recursos)
- [Instalação](#instalacao)
- [Uso](#uso)
- [Licença](#licenca)
- [Contato](#contato)


## Visão Geral <a name="visao-geral"></a>

DSCommerce é um sistema de comércio eletrônico. Ele é capaz de manter um catálogo de produtos categorizados que os usuários podem navegar, selecionar produtos para visualizar detalhes e adicioná-los a um carrinho de compras.

Cada usuário possui:

- Nome
- Email
- Telefone
- Data de nascimento
- Senha de acesso

Cada produto tem:

- Nome
- Descrição
- Preço
- Imagem

Os pedidos feitos pelos usuários são salvos no sistema com um status "aguardando pagamento". Os pedidos podem ter seus status atualizados para: pago, enviado, entregue ou cancelado.

Há duas categorias de usuários: clientes e administradores. Enquanto os clientes podem atualizar seu cadastro, fazer pedidos e visualizar seus pedidos, os administradores têm acesso à área administrativa, onde podem acessar todos os cadastros de usuários, produtos e categorias.

## Diagrama de Classes <a name="diagrama-de-classes"></a>

O diagrama de classes a seguir representa a estrutura do DSCommerce:

![Diagrama de Classes](https://github.com/solucaoerp/JavaLaboratory/blob/main/prototypes/intermediate/dscommerce/assets/image/use-case-diagram.png)

Ele apresenta uma visão geral de como as classes estão organizadas e interagem entre si. Isso facilita a compreensão da estrutura do código.


## Mapeamento de Entidades e Relacionamentos <a name="mapeamento-entidades"></a>

Um aspecto importante de qualquer aplicação que interage com um banco de dados é como ela mapeia e gerencia as relações entre suas entidades. No DSCommerce, as entidades `User` e `Order` possuem uma relação bidirecional: um usuário pode ter muitos pedidos e um pedido pertence a um único usuário.

No código, usamos as anotações `@ManyToOne` e `@OneToMany` do Spring Data JPA para gerenciar essas relações. Veja como elas são usadas nas classes `Order` e `User`:

### Classe Order

```java
public class Order {
    // ...
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
    // ...
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;
    // ...
}
```

Na classe `Order`, utilizamos a anotação `@ManyToOne` para indicar que muitos pedidos podem pertencer a um único usuário. O `@JoinColumn` é usado para definir a coluna que fará a ligação entre as tabelas `Order` e `User` no banco de dados.

### Classe User

```java
public class User {
    // ...
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();
    // ...
}
```

Na classe `User`, utilizamos a anotação `@OneToMany` para indicar que um único usuário pode ter muitos pedidos. O parâmetro `mappedBy = "client"` indica que essa relação é mapeada pelo campo `client` na classe `Order`.

Essas anotações permitem ao Spring Data JPA gerenciar automaticamente a persistência e a recuperação dessas entidades e suas relações a partir do banco de dados.

1. No lado "muitos-para-um" (`@ManyToOne`), a anotação `@JoinColumn(name = "client_id")` define que "client_id" é a chave estrangeira que conecta a entidade `Order` à entidade `User`. Nesse caso, o campo "client_id" na tabela `Order` irá armazenar o identificador do `User` que é o cliente associado a esse `Order`.

2. No lado "um-para-muitos" (`@OneToMany`), a propriedade `orders` na classe `User` é uma lista porque um `User` pode ter muitos `Order`s. A lista é normalmente inicializada no ponto de declaração para evitar um `NullPointerException` caso você tente acessar os `Order`s de um `User` que não tem nenhum. A inicialização da lista aqui é uma boa prática para garantir que a lista de `Order`s nunca seja nula, ela será apenas vazia se o `User` não tiver nenhum `Order`.

No relacionamento bidirecional entre `User` e `Order`, a propriedade `mappedBy` é usada para indicar o campo na classe `Order` que está mantendo o relacionamento. Neste caso, `mappedBy = "client"` indica que o campo `client` na classe `Order` é o responsável pelo mapeamento do relacionamento entre `Order` e `User`.

Isso significa que o lado `Order` do relacionamento é o lado dominante - ele é o que define como o relacionamento será mantido no banco de dados. O lado `User`, com a anotação `mappedBy`, é o lado passivo - ele recebe a definição de como o relacionamento é mantido a partir do lado `Order`.

Essa abordagem evita a redundância e a inconsistência ao gerenciar relacionamentos bidirecionais em JPA. Se ambos os lados tentassem controlar o relacionamento, você poderia acabar com duas versões diferentes de como o relacionamento deve ser mapeado, o que causaria problemas. Ao fazer um lado dominante e o outro passivo, você garante que há uma única fonte de verdade para como o relacionamento deve ser gerenciado.

Na classe `User`, o `mappedBy = "client"` refere-se ao nome do atributo na classe `Order` que representa o `User` na relação.

Neste caso, o `client` é o campo na classe `Order` que mantém a referência ao `User` que fez o pedido. Ao usar `mappedBy`, estamos indicando que a propriedade `client` na classe `Order` é responsável pelo mapeamento do relacionamento.

Isso é necessário porque o relacionamento é bidirecional - ou seja, a `Order` tem uma referência ao `User` que fez o pedido (através do campo `client`), e o `User` tem uma lista de `Order` que ele fez (através do campo `orders`). Usando `mappedBy`, podemos vincular esses dois lados do relacionamento juntos.

Por fim, cabe uma **OBSERVAÇÃO IMPORTANTE** quanto ao campo **`Instant`** em `Order`.

**Boa prática!** Usar o tipo `Instant` é uma ótima maneira de lidar com data e hora em um **formato universal (UTC)**, independentemente do fuso horário.

Repare que o campo (**propriedade**) **`moment`** da classe **`Order`** é do tipo **`Instant`**, que é uma representação de um ponto específico na linha do tempo. `Instant` **é sempre armazenado no formato UTC**, que é um **padrão internacional e independente do fuso horário**. Isso torna o `Instant` muito útil para armazenar datas e horas em um formato universal, sem ambiguidade.

Usamos a anotação **`@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")`** para instruir o Hibernate (a implementação JPA que estamos usando) a mapear o campo `Instant` para uma coluna de banco de dados do tipo `TIMESTAMP WITHOUT TIME ZONE`. Isso garante que a informação de data e hora seja armazenada no banco de dados em um formato padrão UTC, independente do fuso horário.

Isso é especialmente útil para aplicações que precisam lidar com usuários, transações ou eventos ocorrendo em diferentes fusos horários. Ao armazenar datas e horas em UTC, podemos facilmente converter para qualquer fuso horário local quando necessário, sem ter que se preocupar com inconsistências devido à armazenagem de datas/horas em diferentes fusos horários."

### Classe Payment

As classes `Order` e `Payment` representam uma relação um-para-um no modelo de domínio, e esta relação será mapeada no banco de dados através do JPA.

A relação entre essas classes é simples: cada `Order` (Pedido) pode ter zero "0" ou um `Payment` (Pagamento) associado, e cada `Payment` está associado a um `Order` específico.

Para entender melhor, vejamos cada classe e suas anotações em detalhes:

```java
public class Order {
...
@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
private Payment payment;
...
```

Na classe `Order`, a propriedade `Payment` é anotada com `@OneToOne`, que indica uma relação um-para-um com a entidade `Payment`. Isso significa que cada `Order` pode ter zero "0" ou apenas um `Payment` associado.

Além disso, `mappedBy = "order"` indica que o `Payment` é o proprietário da relação. Isso significa que a chave estrangeira da relação será armazenada na tabela `Payment` no banco de dados.

`cascade = CascadeType.ALL` é uma opção de configuração que define como as operações de persistência em cascata são propagadas. Por exemplo, quando um `Order` é salvo, seu `Payment` correspondente também será salvo.

```java
public class Payment {
...
@OneToOne
@MapsId
private Order order;
...
```

Na classe `Payment`, a propriedade `Order` também é anotada com `@OneToOne`, reafirmando a relação um-para-um.

`@MapsId` é uma anotação importante aqui. Significa que queremos mapear o ID do `Payment` para ser o mesmo que o ID do `Order`. Isso é útil, por exemplo, para evitar a necessidade de gerar uma chave primária adicional para a tabela `Payment`. Com essa anotação, a tabela `Payment` usará a mesma chave primária da tabela `Order`.

Em resumo, cada `Order` pode ter zero ou um `Payment` e vice-versa, e a chave estrangeira dessa relação está armazenada na tabela `Payment`. Além disso, `Payment` usa o mesmo ID que `Order`, tornando a relação mais eficiente em termos de armazenamento.

Essa abordagem para mapear relações entre entidades é bastante comum em aplicações de banco de dados relacional e facilita a modelagem e a manipulação de relações complexas entre entidades.


## Tecnologias <a name="tecnologias"></a>

- [Java](https://www.oracle.com/br/java/)
- [Spring Boot v3.1.0](https://spring.io/projects/spring-boot)
- [H2 Database v2.1.214](https://www.h2database.com/)

## Recursos <a name="recursos"></a>

- Cadastro de usuários
- Catálogo de produtos
- Carrinho de compras
- Controle de pedidos e status
- Área administrativa

## Instalação <a name="instalacao"></a>

Fique ligado, em breve detalharemos as instruções para instalação e configuração do sistema.

## Uso <a name="uso"></a>

Após a instalação, você pode acessar o sistema através do seu navegador de internet favorito. Basta digitar a URL do seu sistema e começar a navegar pelo catálogo de produtos!

## Licença <a name="licenca"></a>

Este projeto está sob a licença do MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Contato <a name="contato"></a>

Charles: solucao.erp@gmail.com

Link do Projeto: [DSCommerce](https://github.com/solucaoerp/JavaLaboratory/tree/main/prototypes/intermediate/dscommerce)

---

Este README será atualizado conforme o projeto evolui. Fique ligado para novas funcionalidades e melhorias!