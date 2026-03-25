package br.com.indra.lucas_carlos_batista_cp2026.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produtos produto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade = 0;

    @Column(name = "quantidade_minima")
    private Integer quantidadeMinima = 0;

    @Column(name = "estoque_baixo")
    private Boolean estoqueBaixo = false;
}
