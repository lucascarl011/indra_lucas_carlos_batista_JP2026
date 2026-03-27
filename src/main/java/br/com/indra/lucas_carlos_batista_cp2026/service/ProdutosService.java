package br.com.indra.lucas_carlos_batista_cp2026.service;

import br.com.indra.lucas_carlos_batista_cp2026.exception.CategoriaNotFoundException;
import br.com.indra.lucas_carlos_batista_cp2026.exception.ProdutoNotFoundException;
import br.com.indra.lucas_carlos_batista_cp2026.mappers.ProdutoMapper;
import br.com.indra.lucas_carlos_batista_cp2026.model.Categoria;
import br.com.indra.lucas_carlos_batista_cp2026.model.HistoricoPreco;
import br.com.indra.lucas_carlos_batista_cp2026.model.Produtos;
import br.com.indra.lucas_carlos_batista_cp2026.repository.CategoriaRepository;
import br.com.indra.lucas_carlos_batista_cp2026.repository.HistoricoPrecoRepository;
import br.com.indra.lucas_carlos_batista_cp2026.repository.ProdutosRepository;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.request.ProdutoRequest;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.response.ProdutoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutosService {

    private final ProdutosRepository produtosRepository;
    private final HistoricoPrecoRepository historicoPrecoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProdutoMapper produtoMapper;

    public List<ProdutoResponse> getAll() {
        return produtosRepository.findByAtivoTrue()
                .stream()
                .map(produtoMapper::toResponse)
                .toList();
    }

    public ProdutoResponse getById(Long id) {
        return produtoMapper.toResponse(
                produtosRepository.findByIdAndAtivoTrue(id)
                        .orElseThrow(() -> new ProdutoNotFoundException(id)));
    }

    public ProdutoResponse criarProduto(ProdutoRequest request) {
        Produtos produto = produtoMapper.toEntity(request);

        Categoria categoria = categoriaRepository.findByIdAndAtivoTrue(request.categoriaId())
                .orElseThrow(() -> new CategoriaNotFoundException(request.categoriaId()));

        produto.setCategoria(categoria);
        return produtoMapper.toResponse(produtosRepository.save(produto));
    }

    public ProdutoResponse atualiza(Long id, ProdutoRequest request) {
        Produtos existente = produtosRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));

        existente.setNome(request.nome());
        existente.setDescricao(request.descricao());
        existente.setPreco(request.preco());
        existente.setCodigoBarras(request.codigoBarras());

        if (request.categoriaId() != null) {
            Categoria categoria = categoriaRepository.findByIdAndAtivoTrue(request.categoriaId())
                    .orElseThrow(() -> new CategoriaNotFoundException(request.categoriaId()));
            existente.setCategoria(categoria);
        }

        return produtoMapper.toResponse(produtosRepository.save(existente));
    }

    public ProdutoResponse atualizaPreco(Long id, BigDecimal preco) {
        Produtos produto = produtosRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));

        HistoricoPreco historico = new HistoricoPreco();
        historico.setPrecoAntigo(produto.getPreco());
        historico.setProdutos(produto);
        historico.setPrecoNovo(preco);
        historicoPrecoRepository.save(historico);

        produto.setPreco(preco);
        return produtoMapper.toResponse(produtosRepository.saveAndFlush(produto));
    }

    public void deletaProduto(Long id) {
        Produtos produto = produtosRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));

        produto.setAtivo(false);
        produtosRepository.save(produto);
    }


}
