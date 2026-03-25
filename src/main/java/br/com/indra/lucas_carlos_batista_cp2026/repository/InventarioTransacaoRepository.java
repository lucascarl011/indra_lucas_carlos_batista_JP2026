package br.com.indra.lucas_carlos_batista_cp2026.repository;

import br.com.indra.lucas_carlos_batista_cp2026.model.InventarioTransacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventarioTransacaoRepository extends JpaRepository<InventarioTransacao, Long> {

    List<InventarioTransacao> findByProdutoId(Long produtoId);
}
