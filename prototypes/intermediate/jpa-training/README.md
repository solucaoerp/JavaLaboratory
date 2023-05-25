# JPA e Hibernate Training

Este é um projeto básico e dedicado ao estudo e à implementação de aplicações utilizando JPA (Java Persistence API) e Hibernate. Ambas são tecnologias essenciais para a camada de persistência em aplicações Java, e são usadas para gerenciar dados de banco de dados relacional de forma eficaz.

A `JPA` é uma especificação padrão da linguagem Java para mapeamento objeto-relacional e gerenciamento de persistência. Ela permite que os desenvolvedores manipulem dados de bancos de dados relacional usando apenas objetos Java, abstraindo muitos detalhes tediosos e propensos a erros de manipulação de dados SQL.

O `Hibernate` é uma das implementações mais populares da JPA. Ele não apenas cumpre todos os requisitos da especificação JPA, mas também adiciona uma série de recursos que facilitam ainda mais o desenvolvimento da camada de persistência.

O objetivo principal é compreender o processo de gerenciamento de persistência em aplicações Java, o que envolve a exploração detalhada do mapeamento objeto-relacional e operações de CRUD (Create, Read, Update, Delete). Estes são os blocos de construção fundamentais de qualquer aplicação que interage com um banco de dados.

## Tópicos do Estudo

- Entendendo o contexto de persistência
- Utilização do mapa de identidade
- Aplicação do carregamento tardio (lazy loading)
- Realização do mapeamento objeto-relacional
- Compreendendo a arquitetura do JPA e Hibernate
- Configuração da JPA via arquivo 'persistence.xml'
- Realização das operações de CRUD
- Integração com o MySQL e PhpMyAdmin

## Problemas e Soluções

Ao longo do projeto estudamos a resolução de problemas como:

- Contexto de persistência: entender quais objetos estão ou não atrelados a uma conexão em um dado momento é crucial para a correta administração dos recursos do sistema.
- Mapa de identidade: trabalhar com um cache de objetos já carregados permite otimizar o desempenho da aplicação.
- Carregamento tardio (`lazy loading`): este recurso é vital para garantir que a aplicação carregue apenas os dados que são estritamente necessários para cada operação, evitando o consumo desnecessário de recursos.

## ORM - Mapeamento Objeto-Relacional

O ORM (Object-Relational Mapping) é uma técnica fundamental no desenvolvimento de aplicações que envolvem a persistência de dados. Por meio do ORM, podemos realizar a "tradução" entre a representação dos dados na aplicação (baseada em objetos) e a representação dos dados no banco (baseada em tabelas relacionais). No âmbito desse projeto foi utilizado o Hibernate, uma das mais robustas implementações de ORM disponíveis para Java.

## Dependências

Este projeto faz uso das seguintes dependências:

