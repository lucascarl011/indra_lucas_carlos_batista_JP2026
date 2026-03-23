package br.com.indra.lucas_carlos_batista_cp2026.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemCarrinhoNotFoundException extends RuntimeException {
    public ItemCarrinhoNotFoundException(Long id) {
        super("Item com id " + id + " não encontrado no carrinho");
    }
}
