#  Projeto JPA com Spring Boot  
> Repositório criado durante meus estudos no curso **Java Spring Profissional**, com foco em JPA, consultas SQL/JPQL, performance e boas práticas com Spring Data JPA.

---

##  O que estou aprendendo aqui?

Esse projeto é um laboratório prático pra entender como o **Spring Data JPA** funciona debaixo dos panos e como a gente pode tirar o máximo proveito dele na hora de trabalhar com banco de dados relacionais. Aqui vão os principais tópicos que já estudei até agora:

---

###  Sessão JPA e os Estados das Entidades

Antes de mais nada, é importante saber que as entidades no JPA passam por "estados". Elas podem estar:

- **Transient**: ainda não está ligada à sessão/persistence context.
- **Managed**: está sendo gerenciada pela JPA (pensa num "modo online").
- **Detached**: foi salva, mas não está mais conectada.
- **Removed**: marcada para ser deletada no commit.

Entender isso ajuda a evitar bugs e comportamentos estranhos nas operações com o banco.

---

###  Salvando Entidades Associadas (Um-para-Um / Um-para-Muitos)

Aqui aprendi a salvar entidades que têm relacionamentos entre si. Por exemplo:

- Uma entidade `Cliente` que tem um `Endereco` (1:1)
- Um `Produto` que pertence a várias `Categorias` (N:1 ou N:N)

Vi como usar o `CascadeType` e o `mappedBy` para configurar isso do jeito certo.

---

###  Carregamento EAGER vs LAZY

Sabe quando você busca uma entidade e ela traz outra junto? Isso é o **EAGER loading**.

Já o **LAZY loading** só carrega os dados relacionados *quando você acessa*. Isso ajuda na performance, mas pode causar erros se você não estiver atento à transação ativa (ex: `LazyInitializationException`).

---

###  Otimizando Consultas com JOIN FETCH

Descobri que dá pra evitar o famoso problema do **N+1** usando `JOIN FETCH` no JPQL. Isso melhora demais a performance e evita fazer mil queries no banco.

Exemplo de uso:

```java
@Query("SELECT p FROM Produto p JOIN FETCH p.categorias")
List<Produto> buscarComCategorias();
