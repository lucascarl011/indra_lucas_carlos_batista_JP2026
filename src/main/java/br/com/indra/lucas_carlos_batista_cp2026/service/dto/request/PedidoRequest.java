package br.com.indra.lucas_carlos_batista_cp2026.service.dto.request;

import jakarta.validation.constraints.NotNull;

public record PedidoRequest(

        @NotNull(message = "Usuário é obrigatório")
        Long usuarioId,

        String endereco,

        java.math.BigDecimal frete
) {}
