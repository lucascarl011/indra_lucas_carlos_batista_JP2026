# 🛒 JP Capacitação 2026 - API REST

API REST desenvolvida em Java com Spring Boot para gerenciamento de produtos, estoque, carrinho de compras e categorias.

---

## 🌿 Organização das Branches

Cada funcionalidade foi desenvolvida em uma branch separada, seguindo boas práticas de versionamento:

| Branch | Descrição |
|---|---|
| `main` | Código estável e revisado |
| `feature/estoque` | Módulo de Estoque e InventarioTransacao |
| `feature/carrinho` | Módulo Carrinho e ItemCarrinho |
| `feature/categoria` | Entidade Categoria completa |

> Após validação, cada branch foi mergeada na `main` via Pull Request.

---

## 📊 Diagrama de Entidades

![Diagrama ER](./JP2026_DIAGRAMA_DE_CLASSES.png)

---

## 🚀 Tecnologias

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- Oracle XE
- DBeaver
- Swagger / OpenAPI 3
- Postman (testes)

---

## 📦 Módulos da API

| Módulo | Descrição |
|---|---|
| Produtos | CRUD completo de produtos |
| Categorias | Gerenciamento de categorias e subcategorias |
| Estoque | Controle de entradas, saídas e devoluções |
| Carrinho | Ciclo de vida do carrinho de compras |

---

## ⭐ Implementações em Destaque

### 🗂️ Mapeamento com Stream — `HistoricoProdutoDTO`
Transformação da lista de histórico de preços utilizando `Stream.map()` e `Collectors.toList()`, retornando os dados de forma estruturada via DTO.

### 🔍 Tratamento de Erros com `orElseThrow`
Adicionado tratamento de exceção nos métodos `getById` e `atualizaPreco` do `ProdutoService`, lançando `RuntimeException` com mensagem clara caso o produto não seja encontrado.

### 🗑️ Delete Lógico em Todas as Entidades
Em vez de remover fisicamente os registros do banco, o sistema marca o registro como inativo através de um campo `ativo`. Os dados continuam existindo, mas ficam invisíveis para o sistema — garantindo rastreabilidade e integridade dos dados.

### 🏷️ Entidade Categoria Completa
Implementação do zero da entidade `Categoria` com suporte a categorias filhas, incluindo `CategoriaService`, `CategoriaRepository`, `CategoriaController` e `CategoriaNotFoundException`.

### 🛒 Módulo Carrinho + ItemCarrinho
Gerenciamento completo do ciclo de vida do carrinho de compras, com relacionamentos JPA entre `Carrinho` e `ItemCarrinho` e serialização controlada para evitar referências circulares.

### 📦 Módulo Estoque + InventarioTransacao
Controle completo do estoque com criação automática de estoque inicial, registro de entradas, saídas e devoluções, controle de estoque baixo e relacionamentos JPA com serialização controlada.

---

## 📄 Documentação da API

Com a aplicação rodando, acesse:
```
http://localhost:8080/swagger-ui.html
```

---

## ▶️ Como Executar

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/jp-capacitacao-2026

# Configure o banco de dados no application.properties

# Rode a aplicação
./mvnw spring-boot:run
```

---

## 👨‍💻 Autor

**Lucas Carlos Batista**