- [Hibernate Core 6.2.3.Final](https://mvnrepository.com/artifact/org.hibernate/hibernate-core)
- [Hibernate Entitymanager 5.6.15.Final](https://mvnrepository.com/artifact/org.hibernate/hibernate-entitymanager)
- [MySQL Connector 8.0.33](https://mvnrepository.com/artifact/mysql/mysql-connector-java)

## Arquivo 'persistence.xml'

O arquivo `persistence.xml` é o principal arquivo de configuração do JPA e é necessário para configurar um `EntityManagerFactory`. Ele define um ou mais grupos de entidades, cada um conhecido como uma unidade de persistência, e especifica configurações para eles.

Uma unidade de persistência corresponde a um conjunto de entidades que são gerenciadas em conjunto. Cada unidade de persistência tem um nome único e é associada a um `EntityManagerFactory`. Em outras palavras, cada `EntityManagerFactory` é criado a partir de uma unidade de persistência especificada no arquivo `persistence.xml`.

Dentro do `persistence.xml`, você pode definir uma variedade de configurações para a sua unidade de persistência, tais como:

- O provedor de persistência a ser usado (por exemplo, Hibernate).
- A lista de classes de entidades que fazem parte da unidade de persistência.
- As propriedades do banco de dados, como a URL do banco de dados, o nome de usuário, a senha e o dialeto do banco de dados.
- As configurações de transação e pool de conexões.

Quando você cria uma instância de `EntityManagerFactory` usando o método `Persistence.createEntityManagerFactory()`, você passa o nome da unidade de persistência como um argumento. O JPA procura no classpath um arquivo `persistence.xml` que define uma unidade de persistência com esse nome.

Depois que a `EntityManagerFactory` é criada, você pode criar instâncias de `EntityManager` chamando o método `createEntityManager()` na `EntityManagerFactory`. Cada `EntityManager` está associado a um contexto de persistência, que é um conjunto de entidades gerenciadas. O `EntityManager` é responsável por realizar operações de banco de dados nas entidades gerenciadas.

## EntityManager

O `EntityManager` é a interface principal no modelo de programação JPA. Ele é responsável por uma variedade de funções importantes, incluindo as operações CRUD (Create, Read, Update e Delete) e consultas à base de dados.

Cada instância de `EntityManager` está associada a um contexto de persistência, que é um conjunto de entidades gerenciadas pelo `EntityManager`. Uma vez que uma entidade é gerenciada, qualquer alteração feita nessa entidade será sincronizada com o banco de dados quando o contexto de persistência for liberado.

O `EntityManager` é também o ponto de entrada para iniciar e concluir transações, que são usadas para garantir a consistência dos dados ao realizar operações de banco de dados.

## EntityManagerFactory

A `EntityManagerFactory`, por outro lado, é uma interface responsável por criar instâncias de `EntityManager`. Uma aplicação normalmente tem apenas uma instância de `EntityManagerFactory` que é inicializada na inicialização da aplicação.

A `EntityManagerFactory` é thread-safe, o que significa que pode ser compartilhada por vários threads simultaneamente. No entanto, as instâncias de `EntityManager` que ela cria não são thread-safe. Em outras palavras, cada thread que precisa interagir com o banco de dados deve obter sua própria instância de `EntityManager`.

A instância de `EntityManagerFactory` pode ser configurada por meio de vários parâmetros, que podem ser especificados no arquivo `persistence.xml`. Esses parâmetros incluem, por exemplo, o provedor de persistência a ser usado (como Hibernate), a URL do banco de dados, as credenciais do banco de dados e várias outras configurações.

Por fim, é importante ressaltar que, uma vez que uma `EntityManagerFactory` tenha sido inicializada, ela deve ser explicitamente fechada para liberar seus recursos quando não for mais necessária. Isso é normalmente feito durante o encerramento da aplicação.

## Exemplo de Classe de Modelo

```java
@Entity
public class Pessoa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    
    // ... Constructor, Getter, Setter, toString(), etc...
}
```

## Implementação Básica do CRUD

### Criação (Insert)

A operação de inserção é realizada por meio do método `persist()` do objeto `EntityManager`. No exemplo abaixo, quatro instâncias da classe `Pessoa` são criadas e salvas no banco de dados.

```java
Pessoa p1 = new Pessoa(null, "Charles Borges", "charles@gmail.com");
Pessoa p2 = new Pessoa(null, "Carlos da Silva", "carlos@gmail.com");
Pessoa p3 = new Pessoa(null, "Ana Maria", "ana@gmail.com");
Pessoa p4 = new Pessoa(null, "Francisco Robson", "francisco@gmail.com");

em.getTransaction().begin();
em.persist(p1);
em.persist(p2);
em.persist(p3);
em.persist(p4);
em.getTransaction().commit();
```

### Leitura (Select)

A operação de leitura é realizada por meio do método `find()` do objeto `EntityManager`. No exemplo abaixo, a `Pessoa` com id 3 é recuperada do banco de dados.

```java
Pessoa p = em.find(Pessoa.class, 3);
```

### Atualização (Update)

Para a operação de atualização (Update), precisamos primeiro ter uma referência ao objeto que desejamos atualizar. A JPA nos permite fazer isso de duas maneiras principais:

1. Recuperando o objeto do banco de dados usando o método `find()`, modificando os campos desejados e, em seguida, confirmando a transação. Como a JPA monitora automaticamente os objetos que foram recuperados do banco de dados, qualquer alteração feita nesses objetos será automaticamente sincronizada com o banco de dados quando a transação for confirmada.

```java
// Buscando a pessoa com id 3
Pessoa p = em.find(Pessoa.class, 3);

// Iniciando a transação
em.getTransaction().begin();

// Alterando o email da Pessoa p
p.setEmail("novoemail@gmail.com");

// Confirmado a transação
em.getTransaction().commit();
```

2. Criando uma nova instância do objeto com o mesmo ID que o objeto que desejamos atualizar, modificando os campos desejados e, em seguida, mesclando o novo objeto com o objeto existente no banco de dados usando o método `merge()`. A operação `merge()` retorna uma referência ao objeto que foi realmente persistido, que é uma cópia do objeto que foi passado para `merge()`.

```java
// Criando uma nova instância da Pessoa com o mesmo ID
Pessoa p = new Pessoa(3, "Novo Nome", "novoemail@gmail.com");

// Iniciando a transação
em.getTransaction().begin();

// Mesclando o novo objeto com o objeto existente
p = em.merge(p);

// Confirmado a transação
em.getTransaction().commit();
```

### Deleção (Delete)

Para a operação de exclusão (Delete), é necessário ter uma referência ao objeto que queremos excluir. Este objeto precisa ser gerenciado (ou seja, deve estar associado ao `EntityManager`). 

Primeiramente, buscamos o objeto que queremos excluir com o método `find()`. Em seguida, usamos o método `remove()` para marcar o objeto para exclusão. Quando a transação é confirmada com o método `commit()`, o objeto é removido do banco de dados. 

Segue um exemplo de como a operação de exclusão poderia ser realizada:

```java
// Buscar a pessoa com id 3
Pessoa p = em.find(Pessoa.class, 3);

// Iniciar a transação
em.getTransaction().begin();

// Marcar a pessoa p para exclusão
em.remove(p);

// Confirmar a transação, o que causará a exclusão da pessoa p no banco de dados
em.getTransaction().commit();
```

Neste exemplo, a pessoa com id 3 é removida do banco de dados. Importante destacar que, se a pessoa com id 3 não existir no banco de dados, o método `find()` retornará `null` e uma `NullPointerException` será lançada ao chamar o método `remove()`. Portanto, é sempre bom verificar se o objeto retornado pelo método `find()` não é `null` antes de prosseguir com a operação de exclusão.

Uma maneira simples de lidar com isso é verificar se o objeto retornado pelo `find()` não é `null` antes de chamar o `remove()`. Aqui está um exemplo de como fazer:

```java
// Buscar a pessoa com id 3
Pessoa p = em.find(Pessoa.class, 3);

if (p != null) {
    // Iniciar a transação
    em.getTransaction().begin();

    // Marcar a pessoa p para exclusão
    em.remove(p);

    // Confirmar a transação, o que causará a exclusão da pessoa p no banco de dados
    em.getTransaction().commit();
} else {
    System.out.println("Não foi encontrada nenhuma pessoa com o ID fornecido.");
}
```

Desta forma, você estará seguro contra a possibilidade de um `NullPointerException`. Se o `find()` retornar `null`, a mensagem será impressa e o bloco de código dentro do `if` será ignorado.

## Importância das Transações

As transações são essenciais para manter a integridade dos dados no banco de dados. Elas garantem que uma série de operações seja executada como uma unidade de trabalho atômica, o que significa que todas as operações dentro da transação serão concluídas com sucesso ou todas falharão. No caso de falha, as operações são revertidas para o estado anterior à transação. 

No contexto da JPA, a transação é iniciada com `em.getTransaction().begin()` e confirmada com `em.getTransaction().commit()`. Se ocorrer algum problema durante a transação, você pode chamar `em.getTransaction().rollback()` para desfazer todas as alterações feitas durante a transação.

A operação `rollback()` é uma operação de controle de transação que desfaz todas as mudanças feitas à base de dados na transação atual. Ela é particularmente útil para garantir a integridade dos dados quando algo dá errado.

O método `rollback()` pode ser chamado após o método `begin()` e antes do método `commit()`. Quando chamado, desfaz todas as operações que foram realizadas na transação atual.

Exemplo do uso de `rollback()` em um cenário em que uma operação pode falhar:

```java
// Buscar a pessoa com id 3
Pessoa p = em.find(Pessoa.class, 3);

if (p != null) {
    try {
        // Iniciar a transação
        em.getTransaction().begin();

        // Operações potencialmente perigosas aqui
        // Marcar a pessoa p para exclusão
        em.remove(p);

        // Tentativa de commit da transação
        em.getTransaction().commit();
    } catch (Exception e) {
        // Se algo der errado, fazemos rollback da transação
        em.getTransaction().rollback();

        // Imprime a exceção
        e.printStackTrace();

        // Informa que uma exceção ocorreu
        System.out.println("Um erro ocorreu, a operação de remoção foi desfeita.");
    }
} else {
    System.out.println("Não foi encontrada nenhuma pessoa com o ID fornecido.");
}
```

Neste exemplo, estamos protegidos contra a possibilidade de uma exceção ocorrer durante a transação. Se uma exceção for lançada dentro do bloco `try`, o bloco `catch` será executado, o que resultará no rollback da transação e na impressão da exceção.


## Como contribuir

1. Faça um "fork" do projeto no [GitHub](https://github.com/solucaoerp/JavaLaboratory)
2. Crie uma nova branch para as suas modificações: `git checkout -b nome-da-sua-branch`
3. Salve as suas modificações e faça um commit: `git commit -m 'Adicione aqui a descrição das suas modificações'`
4. Submeta o seu commit para a sua branch: `git push origin nome-da-sua-branch`
5. Agora basta ir ao seu repositório no GitHub e clicar em `Compare & pull request`

### Créditos

Professor Nélio Alves:
- GitHub: [Aulao006](https://github.com/devsuperior/aulao006)
- YouTube: [DevSuperior](https://youtu.be/CAP1IPgeJkw)

### Contato

Para quaisquer dúvidas ou sugestões, sinta-se à vontade para nos contatar através do GitHub.

## Licença

Este projeto está licenciado sob os termos da [Licença MIT](https://opensource.org/licenses/MIT). A Licença MIT é uma licença de software livre e de código aberto que permite o uso, a cópia, a modificação e a distribuição do código-fonte. Esta licença é notória por sua simplicidade e flexibilidade, incentivando a colaboração e a inovação no software de código aberto.