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

# Passos para Backup do Banco de Dados do Container Docker para o Diretório Local

Passos para fazer o backup legível do banco de dados do ambiente Docker:

Passo 1: Acesse o terminal do contêiner PostgreSQL. Abra o terminal ou prompt de comando no seu sistema operacional.

Passo 2: Use o comando `docker exec` para executar o comando `pg_dump` dentro do contêiner, e criar o backup do banco de dados em um formato legível (formato ANSI/SQL). Exemplo:

```shell
docker exec <container_name> pg_dump -U <user-db> -d <nome_do_banco> --format=plain --file=/DiretorioDockerDB/Database.sql
```

ou, caso precise passar a 'senha', acrescente o '-W' no final...

```shell
docker exec -it <container_name> pg_dump -U <user-db> -d <nome_do_banco> --format=plain --file=/DiretorioDockerDB/Database.sql -W

```

O comando acima irá criar um arquivo de backup chamado `Database.sql` dentro do contêiner no formato SQL legível.

Passo 3: Use o comando `docker cp` para copiar o arquivo de backup do contêiner para o diretório local. Exemplo:

```shell
docker cp <container_name>:/DiretorioDockerDB/Database.sql /caminho/local/para/o/Database.sql
```

Após executar esse comando, o arquivo de backup será copiado do contêiner para o diretório local especificado.

Com esses passos, você poderá fazer o backup legível do banco de dados do ambiente Docker e salvá-lo em um arquivo SQL no diretório local.

# Passos para Restauração do Banco de Dados no Container Docker a partir do Diretório Local

Passos para restaurar o banco de dados a partir do arquivo de backup.

Passo 1: Crie o banco de dados no contêiner Docker. Execute o seguinte comando para criar o banco de dados no contêiner:

```shell
docker exec -it <container_name> psql -U <usuario-db> -c "CREATE DATABASE <nome_do_banco>;"
```

Passo 2: Copie o arquivo de backup local para o contêiner PostgreSQL usando o comando `docker cp`. Por exemplo:

```shell
docker cp C:/DiretorioLocalDB/Database.sql <container_name>:/DiretorioDockerDB/Database.sql
```

Certifique-se de fornecer o caminho correto para o arquivo de backup em seu sistema local.

Passo 3: Acesse o terminal do contêiner PostgreSQL com o seguinte comando:

```shell
docker exec -it <container_name> psql -U <usuario-db> -d <nome_do_banco>
```

ou, caso precise passar a 'senha', acrescente o '-W' no final...

```shell
docker exec -it <container_name> psql -U <nome_do_usuario> -d <nome_do_banco> -W

```

Passo 4: Restaure o banco de dados a partir do arquivo de backup. Dentro do prompt interativo do PostgreSQL no contêiner, execute o seguinte comando para restaurar o banco de dados a partir do arquivo de backup:

```shell
\i /DiretorioDockerDB/Database.sql
```

Isso executará o script SQL contido no arquivo `Database.sql` e restaurará o banco de dados no contêiner.

Lembre-se de ajustar os caminhos e nomes de arquivos de acordo com a localização do seu arquivo de backup e o nome do seu contêiner PostgreSQL, se necessário.

Após a execução desses passos, o banco de dados será criado (se necessário) e o esquema e os dados das tabelas serão restaurados no contêiner Docker.