package br.com.indra.lucas_carlos_batista_cp2026.mappers;

import br.com.indra.lucas_carlos_batista_cp2026.model.ItemPedido;
import br.com.indra.lucas_carlos_batista_cp2026.model.Pedido;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.response.ItemPedidoResponse;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.response.PedidoResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PedidoMapper {

    public PedidoResponse toResponse(Pedido pedido) {
        List<ItemPedidoResponse> itens = pedido.getItens().stream()
                .map(this::toItemResponse)
                .toList();

        return new PedidoResponse(
                pedido.getId(),
                pedido.getUsuarioId(),
                itens,
                pedido.getTotal(),
                pedido.getDesconto(),
                pedido.getFrete(),
                pedido.getStatus(),
                pedido.getCriadoEm(),
                pedido.getEndereco()
        );
    }

    public ItemPedidoResponse toItemResponse(ItemPedido item) {
        BigDecimal subtotal = item.getPrecoRegistrado()
                .multiply(BigDecimal.valueOf(item.getQuantidade()));

        return new ItemPedidoResponse(
                item.getId(),
                item.getProduto().getId(),
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getPrecoRegistrado(),
                subtotal
        );
    }
}
