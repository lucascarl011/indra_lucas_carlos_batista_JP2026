package br.com.indra.lucas_carlos_batista_cp2026.service;

import br.com.indra.lucas_carlos_batista_cp2026.exception.*;
import br.com.indra.lucas_carlos_batista_cp2026.mappers.PedidoMapper;
import br.com.indra.lucas_carlos_batista_cp2026.model.*;
import br.com.indra.lucas_carlos_batista_cp2026.model.enuns.StatusCarrinho;
import br.com.indra.lucas_carlos_batista_cp2026.model.enuns.StatusPedido;
import br.com.indra.lucas_carlos_batista_cp2026.model.enuns.TipoTransacao;
import br.com.indra.lucas_carlos_batista_cp2026.repository.*;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.request.PedidoRequest;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.response.PedidoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final ItemCarrinhoRepository itemCarrinhoRepository;
    private final EstoqueRepository estoqueRepository;
    private final InventarioTransacaoRepository transacaoRepository;
    private final PedidoMapper pedidoMapper;

    public PedidoResponse criarPedido(PedidoRequest request) {
        Carrinho carrinho = carrinhoRepository.findByUsuarioIdAndAtivoTrue(request.usuarioId())
                .orElseThrow(() -> new CarrinhoNotFoundException(request.usuarioId()));

        List<ItemCarrinho> itensAtivos = itemCarrinhoRepository
                .findByCarrinhoIdAndAtivoTrue(carrinho.getId());

        if (itensAtivos.isEmpty()) {
            throw new RuntimeException("Carrinho está vazio");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuarioId(request.usuarioId());
        pedido.setEndereco(request.endereco());
        pedido.setFrete(request.frete() != null ? request.frete() : BigDecimal.ZERO);
        pedido.setDesconto(BigDecimal.ZERO);
        pedido.setStatus(StatusPedido.CRIADO);

        BigDecimal total = BigDecimal.ZERO;
        for (ItemCarrinho itemCarrinho : itensAtivos) {

            Estoque estoque = estoqueRepository
                    .findByProdutoId(itemCarrinho.getProduto().getId())
                    .orElseThrow(() -> new EstoqueInsuficienteException(
                            itemCarrinho.getProduto().getId(), 0));

            if (estoque.getQuantidade() < itemCarrinho.getQuantidade()) {
                throw new EstoqueInsuficienteException(
                        itemCarrinho.getProduto().getId(), estoque.getQuantidade());
            }

            total = total.add(itemCarrinho.getPrecoRegistrado()
                    .multiply(BigDecimal.valueOf(itemCarrinho.getQuantidade())));
        }

        pedido.setTotal(total.add(pedido.getFrete()).subtract(pedido.getDesconto()));
        pedidoRepository.save(pedido);

        for (ItemCarrinho itemCarrinho : itensAtivos) {

            Estoque estoque = estoqueRepository
                    .findByProdutoId(itemCarrinho.getProduto().getId())
                    .orElseThrow(() -> new EstoqueInsuficienteException(
                            itemCarrinho.getProduto().getId(), 0));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(itemCarrinho.getProduto());
            itemPedido.setQuantidade(itemCarrinho.getQuantidade());
            itemPedido.setPrecoRegistrado(itemCarrinho.getPrecoRegistrado());
            itemPedidoRepository.save(itemPedido);

            estoque.setQuantidade(estoque.getQuantidade() - itemCarrinho.getQuantidade());
            estoque.setEstoqueBaixo(estoque.getQuantidade() <= estoque.getQuantidadeMinima());
            estoqueRepository.save(estoque);

            InventarioTransacao transacao = new InventarioTransacao();
            transacao.setProduto(itemCarrinho.getProduto());
            transacao.setTipo(TipoTransacao.SAIDA);
            transacao.setQuantidade(itemCarrinho.getQuantidade());
            transacao.setMotivo("Venda - Pedido #" + pedido.getId());
            transacaoRepository.save(transacao);
        }

        carrinho.setAtivo(false);
        carrinho.setStatus(StatusCarrinho.FINALIZADO);
        carrinhoRepository.save(carrinho);

        return pedidoMapper.toResponse(pedidoRepository.findById(pedido.getId()).get());
    }

    public PedidoResponse getById(Long id) {
        return pedidoMapper.toResponse(
                pedidoRepository.findById(id)
                        .orElseThrow(() -> new PedidoNotFoundException(id)));
    }

    public PedidoResponse cancelar(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException(id));

        if (pedido.getStatus() != StatusPedido.CRIADO &&
                pedido.getStatus() != StatusPedido.PAGO) {
            throw new CancelamentoPedidoException(pedido.getStatus().name());
        }

        for (ItemPedido item : pedido.getItens()) {
            Estoque estoque = estoqueRepository
                    .findByProdutoId(item.getProduto().getId())
                    .orElseThrow(() -> new ProdutoNotFoundException(item.getProduto().getId()));

            estoque.setQuantidade(estoque.getQuantidade() + item.getQuantidade());
            estoque.setEstoqueBaixo(estoque.getQuantidade() <= estoque.getQuantidadeMinima());
            estoqueRepository.save(estoque);

            InventarioTransacao transacao = new InventarioTransacao();
            transacao.setProduto(item.getProduto());
            transacao.setTipo(TipoTransacao.DEVOLUCAO);
            transacao.setQuantidade(item.getQuantidade());
            transacao.setMotivo("Cancelamento - Pedido #" + pedido.getId());
            transacaoRepository.save(transacao);
        }

        pedido.setStatus(StatusPedido.CANCELADO);
        return pedidoMapper.toResponse(pedidoRepository.save(pedido));
    }
}
