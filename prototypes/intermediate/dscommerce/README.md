<div align="center">
  <h1>DSCommerce</h1>
  <img src="https://github.com/solucaoerp/JavaLaboratory/blob/main/prototypes/intermediate/dscommerce/assets/image/icon48_java.png" alt="Java">
  <img src="https://github.com/solucaoerp/JavaLaboratory/blob/main/prototypes/intermediate/dscommerce/assets/image/icon48_spring.png" alt="Spring">
  <img src="https://github.com/solucaoerp/JavaLaboratory/blob/main/prototypes/intermediate/dscommerce/assets/image/icon48_h2database.png" alt="H2Database">
</div>

DSCommerce é um sistema de comércio eletrônico desenvolvido em Java utilizando o framework `Spring Boot (v3.1.0)` com banco de dados `H2 (v2.1.214)`.

## Índice

- [Visão Geral](#view)
- [Diagrama de Classes](#diagram)
- [Mapeamentos e Relacionamentos](#mapping)
    - [*:1 (muitos-para-um)](#relation-many-to-one)
	    - [Class Order](#class-order)
    - [1:* (um-para-muitos)](#relation-one-to-many)
        - [Class User](#class-user)
    - [1:1 (um-para-um)](#relation-one-to-one)
        - [Class Order](#class-order-one-to-one)
        - [Class Payment](#class-payment-one-to-one)
    - [M:M (muitos-para-muitos)](#relation-many-to-many)
        - [Class Category](#class-category-many-to-many)
        - [Class Product](#class-product-many-to-many)
	- [M:M (muitos-para-Muitos) com classe de associação e (chave Composta)](#relation-many-to-many-composite-key)
	    - [1. Class OrderItemPK](#class-orderitempk-many-to-many)
	    - [2. Class OrderItem](#class-orderitem-many-to-many)
	    - [3. Class Order](#class-order-many-to-many-compost)
	    - [4. Class Product](#class-product-many-to-many-compost)
- [Tecnologias](#technologies)
- [Recursos](#resources)
- [Instalação](#install)
- [Uso](#use)
- [Licença](#license)
- [Contato](#contact)


## Visão Geral <a name="view"></a>

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

## Diagrama de Classes <a name="diagram"></a>

O diagrama de classes a seguir representa a estrutura do DSCommerce:

![Diagrama de Classes](https://github.com/solucaoerp/JavaLaboratory/blob/main/prototypes/intermediate/dscommerce/assets/image/use-case-diagram.png)

Ele apresenta uma visão geral de como as classes estão organizadas e interagem entre si. Isso facilita a compreensão da estrutura do código.


## Mapeamento de Entidades e Relacionamentos <a name="mapping"></a>

Um aspecto importante de qualquer aplicação que interage com um banco de dados é como ela mapeia e gerencia as relações entre suas entidades. No DSCommerce, as entidades `User` e `Order` possuem uma relação bidirecional: um usuário pode ter muitos pedidos e um pedido pertence a um único usuário.

No código, usamos as anotações `@ManyToOne` e `@OneToMany` do Spring Data JPA para gerenciar essas relações. Veja como elas são usadas nas classes `Order` e `User`:

### Muitos-para-Um [ *:1 ] <a name="relation-many-to-one"></a>

#### Class Order <a name="class-order"></a>

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

### 1:* (um-para-muitos) <a name="relation-one-to-many"></a>

#### Class User <a name="class-user"></a>

```java
public class User {
    // ...
    
    @Column(unique = true)
    private String email;

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

**DICA!** Aqui cabe uma breve explicação sobre o campo `email` quanto à anotação `@Column(unique = true)`:

O atributo **`@Column(unique = true)`** é uma anotação do JPA que define uma restrição de unicidade para a coluna de banco de dados correspondente. Isso significa que dois registros não podem ter o mesmo valor para esse campo. Ao definir `unique = true`, o JPA (Hibernate, por exemplo) criará uma restrição de unicidade para a coluna de email na tabela do banco de dados ao criar a estrutura da tabela.

Se tentarmos inserir um usuário com um endereço de e-mail que já está presente na tabela, o banco de dados lançará uma exceção.

Vale a pena notar que as restrições de unicidade são uma forma de regra de validação de negócios e é melhor aplicá-las no nível do banco de dados para garantir a integridade dos dados.


### Um-para-Um [ 1:1 -> '0..1' ] <a name="relation-one-to-one"></a>

As classes `Order` e `Payment` representam uma relação um-para-um no modelo de domínio, e esta relação será mapeada no banco de dados através do JPA.

A relação entre essas classes é simples: cada `Order` (Pedido) pode ter zero "0" ou um `Payment` (Pagamento) associado, e cada `Payment` está associado a um `Order` específico.

Para entender melhor, vejamos cada classe e suas anotações em detalhes:

#### Class Order <a name="class-order-one-to-one"></a>

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

#### Class Payment <a name="class-payment-one-to-one"></a>

Na classe `Payment`, a propriedade `Order` também é anotada com `@OneToOne`, reafirmando a relação um-para-um.

```java
public class Payment {
  //...
  @OneToOne
  @MapsId
  private Order order;
  //...
}
```

`@MapsId` é uma anotação importante aqui. Significa que queremos mapear o ID do `Payment` para ser o mesmo que o ID do `Order`. Isso é útil, por exemplo, para evitar a necessidade de gerar uma chave primária adicional para a tabela `Payment`. Com essa anotação, a tabela `Payment` usará a mesma chave primária da tabela `Order`.

Em resumo, cada `Order` pode ter zero ou um `Payment` e vice-versa, e a chave estrangeira dessa relação está armazenada na tabela `Payment`. Além disso, `Payment` usa o mesmo ID que `Order`, tornando a relação mais eficiente em termos de armazenamento.

Essa abordagem para mapear relações entre entidades é bastante comum em aplicações de banco de dados relacional e facilita a modelagem e a manipulação de relações complexas entre entidades.

### Muitos-para-Muitos [ M:M ] <a name="relation-many-to-many"></a>

Em um relacionamento de banco de dados, **muitos-para-muitos (M:M)** ocorre quando muitas instâncias de uma entidade estão associadas a muitas instâncias de outra entidade. No modelo proposto, um produto pode pertencer a várias categorias e uma categoria pode está associada a vários produtos.

#### Class Category <a name="class-category-many-to-many"></a>

```java
@Entity
@Table(name = "tb_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();    
    //...
}
```

#### Class Product <a name="class-product-many-to-many"></a>

```java
@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double price;
    private String imgUrl;

    @ManyToMany
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    //...
}
```

Análise importante quanto a codificação da relação acima:

1. **Tabela de Associação:** No modelo relacional de banco de dados, um relacionamento **M:M** entre duas tabelas é representado por uma tabela de associação (também chamada de tabela de junção). A tabela de associação `tb_product_category` é usada para resolver o relacionamento **M:M** entre `Product` e `Category`. Ela contém as chaves estrangeiras das tabelas `Product` e `Category`, chamadas `product_id` e `category_id` respectivamente. Cada linha nesta tabela associa um produto a uma categoria.

A anotação `@JoinTable` é usada em um relacionamento de muitos-para-muitos para definir a tabela de junção (ou tabela de associação) e as chaves estrangeiras que compõem essa tabela. No modelo proposto, a tabela de junção é chamada de `tb_product_category`, que contém a associação entre produtos e categorias.

Quanto as partes da anotação `@JoinTable`:

1. **name:** o nome da tabela de junção.

2. **joinColumns:** uma ou mais anotações `@JoinColumn`, cada uma delas referindo-se a uma coluna de chave estrangeira na tabela de junção que faz referência à tabela primária dessa entidade. Nesse modelo, a tabela primária é `Product` e a chave estrangeira na tabela de junção que faz referência a `Product` é `product_id`.

3. **inverseJoinColumns:** uma ou mais anotações `@JoinColumn`, cada uma delas referindo-se a uma coluna de chave estrangeira na tabela de junção que faz referência à tabela primária da entidade no outro lado da relação. Nesse modelo, a entidade do outro lado é `Category` e a chave estrangeira na tabela de junção que faz referência a `Category` é `category_id`.

Então, em termos simples, `joinColumns` refere-se à coluna na tabela de junção que conecta a entidade onde você está declarando a anotação `@JoinTable` (neste caso, `Product`), e `inverseJoinColumns` refere-se à coluna que conecta a entidade do outro lado da relação (neste caso, `Category`).

4. **Uso de Set em vez de List:** O uso de `Set` para armazenar as associações em vez de uma `List`. A diferença principal entre `Set` e `List` é que `Set` não permite duplicatas enquanto `List` sim. Em um contexto de **M:M**, usar um `Set` faz sentido porque você não quer duplicatas na relação, ou seja, você não quer que um produto seja associado à mesma categoria mais de uma vez. 

5. **Anotações `@ManyToMany` e `@JoinTable`:** Em `Product`, a anotação `@ManyToMany` é usada para indicar que é um relacionamento **M:M**. A anotação `@JoinTable` é usada para especificar a tabela de associação e as chaves estrangeiras que compõem a tabela de associação. Em `Category`, a anotação `@ManyToMany` é novamente usada para indicar que é um relacionamento **M:M**. No entanto, também é usado `mappedBy` para indicar que o mapeamento para esta relação já foi feito na entidade `Product`.

6. **O que acontece neste tipo de relação:** Em uma relação **M:M**, quando você salva uma entidade, as associações também são salvas. Por exemplo, ao salvar um produto, também são salvas as categorias associadas a esse produto. Além disso, você pode navegar em ambas as direções. Você pode obter todas as categorias de um produto e todos os produtos de uma categoria.

**DICA!** Aqui cabe uma observação importante quanto ao campo `description` da classe `Product`:

Já sabemos qeu a anotação `@Column` é usada para especificar detalhes sobre a coluna para a propriedade ou campo persistente ao qual está aplicada. A anotação pode ser usada para todos os tipos de campos persistentes, sejam eles primitivos, wrappers, `String`, outros objetos ou coleções.

No exemplo proposto, `@Column(columnDefinition = "TEXT")` está definindo como a coluna será criada na base de dados. O valor `TEXT` dentro de `columnDefinition` é uma instrução específica para o sistema de gerenciamento de banco de dados sobre como criar essa coluna.

- `columnDefinition`: Essa opção permite a definição direta da DDL (Data Definition Language) para esta coluna no banco de dados. 

Quando o Hibernate (ou outro provedor JPA) cria as tabelas do banco de dados, ele usará o valor de `columnDefinition` para criar a coluna `description`. Nesse caso específico, a coluna `description` será criada como uma coluna `TEXT`. O tipo `TEXT` é um tipo de dado de string SQL que pode armazenar grandes quantidades de texto. O tamanho exato que um `TEXT` pode armazenar varia de um sistema de gerenciamento de banco de dados para outro, mas geralmente é considerado suficiente para armazenar texto de qualquer tamanho razoável.


## Muitos-para-Muitos [ M:M ] com classe de associação e (chave Composta) <a name="relation-many-to-many-composite-key"></a> 

Entendendo o conceito de chave composta e associação muitos-para-muitos (M:M).

A chave composta é uma chave que é composta por mais de um campo para identificar de forma única um registro em uma tabela. No contexto do JPA e Hibernate, as chaves compostas podem ser representadas através de uma classe auxiliar com a anotação `@Embeddable`, que no modelo será representado pela classe **`OrderItemPK`**.

Por outro lado, uma associação M:M ocorre quando múltiplos registros em uma tabela estão associados a múltiplos registros em outra tabela. Para efetuar essa associação, geralmente **é usada uma tabela de associação** que contém chaves estrangeiras para as tabelas que estão sendo associadas. Essa tabela de associação é justamente a representada pela entidade **`OrderItem`**.

1. Classe **`OrderItemPK`**: Esta é uma classe auxiliar que representa a chave primária composta da tabela **`tb_order_item`**. Ela possui dois atributos, **`order`** e **`product`**, que são as chaves estrangeiras para as tabelas **`Order`** e **`Product`** respectivamente. A anotação **`@ManyToOne`** indica que para um **`OrderItemPK`** há muitos pedidos (**Order**) e muitos produtos (**Product**).

2. Classe `OrderItem`: Esta é a entidade que representa a tabela de associação `tb_order_item`. A chave primária dessa entidade é do tipo `OrderItemPK` e é marcada com a anotação `@EmbeddedId`. Isso significa que a chave primária dessa entidade é um tipo incorporado, ou seja, é composta pelos campos definidos na classe `OrderItemPK`. 

3. Classe `Order`: Esta classe representa a entidade `Order` no banco de dados. O campo `items` representa a associação de um pedido com muitos itens de pedido. O mapeamento `@OneToMany(mappedBy = "id.order")` indica que a propriedade `order` dentro da chave primária incorporada `id` na entidade `OrderItem` é a que mapeia de volta para o pedido.

4. Classe `Product`: Similarmente, a classe `Product` representa a entidade `Product` no banco de dados. O campo `items` representa a associação de um produto com muitos itens de pedido. O mapeamento `@OneToMany(mappedBy = "id.product")` indica que a propriedade `product` dentro da chave primária incorporada `id` na entidade `OrderItem` é a que mapeia de volta para o produto.

Este design de tabela de associação com chave primária composta é muito comum em bases de dados relacionais quando se deseja representar relações muitos-para-muitos, pois ele permite representar a relação M:M e, além disso, armazenar dados adicionais sobre a relação, como é o caso da quantidade e preço no item do pedido.

Em resumo, a relação entre `Order`, `Product` e `OrderItem` é uma **relação muitos-para-muitos com classe de associação**, onde a chave de **`OrderItem`** é composta e montada na classe **`OrderItemPK`**, que vem das classes **`Product`** e **`Order`**.

#### 1. Class OrderItemPK <a name="class-orderitempk-many-to-many"></a>

```java
@Embeddable
public class OrderItemPK {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Getters e Setters
}
```

Nesta classe, **`@Embeddable`** indica que esta é uma classe auxiliar que será usada para compor outra entidade. A classe contém duas associações `@ManyToOne` para `Order` e `Product`, respectivamente, formando uma chave composta. Estes dois campos serão usados como uma chave primária composta na tabela de associação **`tb_order_item`**.

#### 2. Class OrderItem <a name="class-orderitem-many-to-many"></a>

```java
@Entity
@Table(name = "tb_order_item")
public class OrderItem {

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;
    private Double price;

    // Constructors, Getters e Setters
}
```

Aqui, **`OrderItem`** é a entidade que **representa a tabela de associação `tb_order_item`**. **`@EmbeddedId`** é usado para indicar que a chave primária dessa entidade é do tipo `OrderItemPK`, uma classe auxiliar incorporada.

#### 3. Class Order <a name="class-order-many-to-many-compost"></a>

```java
@Entity
@Table(name = "tb_order")
public class Order {

    // Outros campos...

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    // Constructors, Getters e Setters
}
```

Em `Order`, a anotação `@OneToMany(mappedBy = "id.order")` estabelece uma relação de um-para-muitos com `OrderItem`, indicando que um pedido (`Order`) pode ter vários itens de pedido (`OrderItem`). A propriedade `mappedBy` significa que a propriedade `order` dentro da chave primária incorporada `id` na entidade `OrderItem` é a que mapeia de volta para o pedido (`Order`).

#### 4. Class Product <a name="class-product-many-to-many-compost"></a>

```java
@Entity
@Table(name = "tb_product")
public class Product {

    // Outros campos...

    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    // Getters e Setters
}
```

Em `Product`, a anotação `@OneToMany(mappedBy = "id.product")` estabelece uma relação de um-para-muitos com `OrderItem`, indicando que um produto (`Product`) pode estar em vários itens de pedido (`OrderItem`). A propriedade `mappedBy` significa que a propriedade `product` dentro da chave primária incorporada `id` na entidade `OrderItem` é a que mapeia de volta para o produto (`Product`).

Em resumo, o design dessas classes reflete um modelo comum em bases de dados relacionais para representar relações muitos-para-muitos, com uma tabela de associação (`OrderItem`) possuindo uma chave composta (`OrderItemPK`), composta pelas chaves de duas outras tabelas (`Order` e `Product`). Isso permite não apenas a relação muitos-para-muitos entre pedidos e produtos, mas também a possibilidade de associar dados adicionais a cada par pedido-produto, como quantidade e preço.


## Tecnologias <a name="technologies"></a>

- [Java](https://www.oracle.com/br/java/)
- [Spring Boot v3.1.0](https://spring.io/projects/spring-boot)
- [H2 Database v2.1.214](https://www.h2database.com/)

## Recursos em Desenvolvimento <a name="resources"></a>

- Cadastro de usuários
- Catálogo de produtos
- Carrinho de compras
- Controle de pedidos e status
- Área administrativa

## Instalação <a name="install"></a>

Para instalar o projeto DSCommerce, siga as etapas abaixo:

1. Prepare o ambiente:
   - Certifique-se de ter o Java instalado em sua máquina. Você pode baixar o Java em [java.com](https://www.java.com/).
   - Verifique se você tem o Spring Boot v3.1.0 e o H2 Database v2.1.214 instalados. Caso contrário, você pode encontrá-los nos links mencionados anteriormente.

2. Faça o download do repositório:
   - Acesse o [repositório do DSCommerce](https://github.com/solucaoerp/JavaLaboratory/tree/main/prototypes/intermediate/dscommerce) no GitHub.
   - Clique no botão "Code" e selecione a opção de download em ZIP.
   - Extraia o arquivo ZIP em um local de sua escolha.

3. Configure o ambiente:
   - Abra o projeto DSCommerce em sua IDE favorita.
   - Certifique-se de configurar corretamente o perfil de ambiente para "test" no arquivo `application-test.properties`.
   - Verifique as configurações do banco de dados H2 e ajuste-as, se necessário, no arquivo de configuração.

4. Execute o projeto:
   - Inicie a execução do projeto DSCommerce com o perfil de ambiente "test".
   - Verifique se o aplicativo está sendo executado corretamente sem erros.

## Uso <a name="use"></a>

Após a instalação e execução do projeto DSCommerce, você pode seguir os passos abaixo para utilizá-lo:

1. Acesse o sistema:
   - Abra seu navegador de internet favorito.
   - Digite a URL do DSCommerce (dependendo da configuração local da execução do projeto).
   - Navegue pelo catálogo de produtos e utilize as funcionalidades do sistema.

2. Acesse o Banco de Dados H2:
   - Para acessar o banco de dados embutido H2, abra seu navegador de internet.
   - Digite a URL: `http://localhost:8080/h2-console`.
   - Insira as informações de login fornecidas no arquivo de configuração do perfil "test".
   - Explore as tabelas e dados do banco de dados H2 relacionados ao DSCommerce.

![H2 Database Login](https://github.com/solucaoerp/JavaLaboratory/blob/main/prototypes/intermediate/dscommerce/assets/image/login-h2-database.png)

## Licença <a name="license"></a>

Este projeto está licenciado sob os termos da [Licença MIT](https://opensource.org/licenses/MIT). A Licença MIT é uma licença de software livre e de código aberto que permite o uso, a cópia, a modificação e a distribuição do código-fonte. Esta licença é notória por sua simplicidade e flexibilidade, incentivando a colaboração e a inovação no software de código aberto.

## Contato <a name="contact"></a>

Se você tiver alguma dúvida ou precisar de suporte relacionado ao projeto DSCommerce, entre em contato com Charles através do e-mail: solucao.erp@gmail.com.

Link do Projeto (Project Link):
Acesse o [repositório do DSCommerce](https://github.com/solucaoerp/JavaLaboratory/tree/main/prototypes/intermediate/dscommerce) no GitHub para obter mais informações e acessar o código-fonte do projeto.

---

Este README será atualizado conforme o projeto evolui. Fique ligado para novas funcionalidades e melhorias!