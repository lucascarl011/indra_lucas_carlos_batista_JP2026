package br.com.indra.lucas_carlos_batista_cp2026.service.dto.response;

import java.math.BigDecimal;

public record ProdutoResponse(

        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        String codigoBarras,
        Long categoriaId,
        String categoriaNome,
        Boolean ativo
) {}
