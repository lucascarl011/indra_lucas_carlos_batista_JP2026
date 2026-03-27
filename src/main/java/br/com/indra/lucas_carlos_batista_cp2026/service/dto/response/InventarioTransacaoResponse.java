package br.com.indra.lucas_carlos_batista_cp2026.service.dto.response;

import br.com.indra.lucas_carlos_batista_cp2026.model.enuns.TipoTransacao;

import java.time.LocalDateTime;

public record InventarioTransacaoResponse(

        Long id,
        Long produtoId,
        String produtoNome,
        TipoTransacao tipo,
        Integer quantidade,
        String motivo,
        LocalDateTime dataTransacao
) {}
