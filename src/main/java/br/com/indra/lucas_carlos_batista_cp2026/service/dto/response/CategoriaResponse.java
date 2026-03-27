package br.com.indra.lucas_carlos_batista_cp2026.service.dto.response;

public record CategoriaResponse(

        Long id,
        String nome,
        Long categoriaPaiId,
        String categoriaPaiNome,
        Boolean ativo
) {}
