package br.com.indra.lucas_carlos_batista_cp2026.repository;

import br.com.indra.lucas_carlos_batista_cp2026.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
}
