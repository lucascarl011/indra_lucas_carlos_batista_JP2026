package br.com.indra.lucas_carlos_batista_cp2026.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CancelamentoPedidoException extends RuntimeException {
    public CancelamentoPedidoException(String status) {
        super("Pedido não pode ser cancelado pois está com status: " + status);
    }
}
