package br.com.drwars.entites;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_negociacao_propostas")
public class NegociacaoProposta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_negociacao_propostas_id_negociacao_proposta_seq")
    @SequenceGenerator(name = "tb_negociacao_propostas_id_negociacao_proposta_seq", sequenceName = "tb_negociacao_propostas_id_negociacao_proposta_seq", allocationSize = 1)
    @Column(name = "id_negociacao_proposta")
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_negociacao_rodada",referencedColumnName = "id_negociacao_rodada")
    private NegociacaoRodada negociacaoRodada;

    @ManyToOne
    @JoinColumn(name="id_negociacao_item",referencedColumnName = "id_negociacao_item")
    private NegociacaoItens negociacaoItens;

    @Column(name = "valor_proposto")
    private String valor;


}
