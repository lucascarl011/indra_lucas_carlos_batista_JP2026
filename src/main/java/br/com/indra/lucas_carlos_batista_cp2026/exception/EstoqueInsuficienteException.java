package br.com.indra.lucas_carlos_batista_cp2026.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EstoqueInsuficienteException extends RuntimeException {
    public EstoqueInsuficienteException(Long produtoId, Integer quantidadeDisponivel) {
        super("Estoque insuficiente para o produto " + produtoId +
                ". Disponível: " + quantidadeDisponivel);
    }
}
