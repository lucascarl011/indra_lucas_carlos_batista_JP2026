package br.com.indra.lucas_carlos_batista_cp2026.controller;

import br.com.indra.lucas_carlos_batista_cp2026.service.CategoriaService;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.request.CategoriaRequest;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.response.CategoriaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")
@Tag(name = "Categoria", description = "Operações relacionadas às categorias de produtos")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Operation(description = "Endpoint para listar categorias", summary = "Retorna todas categorias")
    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> getAll() {
        return ResponseEntity.ok(categoriaService.getAll());
    }

    @Operation(description = "Endpoint para listar categoria por id", summary = "Retorna uma categoria")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.getById(id));
    }

    @Operation(description = "Endpoint para criar nova categoria", summary = "Criação de categoria")
    @PostMapping
    public ResponseEntity<CategoriaResponse> criarCategoria(
            @Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaService.createdCategoria(request));
    }

    @Operation(summary = "Endpoint para atualizar categoria", description = "Atualiza todos os dados de uma categoria")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> atualizarCategoria(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.ok(categoriaService.atualiza(id, request));
    }

    @Operation(summary = "Endpoint para deletar categoria", description = "Remove uma categoria pelo seu id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}