package br.com.indra.lucas_carlos_batista_cp2026.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carrinho")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "total")
    private BigDecimal total = BigDecimal.ZERO;

    @OneToMany(mappedBy = "carrinho", fetch = FetchType.EAGER)
    private List<ItemCarrinho> itens = new ArrayList<>();

    @Column(name = "ativo")
    private Boolean ativo  = true;
}
