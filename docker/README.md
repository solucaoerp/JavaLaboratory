# Docker Compose

O arquivo `docker-compose.yml` é usado pelo **Docker Compose** para definir e executar múltiplos contêineres `Docker` como um único serviço. O `Docker Compose` é uma ferramenta que ajuda a orquestrar vários contêineres `Docker`, permitindo que eles sejam executados juntos.

### Entendendo cada parte do arquivo:

```yml
version: "3.7"
```

A primeira linha especifica a versão do formato do arquivo Docker Compose. A **versão 3.7** é uma versão relativamente recente que suporta a maioria dos recursos do **Docker Compose**.

```yml
services:
```

A seção `services` define os contêineres que se deseja executar. Neste caso está definindo dois serviços: `pg-docker` e `pgadmin-docker`.

```yml
pg-docker:
    image: postgres:14-alpine
    container_name: dev-postgresql
    environment:
      POSTGRES_DB: mydatabase
      POSTGRES_PASSWORD: 1234567
    ports:
      - 5433:5432
    volumes:
      - ./.data/postgresql/data:/var/lib/postgresql/data
    networks:
      - dev-network
```

Esta seção define o serviço `pg-docker`, que usa a imagem Docker `postgres:14-alpine`. Esta é uma imagem oficial do PostgreSQL, um sistema de gerenciamento de banco de dados relacional, baseada na imagem **Alpine**, que é uma imagem base leve e segura.

- `container_name`: É o nome que se deseja dar ao contêiner quando ele estiver em execução.
- `environment`: Define variáveis de ambiente dentro do contêiner. Aqui está definido o nome do banco de dados (`POSTGRES_DB`) e a senha do usuário administrador (`POSTGRES_PASSWORD`).
- `ports`: Mapeia portas entre o contêiner e o host. Aqui está mapeando a **porta 5433** do host para a **porta 5432** do contêiner (a porta padrão do PostgreSQL).
- `volumes`: Mapeia diretórios entre o contêiner e o host. Aqui está mapeando o diretório `./.data/postgresql/data` do host para o diretório `/var/lib/postgresql/data` do contêiner, que é onde o PostgreSQL armazena seus dados.
- `networks`: Especifica as redes às quais o contêiner deve se conectar. Aqui está conectando o contêiner à rede `dev-network`.

A seção `pgadmin-docker` é similar, mas define um serviço para o pgAdmin, que é uma interface gráfica para a administração do PostgreSQL.

```yml
networks:
  dev-network:
    driver: bridge
```

Finalmente, a seção `networks` define as redes a serem usadas pelos contêineres. Aqui está definido uma rede chamada `dev-network` que usa o driver `bridge`, que é o driver de rede padrão do `Docker`. Uma rede do tipo bridge permite que os contêineres conectados a ela se comuniquem entre si, enquanto os isola dos contêineres que não estão conectados à mesma rede.