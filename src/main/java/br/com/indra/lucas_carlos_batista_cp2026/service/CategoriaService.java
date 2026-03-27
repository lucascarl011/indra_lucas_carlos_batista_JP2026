package br.com.indra.lucas_carlos_batista_cp2026.service;

import br.com.indra.lucas_carlos_batista_cp2026.exception.CategoriaNotFoundException;
import br.com.indra.lucas_carlos_batista_cp2026.mappers.CategoriaMapper;
import br.com.indra.lucas_carlos_batista_cp2026.model.Categoria;
import br.com.indra.lucas_carlos_batista_cp2026.repository.CategoriaRepository;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.request.CategoriaRequest;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.response.CategoriaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public List<CategoriaResponse> getAll() {
        return categoriaRepository.findByAtivoTrue()
                .stream()
                .map(categoriaMapper::toResponse)
                .toList();
    }

    public CategoriaResponse getById(Long id) {
        return categoriaMapper.toResponse(
                categoriaRepository.findByIdAndAtivoTrue(id)
                        .orElseThrow(() -> new CategoriaNotFoundException(id)));
    }

    public CategoriaResponse createdCategoria(CategoriaRequest request) {
        Categoria categoria = categoriaMapper.toEntity(request);

        // busca a categoria pai se informada
        if (request.categoriaPaiId() != null) {
            Categoria pai = categoriaRepository.findByIdAndAtivoTrue(request.categoriaPaiId())
                    .orElseThrow(() -> new CategoriaNotFoundException(request.categoriaPaiId()));
            categoria.setCategoriaPai(pai);
        }

        // proibe duplicidade no mesmo nível
        if (categoriaRepository.existsByNomeAndCategoriaPai(categoria.getNome(), categoria.getCategoriaPai())) {
            throw new RuntimeException("Já existe uma categoria com esse nome nesse nível");
        }

        return categoriaMapper.toResponse(categoriaRepository.save(categoria));
    }

    public CategoriaResponse atualiza(Long id, CategoriaRequest request) {
        Categoria existente = categoriaRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));

        existente.setNome(request.nome());

        if (request.categoriaPaiId() != null) {
            Categoria pai = categoriaRepository.findByIdAndAtivoTrue(request.categoriaPaiId())
                    .orElseThrow(() -> new CategoriaNotFoundException(request.categoriaPaiId()));
            existente.setCategoriaPai(pai);
        } else {
            existente.setCategoriaPai(null);
        }

        return categoriaMapper.toResponse(categoriaRepository.save(existente));
    }

    public void deletar(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));

        categoria.setAtivo(false);
        categoriaRepository.save(categoria);
    }
}
