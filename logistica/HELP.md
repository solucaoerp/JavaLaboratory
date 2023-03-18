## Documentação HTTP/Code

### Características do Controller

Retorno tradicional (Funcional) com Optional

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

Retorno elegante (Funcional) com Optional e Lambda Expression

``` java
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return repo.findById(id).map(cliente -> ResponseEntity.ok(cliente)).orElse(ResponseEntity.notFound().build());
    }
```

### Características do Repository

#### Métodos customizados.

Busca Exata
``` java
    List<Cliente> findByNome(String nome);
```

Busca aproximada com Like %value%
``` java
    List<Cliente> findByNomeContaining(String nome);
```


### Referências e links úteis

Aqui estão alguns links úteis que podem ser usados como referência:

- [Documentação oficial de códigos de status HTTP](https://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml)
- [Guia sobre códigos de status HTTP do MDN Web Docs](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status)
- [Lista completa de códigos de status HTTP do WebFX](https://www.webfx.com/web-development/glossary/http-status-codes/)
