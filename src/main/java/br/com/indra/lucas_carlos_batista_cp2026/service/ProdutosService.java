package br.com.indra.lucas_carlos_batista_cp2026.service;

import br.com.indra.lucas_carlos_batista_cp2026.exception.ProdutoNotFoundException;
import br.com.indra.lucas_carlos_batista_cp2026.model.HistoricoPreco;
import br.com.indra.lucas_carlos_batista_cp2026.model.Produtos;
import br.com.indra.lucas_carlos_batista_cp2026.repository.HistoricoPrecoRepository;
import br.com.indra.lucas_carlos_batista_cp2026.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutosService {

    private final ProdutosRepository produtosRepository;
    private final HistoricoPrecoRepository historicoPrecoRepository;

    public List<Produtos> getAll(){
        return produtosRepository.findByAtivoTrue(); // so produtos ativos
    }

    public Produtos getById(Long id){
        return produtosRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado ou inativo"));
    }

    public Produtos createdProduto(Produtos produto) {
        return produtosRepository.save(produto);
    }

    public Produtos atualiza(Long id, Produtos produto) {
        Produtos produtoExistente = produtosRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));

        produtoExistente.setNome(produto.getNome());
        produtoExistente.setDescricao(produto.getDescricao());
        produtoExistente.setPreco(produto.getPreco());
        produtoExistente.setCodigoBarras(produto.getCodigoBarras());

        return produtosRepository.save(produtoExistente);
    }

    public void deletaProduto(Long id) {
        Produtos produto = produtosRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));

        produto.setAtivo(false);
        produtosRepository.save(produto);
    }

    public Produtos atualizaPreco(Long id, BigDecimal preco) {
        Produtos produto = produtosRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));

        HistoricoPreco historico = new HistoricoPreco();
        historico.setPrecoAntigo(produto.getPreco());
        historico.setProdutos(produto);
        historico.setPrecoNovo(preco);
        historicoPrecoRepository.save(historico);

        produto.setPreco(preco);
        return produtosRepository.saveAndFlush(produto);
    }


}
