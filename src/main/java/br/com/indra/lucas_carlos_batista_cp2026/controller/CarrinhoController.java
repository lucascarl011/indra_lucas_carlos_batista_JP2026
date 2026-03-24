package br.com.indra.lucas_carlos_batista_cp2026.controller;

import br.com.indra.lucas_carlos_batista_cp2026.model.Carrinho;
import br.com.indra.lucas_carlos_batista_cp2026.service.CarrinhoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @Operation(description = "Endpoint para Buscar carrinho do usuario", summary = "Retorna carrinho do usuario")
    @GetMapping
    public ResponseEntity<Carrinho> getCarrinho(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(carrinhoService.getCarrinho(usuarioId));
    }

    @Operation(description = "Endpoint para adicionar itens", summary = "Adicionar item ao carrinho")
    @PostMapping("/itens")
    public ResponseEntity<Carrinho> adicionarItem(
            @RequestParam Long usuarioId,
            @RequestParam Long produtoId,
            @RequestParam Integer quantidade) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carrinhoService.adicionarItem(usuarioId, produtoId, quantidade));
    }

    @Operation(description = "Endpoint que atualiza quantidade do item", summary = "Atualizar quantidade do item")
    @PutMapping("/itens/{itemId}")
    public ResponseEntity<Carrinho> atualizarItem(
            @PathVariable Long itemId,
            @RequestParam Long usuarioId,
            @RequestParam Integer quantidade) {
        return ResponseEntity.ok(carrinhoService.atualizarItem(usuarioId, itemId, quantidade));
    }

    @Operation(description = "Endpoint que remove itens do carrinho", summary = "Remover item do carrinho")
    @DeleteMapping("/itens/{itemId}")
    public ResponseEntity<Carrinho> removerItem(
            @PathVariable Long itemId,
            @RequestParam Long usuarioId) {
        return ResponseEntity.ok(carrinhoService.removerItem(usuarioId, itemId));
    }

}
