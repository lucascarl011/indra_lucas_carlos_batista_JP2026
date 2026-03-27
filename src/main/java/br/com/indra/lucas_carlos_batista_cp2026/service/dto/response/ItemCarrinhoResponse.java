package br.com.indra.lucas_carlos_batista_cp2026.service.dto.response;

import java.math.BigDecimal;

public record ItemCarrinhoResponse(

        Long id,
        Long produtoId,
        String produtoNome,
        Integer quantidade,
        BigDecimal precoRegistrado,
        BigDecimal subtotal,
        Boolean ativo
) {}
