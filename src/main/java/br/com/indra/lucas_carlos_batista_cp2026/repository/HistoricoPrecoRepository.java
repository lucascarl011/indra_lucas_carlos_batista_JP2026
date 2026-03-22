package br.com.indra.lucas_carlos_batista_cp2026.repository;

import br.com.indra.lucas_carlos_batista_cp2026.model.HistoricoPreco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface HistoricoPrecoRepository extends JpaRepository<HistoricoPreco, UUID> {

    Set<HistoricoPreco> findByProdutosID(Long produtoid);
}
