package br.com.indra.lucas_carlos_batista_cp2026.repository;

import br.com.indra.lucas_carlos_batista_cp2026.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> FindByAtivoTrue();
    Optional<Categoria> FindByIdAndAtivoTrue(Long id);
    boolean existsByNomeAndCategoriaPai(String nome, Categoria categoriaPai); // proibe duplicidad no mesmo nivel
}
