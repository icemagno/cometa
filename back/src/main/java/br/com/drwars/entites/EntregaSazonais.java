package br.com.drwars.entites;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_entregas_sazonais")
public class EntregaSazonais {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_entregas_sazonais_id_entrega_sazonal_seq")
    @SequenceGenerator(name = "tb_entregas_sazonais_id_entrega_sazonal_seq", sequenceName = "tb_entregas_sazonais_id_entrega_sazonal_seq", allocationSize = 1)
    @Column(name = "id_entrega_sazonal")
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_negociacao_rodada", referencedColumnName = "id_negociacao_rodada")
    private NegociacaoRodada negociacaoRodada;

    @Column(name = "mes_entrega")
    private Long mesEntrega;

    @Column(name = "valor_mes")
    private BigDecimal valorMes;

}
