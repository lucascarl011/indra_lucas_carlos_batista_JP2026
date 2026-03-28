package br.com.indra.lucas_carlos_batista_cp2026.mappers;

import br.com.indra.lucas_carlos_batista_cp2026.model.Carrinho;
import br.com.indra.lucas_carlos_batista_cp2026.model.ItemCarrinho;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.response.CarrinhoResponse;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.response.ItemCarrinhoResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CarrinhoMapper {

    public CarrinhoResponse toResponse(Carrinho carrinho) {
        List<ItemCarrinhoResponse> itens = carrinho.getItens().stream()
                .filter(i -> i.getAtivo())
                .map(this::toItemResponse)
                .toList();

        return new CarrinhoResponse(
                carrinho.getId(),
                carrinho.getUsuarioId(),
                itens,
                carrinho.getTotal(),
                carrinho.getAtivo(),
                carrinho.getStatus()
        );
    }

    public ItemCarrinhoResponse toItemResponse(ItemCarrinho item) {
        BigDecimal subtotal = item.getPrecoRegistrado()
                .multiply(BigDecimal.valueOf(item.getQuantidade()));

        return new ItemCarrinhoResponse(
                item.getId(),
                item.getProduto().getId(),
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getPrecoRegistrado(),
                subtotal,
                item.getAtivo()
        );
    }
}
