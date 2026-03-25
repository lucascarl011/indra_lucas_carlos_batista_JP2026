package br.com.indra.lucas_carlos_batista_cp2026.controller;

import br.com.indra.lucas_carlos_batista_cp2026.model.Estoque;
import br.com.indra.lucas_carlos_batista_cp2026.model.InventarioTransacao;
import br.com.indra.lucas_carlos_batista_cp2026.service.EstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventario")
@Tag(name = "Estoque", description = "Operações relacionadas ao controle de estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    @Operation(summary = "Consultar estoque do produto")
    @GetMapping("/{produtoId}")
    public ResponseEntity<Estoque> getEstoque(@PathVariable Long produtoId) {
        return ResponseEntity.ok(estoqueService.getEstoque(produtoId));
    }

    @Operation(summary = "Adicionar quantidade ao estoque")
    @PostMapping("/{produtoId}/adicionar")
    public ResponseEntity<Estoque> adicionar(
            @PathVariable Long produtoId,
            @RequestParam Integer quantidade,
            @RequestParam(required = false) String motivo) {
        return ResponseEntity.ok(estoqueService.adicionar(produtoId, quantidade, motivo));
    }

    @Operation(summary = "Remover quantidade do estoque")
    @PostMapping("/{produtoId}/remover")
    public ResponseEntity<Estoque> remover(
            @PathVariable Long produtoId,
            @RequestParam Integer quantidade,
            @RequestParam(required = false) String motivo) {
        return ResponseEntity.ok(estoqueService.remover(produtoId, quantidade, motivo));
    }

    @Operation(summary = "Ajuste manual do estoque")
    @PostMapping("/{produtoId}/ajustar")
    public ResponseEntity<Estoque> ajustar(
            @PathVariable Long produtoId,
            @RequestParam Integer quantidade,
            @RequestParam(required = false) String motivo) {
        return ResponseEntity.ok(estoqueService.ajustar(produtoId, quantidade, motivo));
    }

    @Operation(summary = "Registrar devolução ao estoque")
    @PostMapping("/{produtoId}/devolver")
    public ResponseEntity<Estoque> devolver(
            @PathVariable Long produtoId,
            @RequestParam Integer quantidade,
            @RequestParam(required = false) String motivo) {
        return ResponseEntity.ok(estoqueService.devolver(produtoId, quantidade, motivo));
    }

    @Operation(summary = "Histórico de transações do produto")
    @GetMapping("/{produtoId}/historico")
    public ResponseEntity<List<InventarioTransacao>> getHistorico(@PathVariable Long produtoId) {
        return ResponseEntity.ok(estoqueService.getHistorico(produtoId));
    }
}
