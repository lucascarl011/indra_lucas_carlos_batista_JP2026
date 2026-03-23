package br.com.indra.lucas_carlos_batista_cp2026.repository;

import br.com.indra.lucas_carlos_batista_cp2026.model.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {

    Optional<ItemCarrinho> findByIdAndAtivoTrue(Long carrinhoId);
    List<ItemCarrinho> findByCarrinhoIdAndAtivoTrue(Long carrinhoId);
}
