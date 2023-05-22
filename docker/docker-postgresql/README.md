## Docker Compose

O Compose é uma ferramenta para definir e executar aplicativos Docker de vários contêineres. Com o Compose, você usa um arquivo YAML para configurar os serviços do seu aplicativo. Então, com um único comando, você cria e inicia todos os serviços da sua configuração.[[ Documentação Oficial ]](https://docs.docker.com/compose/ "[ Documentação Oficial ]")

### Recomendação

No Docker, ao utilizar o comando `docker-compose` para criar um conjunto de contêineres com base em um arquivo de configuração, a nomenclatura e a extensão do arquivo são importantes.

O arquivo de configuração utilizado pelo `docker-compose` deve ser nomeado como `docker-compose.yml` ou `docker-compose.yaml`. Essas são as nomenclaturas padrão e esperadas pelo comando `docker-compose`. Ambas as extensões, `.yml` e `.yaml`, são aceitas.

É importante destacar que, ao executar o comando `docker-compose`, o Docker procura por um arquivo com uma dessas nomenclaturas específicas no diretório atual. Portanto, é necessário ter certeza de que o arquivo de configuração esteja corretamente nomeado como `docker-compose.yml` ou `docker-compose.yaml`.

Se você desejar utilizar um arquivo de configuração com outro nome ou localização, é possível especificar o caminho para o arquivo usando a opção `-f` ou `--file` no comando `docker-compose`, seguida pelo caminho completo para o arquivo. Por exemplo:

```yml
docker-compose -f /caminho/para/meu-arquivo.yml up -d
```

Dessa forma, você pode utilizar um arquivo de configuração com um nome diferente, mas é uma boa prática manter o padrão de nomenclatura `docker-compose.yml` para facilitar a identificação e manutenção dos seus projetos.

### Transferência de arquivos entre Host e Container Docker

Para trazer arquivos ou configurações de dentro de um contêiner Docker para o seu host local, existem algumas abordagens que você pode utilizar. Aqui estão algumas opções comuns:

1. Montagem de volumes: Ao iniciar um contêiner, você pode montar um diretório do seu host local para dentro do contêiner. Dessa forma, os arquivos criados ou modificados dentro do contêiner serão refletidos no diretório montado no host local. Você pode utilizar a opção `-v` ou `--volume` ao executar o comando `docker run` ou especificar a configuração de volumes no arquivo `docker-compose.yml`. Por exemplo:

   ```
   docker run -v /caminho/para/host/local:/caminho/para/dentro/do/contêiner <nome_do_contêiner>
   ```

   Com essa abordagem, você pode acessar os arquivos e configurações criados dentro do contêiner diretamente no diretório montado no seu host local.

2. Utilização do comando `docker cp`: O comando `docker cp` permite copiar arquivos entre o host local e o contêiner. Você pode utilizar o comando `docker cp` para copiar arquivos do contêiner para o seu host local ou vice-versa. Por exemplo, para copiar um arquivo do contêiner para o host local:

   ```
   docker cp <nome_do_contêiner>:/caminho/do/arquivo/no/contêiner /caminho/do/diretório/no/host/local
   ```

   Dessa forma, você pode extrair arquivos específicos do contêiner para o seu host local.

3. Uso de ferramentas específicas: Alguns contêineres possuem ferramentas específicas para importar ou exportar dados. Por exemplo, o MySQL tem o utilitário `mysqldump` para realizar o backup de bancos de dados. Você pode executar comandos dentro do contêiner para realizar o backup e, em seguida, copiar o arquivo resultante para o host local utilizando o `docker cp`.

   Cada ferramenta ou contêiner pode ter sua própria maneira de realizar a exportação ou backup de dados, portanto, é importante consultar a documentação específica de cada contêiner para obter instruções detalhadas.

Independentemente da abordagem escolhida, é importante entender o caminho e o local dos arquivos dentro do contêiner para poder copiá-los corretamente para o host local. Consulte a documentação e as especificações do contêiner específico que você está utilizando para obter informações mais detalhadas sobre como lidar com arquivos e configurações dentro do contêiner.

### Arquivo docker-compose

O arquivo `docker-compose.yml` é usado pelo **Docker Compose** para definir e executar múltiplos contêineres `Docker` como um único serviço. O `Docker Compose` é uma ferramenta que ajuda a orquestrar vários contêineres `Docker`, permitindo que eles sejam executados juntos.

### Entendendo as principais partes do arquivo

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

A seção `pgadmin-docker` abaixo é similar a `pg-docker` anteriormente detalhada. Ela define um serviço para o `pgAdmin`, que é uma interface gráfica para a administração do PostgreSQL.

```yml
  pgadmin-docker:
    image: dpage/pgadmin4
    container_name: dev-pgadmin
    environment:
      PGADMIN_DEFAULT_EMAIL: me@example.com
      PGADMIN_DEFAULT_PASSWORD: 1234567
    ports:
      - 5050:80
    volumes:
      - ./.data/pgadmin:/var/lib/pgadmin
    depends_on:
      - pg-docker
    networks:
      - dev-network
```

Finalmente, a seção `networks` define as redes a serem usadas pelos contêineres. Aqui está definido uma rede chamada `dev-network` que usa o driver `bridge`, que é o driver de rede padrão do `Docker`. Uma rede do tipo bridge permite que os contêineres conectados a ela se comuniquem entre si, enquanto os isola dos contêineres que não estão conectados à mesma rede.

```yml
networks:
  dev-network:
    driver: bridge
```

### Backup: passos para o backup do Banco de Dados no Container Docker para o Host Local

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


### Restore: passos para restauração do Banco de Dados no Container Docker a partir Host Local


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