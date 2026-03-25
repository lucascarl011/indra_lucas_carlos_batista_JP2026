package br.com.indra.lucas_carlos_batista_cp2026.service;

import br.com.indra.lucas_carlos_batista_cp2026.exception.EstoqueInsuficienteException;
import br.com.indra.lucas_carlos_batista_cp2026.exception.ProdutoNotFoundException;
import br.com.indra.lucas_carlos_batista_cp2026.model.Estoque;
import br.com.indra.lucas_carlos_batista_cp2026.model.InventarioTransacao;
import br.com.indra.lucas_carlos_batista_cp2026.model.Produtos;
import br.com.indra.lucas_carlos_batista_cp2026.model.TipoTransacao;
import br.com.indra.lucas_carlos_batista_cp2026.repository.EstoqueRepository;
import br.com.indra.lucas_carlos_batista_cp2026.repository.InventarioTransacaoRepository;
import br.com.indra.lucas_carlos_batista_cp2026.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final InventarioTransacaoRepository transacaoRepository;
    private final ProdutosRepository produtosRepository;

    public Estoque getEstoque(Long produtoId) {
        return estoqueRepository.findByProdutoId(produtoId)
                .orElseGet(() -> criarEstoqueInicial(produtoId));
    }

    public Estoque adicionar(Long produtoId, Integer quantidade, String motivo) {
        Estoque estoque = getEstoque(produtoId);

        estoque.setQuantidade(estoque.getQuantidade() + quantidade);
        verificarEstoqueBaixo(estoque);

        registrarTransacao(estoque.getProduto(), TipoTransacao.ENTRADA, quantidade, motivo);
        return estoqueRepository.save(estoque);
    }

    public Estoque remover(Long produtoId, Integer quantidade, String motivo) {
        Estoque estoque = getEstoque(produtoId);

        if (estoque.getQuantidade() < quantidade) {
            throw new EstoqueInsuficienteException(produtoId, estoque.getQuantidade());
        }

        estoque.setQuantidade(estoque.getQuantidade() - quantidade);
        verificarEstoqueBaixo(estoque);

        registrarTransacao(estoque.getProduto(), TipoTransacao.SAIDA, quantidade, motivo);
        return estoqueRepository.save(estoque);
    }

    public Estoque ajustar(Long produtoId, Integer novaQuantidade, String motivo) {
        Estoque estoque = getEstoque(produtoId);

        estoque.setQuantidade(novaQuantidade);
        verificarEstoqueBaixo(estoque);

        registrarTransacao(estoque.getProduto(), TipoTransacao.AJUSTE, novaQuantidade, motivo);
        return estoqueRepository.save(estoque);
    }

    public Estoque devolver(Long produtoId, Integer quantidade, String motivo) {
        Estoque estoque = getEstoque(produtoId);

        estoque.setQuantidade(estoque.getQuantidade() + quantidade);
        verificarEstoqueBaixo(estoque);

        registrarTransacao(estoque.getProduto(), TipoTransacao.DEVOLUCAO, quantidade, motivo);
        return estoqueRepository.save(estoque);
    }

    public List<InventarioTransacao> getHistorico(Long produtoId) {
        return transacaoRepository.findByProdutoId(produtoId);
    }

    private void verificarEstoqueBaixo(Estoque estoque) {
        estoque.setEstoqueBaixo(estoque.getQuantidade() <= estoque.getQuantidadeMinima());
    }

    private Estoque criarEstoqueInicial(Long produtoId) {
        Produtos produto = produtosRepository.findByIdAndAtivoTrue(produtoId)
                .orElseThrow(() -> new ProdutoNotFoundException(produtoId));

        Estoque estoque = new Estoque();
        estoque.setProduto(produto);
        estoque.setQuantidade(0);
        estoque.setQuantidadeMinima(5);
        return estoqueRepository.save(estoque);
    }

    private void registrarTransacao(Produtos produto, TipoTransacao tipo,
                                    Integer quantidade, String motivo) {
        InventarioTransacao transacao = new InventarioTransacao();
        transacao.setProduto(produto);
        transacao.setTipo(tipo);
        transacao.setQuantidade(quantidade);
        transacao.setMotivo(motivo);
        transacaoRepository.save(transacao);
    }
}
