package br.com.indra.lucas_carlos_batista_cp2026.mappers;

import br.com.indra.lucas_carlos_batista_cp2026.model.Estoque;
import br.com.indra.lucas_carlos_batista_cp2026.model.InventarioTransacao;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.response.EstoqueResponse;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.response.InventarioTransacaoResponse;
import org.springframework.stereotype.Component;

@Component
public class EstoqueMapper {

    public EstoqueResponse toResponse(Estoque estoque) {
        return new EstoqueResponse(
                estoque.getId(),
                estoque.getProduto().getId(),
                estoque.getProduto().getNome(),
                estoque.getQuantidade(),
                estoque.getQuantidadeMinima(),
                estoque.getEstoqueBaixo()
        );
    }

    public InventarioTransacaoResponse toTransacaoResponse(InventarioTransacao transacao) {
        return new InventarioTransacaoResponse(
                transacao.getId(),
                transacao.getProduto().getId(),
                transacao.getProduto().getNome(),
                transacao.getTipo(),
                transacao.getQuantidade(),
                transacao.getMotivo(),
                transacao.getDataTransacao()
        );
    }
}
