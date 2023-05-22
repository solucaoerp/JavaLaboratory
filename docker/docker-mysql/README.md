# MySQL & PHPMyAdmin com Docker

Este repositório contém um arquivo Docker Compose para configurar um ambiente de banco de dados MySQL e PHPMyAdmin para administração do banco.

## Serviços

Este Docker Compose arquivo, define os seguintes serviços:

### db

Este é o serviço do banco de dados MySQL. Ele usa a imagem oficial do MySQL versão 5.7.

#### Configurações do serviço db

**Imagem**: `mysql:5.7`. Esta é a imagem Docker oficial para o MySQL versão 5.7.

**Volumes**: `./mysql_data:/var/lib/mysql`. Isso mapeia o diretório `mysql_data` no sistema local para o diretório `/var/lib/mysql` dentro do contêiner, o local padrão onde o MySQL armazena seus arquivos de dados.

**Restart**: `always`. Isso significa que se o contêiner for interrompido, ele será reiniciado automaticamente pelo Docker.

**Environment**: Aqui definimos várias variáveis de ambiente para configurar nosso banco de dados MySQL.

**Networks**: O serviço MySQL está na rede `mynetwork`.

### phpmyadmin

Este é o serviço para a interface web do PHPMyAdmin, que permite gerenciar facilmente o banco de dados MySQL. Ele usa a imagem oficial do PHPMyAdmin.

#### Configurações do serviço phpmyadmin

**Depends_on**: `db`. Isso significa que o serviço PHPMyAdmin depende do serviço MySQL Server.

**Image**: `phpmyadmin/phpmyadmin`. Esta é a imagem Docker oficial para o PHPMyAdmin.

**Restart**: `always`. Assim como o serviço MySQL, este serviço será sempre reiniciado se parar.

**Ports**: `8081:80`. Isso mapeia a porta 8081 no seu sistema local para a porta 80 no contêiner PHPMyAdmin.

**Environment**: Aqui definimos várias variáveis de ambiente para configurar o PHPMyAdmin.

**Networks**: O serviço PHPMyAdmin também está na rede `mynetwork`.

## Redes

Os serviços MySQL Server e PHPMyAdmin estão na mesma rede Docker (`mynetwork`). Isso permite que eles se comuniquem entre si.

## Instalação

Antes de começar, você precisa ter o Docker e o Docker Compose instalados em sua máquina. Se você ainda não instalou, você pode baixá-los e instalá-los a partir do [site oficial do Docker](https://docs.docker.com/get-docker/).

O comando abaixo inicia os serviços:

```
docker-compose up -d
```

O comando `-d` diz ao Docker Compose para iniciar os contêineres em segundo plano.

A interface do PHPMyAdmin pode ser acessada em `http://localhost:8081`. O usuário e a senha são `myUser` e `1234567`, respectivamente.

## Remoção

Para parar e remover os contêineres, a rede e os volumes definidos no arquivo compose, vá para o diretório raiz do projeto onde o arquivo `docker-compose.yml` está localizado e execute:

```shell
docker-compose down
```

Além disso, se desejar excluir os dados persistentes do MySQL, você precisa excluir o diretório de dados no host. No Windows, você pode fazer isso com o seguinte comando:

```shell
Remove-Item -Recurse -Force mysql_data
```

## Backup e Restauração

### Backup

Para fazer backup do banco de dados, primeiro você precisa executar o contêiner do banco de dados. Então, você pode usar o seguinte comando para criar um dump do banco de dados:

```shell
docker exec db /usr/bin/mysqldump -u myUser -p1234567 myDB > fileName.sql
```

Isto irá criar o arquivo `fileName.sql` no diretório atual no host.

### Restore

Para restaurar o seu banco de dados a partir do backup, primeiro certifique-se de que o contêiner do banco de dados está em execução. Então, use o seguinte comando para restaurar o banco de dados a partir do arquivo `fileName.sql`:

```shell
cat fileName.sql | docker exec -i db /usr/bin/mysql -u myUser -p1234567 myDB
```

Isso irá alimentar o arquivo `fileName.sql` para o comando mysql dentro do contêiner do banco de dados, o que irá restaurar o banco de dados.

### Considerações Finais

Este é um ambiente de desenvolvimento e não deve ser usado em produção. A configuração pode ser ajustada conforme necessário. Por exemplo, você pode querer adicionar mais serviços, como um servidor web ou um servidor de aplicativos.

Se você tiver alguma pergunta ou sugestão, sinta-se à vontade para abrir uma issue ou um pull request.