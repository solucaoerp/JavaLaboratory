## Características da Aplicação

---

### Camada Controller

Implementação com Optional

``` java
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Optional<Cliente> cliente = repo.findById(id);

        if (cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }
```

Implementação com Lambda Expression

``` java
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return repo.findById(id).map(cliente -> ResponseEntity.ok(cliente)).orElse(ResponseEntity.notFound().build());
    }
```

Implementação com Method Reference

``` java
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
```

---

### Camada Repository

#### Métodos customizados.

Busca Exata
``` java
    List<Cliente> findByNome(String nome);
```

Busca aproximada com Like %value%
``` java
    List<Cliente> findByNomeContaining(String nome);
```

---

#### SQL's para testes

Tabela: Cliente

```roomsql 
INSERT INTO cliente (nome, telefone, email) VALUES
('João Silva', '11999998888', 'joao.silva@gmail.com'),
('Maria Santos', '11988887777', 'maria.santos@hotmail.com'),
('Pedro Souza', '11977776666', 'pedro.souza@yahoo.com'),
('Ana Costa', '11966665555', 'ana.costa@outlook.com'),
('Lucas Oliveira', '11955554444', 'lucas.oliveira@gmail.com');

```

---

### Fontes [Referências e links úteis]

#### Documentação HTTP/Code

Aqui estão alguns links úteis que podem ser usados como referência:

- [Documentação oficial de códigos de status HTTP](https://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml)
- [Guia sobre códigos de status HTTP do MDN Web Docs](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status)
- [Lista completa de códigos de status HTTP do WebFX](https://www.webfx.com/web-development/glossary/http-status-codes/)
