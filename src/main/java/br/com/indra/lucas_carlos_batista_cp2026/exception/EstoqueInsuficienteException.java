package br.com.indra.lucas_carlos_batista_cp2026.exception;

public class EstoqueInsuficienteException extends RuntimeException {

    public EstoqueInsuficienteException(Long produtoId, Integer quantidadeDisponivel) {
        super("Estoque insuficiente para o produto " + produtoId +
                ". Disponível: " + quantidadeDisponivel);
    }
}
