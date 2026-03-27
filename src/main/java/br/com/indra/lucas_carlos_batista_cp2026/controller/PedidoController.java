package br.com.indra.lucas_carlos_batista_cp2026.controller;

import br.com.indra.lucas_carlos_batista_cp2026.service.PedidoService;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.request.PedidoRequest;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.response.PedidoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Operações realcionadas aos pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Operation(description = "Endpoint para criar pedido a partir do carrinho", summary = "Checkout do carrin")
    @PostMapping
    public ResponseEntity<PedidoResponse> criarPedido(@Valid @RequestBody PedidoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoService.criarPedido(request));
    }

    @Operation(description = "Endpoint para buscar pedido por id", summary = "Buscar pedido")
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.getById(id));
    }

    @Operation(description = "Endpoint para cancelar pedido", summary = "Cancelar pedido")
    @PostMapping("/{id}/cancelar")
    public ResponseEntity<PedidoResponse> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.cancelar(id));
    }
}
