package br.com.indra.lucas_carlos_batista_cp2026.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequest(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        String descricao,

        @NotNull(message = "Preço é obrigatório")
        BigDecimal preco,

        String codigoBarras,

        @NotNull(message = "Categoria é obrigatória")
        Long categoriaId
) {}
