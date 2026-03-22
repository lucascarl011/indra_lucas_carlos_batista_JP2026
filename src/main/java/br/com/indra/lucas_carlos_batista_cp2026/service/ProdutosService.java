package br.com.indra.lucas_carlos_batista_cp2026.service;

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
        return produtosRepository.findAll();
    }

    public Produtos createdProduto(Produtos produto) {
        return produtosRepository.save(produto);
    }

    public Produtos atualiza(Produtos produto){
        return produtosRepository.save(produto);
    }

    public void deletaProduto(Long id){
        produtosRepository.deleteById(id);
    }

    public Produtos getById(Long id){
        return produtosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Produtos atualizaPreco(Long id, BigDecimal preco) {

        final var produto = produtosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        final var historico = new HistoricoPreco();
        historico.setPrecoAntigo(produto.getPreco());
        historico.setProdutos(produto);
        historico.setPrecoNovo(preco);
        historicoPrecoRepository.save(historico);

        produto.setPreco(preco);
        return produtosRepository.saveAndFlush(produto);
    }


}
