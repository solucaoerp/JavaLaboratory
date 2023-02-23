-- Inserir EnderecoModel
INSERT INTO tb_endereco(rua, numero, complemento, cidade, estado) VALUES ('Rua 1', '123', 'Bloco A', 'Cidade A', 'Estado A');
INSERT INTO tb_endereco(rua, numero, complemento, cidade, estado) VALUES ('Rua 2', '456', 'Bloco B', 'Cidade B', 'Estado B');
INSERT INTO tb_endereco(rua, numero, complemento, cidade, estado) VALUES ('Rua 3', '789', 'Bloco C', 'Cidade C', 'Estado C');
INSERT INTO tb_endereco(rua, numero, complemento, cidade, estado) VALUES ('Rua 4', '101', 'Bloco D', 'Cidade D', 'Estado D');
INSERT INTO tb_endereco(rua, numero, complemento, cidade, estado) VALUES ('Rua 5', '111', 'Bloco E', 'Cidade E', 'Estado E');
INSERT INTO tb_endereco(rua, numero, complemento, cidade, estado) VALUES ('Rua 6', '222', 'Bloco F', 'Cidade F', 'Estado F');
INSERT INTO tb_endereco(rua, numero, complemento, cidade, estado) VALUES ('Rua 7', '333', 'Bloco G', 'Cidade G', 'Estado G');
INSERT INTO tb_endereco(rua, numero, complemento, cidade, estado) VALUES ('Rua 8', '444', 'Bloco H', 'Cidade H', 'Estado H');
INSERT INTO tb_endereco(rua, numero, complemento, cidade, estado) VALUES ('Rua 9', '555', 'Bloco I', 'Cidade I', 'Estado I');
INSERT INTO tb_endereco(rua, numero, complemento, cidade, estado) VALUES ('Rua 10', '666', 'Bloco J', 'Cidade J', 'Estado J');

-- Inserir ClienteModel com associação com EnderecoModel
INSERT INTO tb_cliente(nome, endereco_id) VALUES ('Fulano', 1);
INSERT INTO tb_cliente(nome, endereco_id) VALUES ('Ciclano', 2);
INSERT INTO tb_cliente(nome, endereco_id) VALUES ('Beltrano', 3);
INSERT INTO tb_cliente(nome, endereco_id) VALUES ('Siclano', 4);
INSERT INTO tb_cliente(nome, endereco_id) VALUES ('Deltrano', 5);
INSERT INTO tb_cliente(nome, endereco_id) VALUES ('Fulana', 6);
INSERT INTO tb_cliente(nome, endereco_id) VALUES ('Ciclana', 7);
INSERT INTO tb_cliente(nome, endereco_id) VALUES ('Beltrana', 8);
INSERT INTO tb_cliente(nome, endereco_id) VALUES ('Siclana', 9);
INSERT INTO tb_cliente(nome, endereco_id) VALUES ('Deltrana', 10);