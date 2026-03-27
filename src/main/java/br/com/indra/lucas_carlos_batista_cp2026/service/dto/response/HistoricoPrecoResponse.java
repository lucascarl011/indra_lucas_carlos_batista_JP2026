package br.com.indra.lucas_carlos_batista_cp2026.service.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record HistoricoPrecoResponse(

        UUID id,
        Long produtoId,
        String produtoNome,
        BigDecimal precoAntigo,
        BigDecimal precoNovo,
        LocalDateTime dataAlteracao
) {
}
