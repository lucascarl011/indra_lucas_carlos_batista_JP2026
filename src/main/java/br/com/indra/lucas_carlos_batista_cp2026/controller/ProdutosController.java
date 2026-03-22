package br.com.indra.lucas_carlos_batista_cp2026.controller;

import br.com.indra.lucas_carlos_batista_cp2026.model.Produtos;
import br.com.indra.lucas_carlos_batista_cp2026.service.ProdutosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutosController {

    private final ProdutosService produtosService;

    @Operation(description = "Endpoint para criar novo produto", summary = "Criação de produto")

    // Esse metodo cria um novo produto
    @PostMapping("/cria")
    public ResponseEntity<Produtos> cria(@RequestBody Produtos produto) {
        return ResponseEntity.ok(ProdutosService.createdProduto(produto));
    }

    // Esse metodo lista todos os produtos criados
    @GetMapping
    public ResponseEntity<List<Produtos>> getAll(){
        return ResponseEntity.ok(produtosService.getAll());
    }

    // Esse metodo lista um produto especifico pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Produtos> getById(@PathVariable Long id){
        return ResponseEntity.ok(produtosService.getById(id));
    }

    // Esse metodo atualiza o produto
    @PutMapping("/atualiza")
    public ResponseEntity<Produtos> atualiza(@RequestParam Long id, @RequestBody Produtos produto){
        return ResponseEntity.ok(produtosService.atualiza(produto));
    }

    // Esse metodo atualiza o produto parcialmente
    @PatchMapping("/atualiza-preco/{id}")
    public ResponseEntity<Produtos> atualizaProdutoParcial(@PathVariable Long id, @RequestParam BigDecimal preco){
        return ResponseEntity.ok(produtosService.atualizaPreco(id, preco));
    }

    // Esse metodo deleta um determinado produto por id
    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<Void> deletaProduto(@PathVariable Long id){
        produtosService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
