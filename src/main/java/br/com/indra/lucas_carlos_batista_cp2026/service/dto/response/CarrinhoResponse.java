package br.com.indra.lucas_carlos_batista_cp2026.service.dto.response;

import br.com.indra.lucas_carlos_batista_cp2026.model.enuns.StatusCarrinho;

import java.math.BigDecimal;
import java.util.List;

public record CarrinhoResponse(

        Long id,
        Long usuarioId,
        List<ItemCarrinhoResponse> itens,
        BigDecimal total,
        Boolean ativo,
        StatusCarrinho status
) {}
