package br.com.indra.lucas_carlos_batista_cp2026.mappers;

import br.com.indra.lucas_carlos_batista_cp2026.model.Categoria;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.request.CategoriaRequest;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.response.CategoriaResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaResponse toResponse(Categoria categoria) {
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNome(),
                categoria.getCategoriaPai() != null ? categoria.getCategoriaPai().getId() : null,
                categoria.getCategoriaPai() != null ? categoria.getCategoriaPai().getNome() : null,
                categoria.getAtivo()
        );
    }

    public Categoria toEntity(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNome(request.nome());
        return categoria;
    }
}
