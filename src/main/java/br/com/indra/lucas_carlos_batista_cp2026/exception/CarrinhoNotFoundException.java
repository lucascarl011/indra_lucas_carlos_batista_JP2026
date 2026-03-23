package br.com.indra.lucas_carlos_batista_cp2026.exception;

public class CarrinhoNotFoundException extends RuntimeException {
    public CarrinhoNotFoundException(Long usuarioId) {
        super("Carrinho não encontrado para o usuário " + usuarioId);
    }
}
