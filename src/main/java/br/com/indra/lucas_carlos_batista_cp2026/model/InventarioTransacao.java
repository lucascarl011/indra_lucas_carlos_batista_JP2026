package br.com.indra.lucas_carlos_batista_cp2026.model;

import br.com.indra.lucas_carlos_batista_cp2026.model.enuns.TipoTransacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventario_transacao")
public class InventarioTransacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produtos produto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoTransacao tipo;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "motivo")
    private String motivo;

    @CreationTimestamp
    @Column(name = "data_transacao", updatable = false)
    private LocalDateTime dataTransacao;
}
