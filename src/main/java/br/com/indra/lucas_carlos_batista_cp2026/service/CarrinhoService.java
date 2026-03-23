package br.com.indra.lucas_carlos_batista_cp2026.service;

import br.com.indra.lucas_carlos_batista_cp2026.model.Carrinho;
import br.com.indra.lucas_carlos_batista_cp2026.repository.CarrinhoRepository;
import br.com.indra.lucas_carlos_batista_cp2026.repository.ItemCarrinhoRepository;
import br.com.indra.lucas_carlos_batista_cp2026.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ItemCarrinhoRepository itemCarrinhoRepository;
    private final ProdutosRepository produtosRepository;

    public Carrinho getCarrinho(Long usuarioId) {
        return carrinhoRepository.findByUsuarioIdAndAtivoTrue(usuarioId)
                .orElseGet(() -> {
                    Carrinho novo = new Carrinho();
                    novo.setUsuarioId(usuarioId);
                    return carrinhoRepository.save(novo);
                });
    }

}
