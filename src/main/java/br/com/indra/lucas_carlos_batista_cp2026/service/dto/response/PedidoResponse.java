package br.com.indra.lucas_carlos_batista_cp2026.service.dto.response;

import br.com.indra.lucas_carlos_batista_cp2026.model.enuns.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(

        Long id,
        Long usuarioId,
        List<ItemPedidoResponse> itens,
        BigDecimal total,
        BigDecimal desconto,
        BigDecimal frete,
        StatusPedido status,
        LocalDateTime criadoEm,
        String endereco
) {}
