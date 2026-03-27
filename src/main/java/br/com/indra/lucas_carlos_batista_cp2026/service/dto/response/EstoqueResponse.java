package br.com.indra.lucas_carlos_batista_cp2026.service.dto.response;

public record EstoqueResponse(

        Long id,
        Long produtoId,
        String produtoNome,
        Integer quantidade,
        Integer quantidadeMinima,
        Boolean estoqueBaixo
) {}
