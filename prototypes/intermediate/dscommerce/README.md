<div align="center">
  <h1>DSCommerce</h1>
  <img src="https://github.com/solucaoerp/JavaLaboratory/blob/main/prototypes/intermediate/dscommerce/assets/image/icon48_java.png" alt="Java">
  <img src="https://github.com/solucaoerp/JavaLaboratory/blob/main/prototypes/intermediate/dscommerce/assets/image/icon48_spring.png" alt="Spring">
  <img src="https://github.com/solucaoerp/JavaLaboratory/blob/main/prototypes/intermediate/dscommerce/assets/image/icon48_h2database.png" alt="H2Database">
</div>


DSCommerce é um sistema de comércio eletrônico desenvolvido em Java utilizando o framework `Spring Boot (v3.1.0)` com banco de dados `H2 (v2.1.214)`.


## Índice

- [Índice](#índice)
- [Visão Geral ](#visão-geral-)
- [Diagrama de Classes ](#diagrama-de-classes-)
- [Tecnologias ](#tecnologias-)
- [Recursos ](#recursos-)
- [Instalação ](#instalação-)
- [Uso ](#uso-)
- [Licença ](#licença-)
- [Contato ](#contato-)

## Visão Geral <a name = "visao-geral"></a>

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

## Diagrama de Classes <a name = "diagrama-classes"></a>

O diagrama de classes a seguir representa a estrutura do DSCommerce:

![Diagrama de Classes](https://github.com/solucaoerp/JavaLaboratory/blob/main/prototypes/intermediate/dscommerce/assets/image/use-case-diagram.png)

Ele apresenta uma visão geral de como as classes estão organizadas e interagem entre si. Isso facilita a compreensão da estrutura do código.

## Tecnologias <a name = "tecnologias"></a>

- [Java](https://www.oracle.com/br/java/)
- [Spring Boot v3.1.0](https://spring.io/projects/spring-boot)
- [H2 Database v2.1.214](https://www.h2database.com/)

## Recursos <a name = "recursos"></a>

- Cadastro de usuários
- Catálogo de produtos
- Carrinho de compras
- Controle de pedidos e status
- Área administrativa

## Instalação <a name = "instalacao"></a>

Fique ligado, em breve detalharemos as instruções para instalação e configuração do sistema.

## Uso <a name = "uso"></a>

Após a instalação, você pode acessar o sistema através do seu navegador de internet favorito. Basta digitar a URL do seu sistema e começar a navegar pelo catálogo de produtos!

## Licença <a name = "licenca"></a>

Este projeto está sob a licença do MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Contato <a name = "contato"></a>

Charles: solucao.erp@gmail.com

Link do Projeto: [DSCommerce](https://github.com/solucaoerp/JavaLaboratory/tree/main/prototypes/intermediate/dscommerce)

---

Este README será atualizado conforme o projeto evolui. Fique ligado para novas funcionalidades e melhorias!