package br.com.indra.lucas_carlos_batista_cp2026.controller;

import br.com.indra.lucas_carlos_batista_cp2026.model.Produtos;
import br.com.indra.lucas_carlos_batista_cp2026.service.ProdutosService;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.request.ProdutoRequest;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.response.ProdutoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Operações relacionadas ao gerenciamento de produtos")
public class ProdutosController {

    private final ProdutosService produtosService;

    @Operation(description = "Endpoint para criar novo produto", summary = "Criação de produto")
    @PostMapping
    public ResponseEntity<ProdutoResponse> criarProduto(
            @Valid @RequestBody ProdutoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtosService.criarProduto(request));
    }

    @Operation(description = "Endpoint para listar produtos", summary = "Retorna todos produtos")
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> getAll() {
        return ResponseEntity.ok(produtosService.getAll());
    }

    @Operation(description = "Endpoint para listar produto por id", summary = "Retorna um produto")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(produtosService.getById(id));
    }

    @Operation(summary = "Endpoint para atualizar produto", description = "Atualiza todos os dados de um produto")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequest request) {
        return ResponseEntity.ok(produtosService.atualiza(id, request));
    }

    @Operation(summary = "Endpoint para atualizar preço", description = "Atualiza apenas o preço de um produto")
    @PatchMapping("/{id}/preco")
    public ResponseEntity<ProdutoResponse> atualizarProdutoParcial(
            @PathVariable Long id,
            @RequestParam BigDecimal preco) {
        return ResponseEntity.ok(produtosService.atualizaPreco(id, preco));
    }

    @Operation(summary = "Endpoint para deletar produto", description = "Remove um produto pelo seu id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtosService.deletaProduto(id);
        return ResponseEntity.noContent().build();
    }
}
