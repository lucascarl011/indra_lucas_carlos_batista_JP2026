package br.com.indra.lucas_carlos_batista_cp2026.repository;

import br.com.indra.lucas_carlos_batista_cp2026.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    Optional<Carrinho> findByUsuarioIdAndAtivoTrue(Long usuarioId);
}
