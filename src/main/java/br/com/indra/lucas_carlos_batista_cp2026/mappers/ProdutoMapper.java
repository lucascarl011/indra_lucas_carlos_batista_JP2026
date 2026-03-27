package br.com.indra.lucas_carlos_batista_cp2026.mappers;

import br.com.indra.lucas_carlos_batista_cp2026.model.Produtos;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.request.ProdutoRequest;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.response.ProdutoResponse;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public ProdutoResponse toResponse(Produtos produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCodigoBarras(),
                produto.getCategoria() != null ? produto.getCategoria().getId() : null,
                produto.getCategoria() != null ? produto.getCategoria().getNome() : null,
                produto.getAtivo()
        );
    }

    public Produtos toEntity(ProdutoRequest request) {
        Produtos produto = new Produtos();
        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setCodigoBarras(request.codigoBarras());
        return produto;
    }
}
