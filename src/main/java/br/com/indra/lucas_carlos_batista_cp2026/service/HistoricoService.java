package br.com.indra.lucas_carlos_batista_cp2026.service;

import br.com.indra.lucas_carlos_batista_cp2026.model.HistoricoPreco;
import br.com.indra.lucas_carlos_batista_cp2026.repository.HistoricoPrecoRepository;
import br.com.indra.lucas_carlos_batista_cp2026.service.dto.HistoricoProdutoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoricoService {

    private final HistoricoPrecoRepository historicoPrecoRepository;

    public List<HistoricoProdutoDTO> getHistoricoByProdutoId(Long produtoId) {
        Set<HistoricoPreco> historicoPrecos = historicoPrecoRepository
                .findByProdutosId(produtoId);

        return historicoPrecos.stream()
                .map(h -> new HistoricoProdutoDTO(
                        h.getId(),
                        h.getProdutos().getNome(),
                        h.getPrecoAntigo(),
                        h.getPrecoNovo(),
                        h.getDataAlteracao()
                ))
                .collect(Collectors.toList());


    }
}
