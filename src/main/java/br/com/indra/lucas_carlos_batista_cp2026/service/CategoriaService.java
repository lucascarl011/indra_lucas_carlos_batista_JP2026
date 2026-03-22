package br.com.indra.lucas_carlos_batista_cp2026.service;

import br.com.indra.lucas_carlos_batista_cp2026.exception.CategoriaNotFoundException;
import br.com.indra.lucas_carlos_batista_cp2026.model.Categoria;
import br.com.indra.lucas_carlos_batista_cp2026.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> getAll() {
        return categoriaRepository.findByAtivoTrue();
    }

    public Categoria getById(Long id) {
        return categoriaRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria createdCategoria(Categoria categoria) {
        if (categoriaRepository.existsByNomeAndCategoriaPai(categoria.getNome(), categoria.getCategoriaPai())) {
            throw new RuntimeException("Já existe uma categoria cadastrada com esse nome nesse n´vel");
        }
        return categoriaRepository.save(categoria);
    }
    public Categoria atualiza(Long id, Categoria categoria) {
        Categoria categoriaExistente = categoriaRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));

        categoriaExistente.setNome(categoria.getNome());
        categoriaExistente.setCategoriaPai(categoria.getCategoriaPai());
        return categoriaRepository.save(categoriaExistente);
    }

    public void deletar(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));

        categoria.setAtivo(false); // delete logico
        categoriaRepository.save(categoria);
    }
}
