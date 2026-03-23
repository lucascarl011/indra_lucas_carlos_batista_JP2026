package br.com.indra.lucas_carlos_batista_cp2026.service;

import br.com.indra.lucas_carlos_batista_cp2026.exception.CarrinhoNotFoundException;
import br.com.indra.lucas_carlos_batista_cp2026.exception.ItemCarrinhoNotFoundException;
import br.com.indra.lucas_carlos_batista_cp2026.exception.ProdutoNotFoundException;
import br.com.indra.lucas_carlos_batista_cp2026.model.Carrinho;
import br.com.indra.lucas_carlos_batista_cp2026.model.ItemCarrinho;
import br.com.indra.lucas_carlos_batista_cp2026.model.Produtos;
import br.com.indra.lucas_carlos_batista_cp2026.repository.CarrinhoRepository;
import br.com.indra.lucas_carlos_batista_cp2026.repository.ItemCarrinhoRepository;
import br.com.indra.lucas_carlos_batista_cp2026.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ItemCarrinhoRepository itemCarrinhoRepository;
    private final ProdutosRepository produtosRepository;

    public Carrinho getCarrinho(Long usuarioId) {
        return carrinhoRepository.findByUsuarioIdAndAtivoTrue(usuarioId)
                .orElseGet(() -> {
                    Carrinho novo = new Carrinho();
                    novo.setUsuarioId(usuarioId);
                    return carrinhoRepository.save(novo);
                });
    }

    public Carrinho adicionarItem(Long usuarioId, Long produtoId, Integer quantidade) {
        Carrinho carrinho = getCarrinho(usuarioId);

        Produtos produto = produtosRepository.findByIdAndAtivoTrue(produtoId)
                .orElseThrow(() -> new ProdutoNotFoundException(produtoId));

        ItemCarrinho item = new ItemCarrinho();
        item.setCarrinho(carrinho);
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        item.setPrecoRegistrado(produto.getPreco());
        itemCarrinhoRepository.save(item);

        recalcularTotal(carrinho);
        return carrinhoRepository.save(carrinho);
    }

    public Carrinho atualizarItem(Long usuarioId, Long itemId, Integer quantidade) {
        Carrinho carrinho = carrinhoRepository.findByUsuarioIdAndAtivoTrue(usuarioId)
                .orElseThrow(() -> new CarrinhoNotFoundException(usuarioId));

        ItemCarrinho item = itemCarrinhoRepository.findByIdAndAtivoTrue(itemId)
                .orElseThrow(() -> new ItemCarrinhoNotFoundException(itemId));

        item.setQuantidade(quantidade);
        itemCarrinhoRepository.save(item);

        recalcularTotal(carrinho);
        return carrinhoRepository.save(carrinho);
    }

    public Carrinho removerItem(Long usuarioId, Long itemId) {
        Carrinho carrinho = carrinhoRepository.findByUsuarioIdAndAtivoTrue(usuarioId)
                .orElseThrow(() -> new CarrinhoNotFoundException(usuarioId));

        ItemCarrinho item = itemCarrinhoRepository.findByIdAndAtivoTrue(itemId)
                .orElseThrow(() -> new ItemCarrinhoNotFoundException(itemId));

        item.setAtivo(false);
        itemCarrinhoRepository.save(item);

        recalcularTotal(carrinho);
        return carrinhoRepository.save(carrinho);
    }

    private void recalcularTotal(Carrinho carrinho) {
        List<ItemCarrinho> itensAtivos = itemCarrinhoRepository
                .findByCarrinhoIdAndAtivoTrue(carrinho.getId());

        BigDecimal total = itensAtivos.stream()
                .map(i -> i.getPrecoRegistrado().multiply(BigDecimal.valueOf(i.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        carrinho.setTotal(total);
    }


}
