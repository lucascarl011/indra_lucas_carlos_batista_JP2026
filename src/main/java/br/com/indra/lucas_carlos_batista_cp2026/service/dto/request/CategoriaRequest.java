package br.com.indra.lucas_carlos_batista_cp2026.service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequest(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        Long categoriaPaiId
) {}
