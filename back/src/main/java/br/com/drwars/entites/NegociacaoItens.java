package br.com.drwars.entites;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_negociacao_itens")
public class NegociacaoItens {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_negociacao_itens_id_negociacao_item_seq")
    @SequenceGenerator(name = "tb_negociacao_itens_id_negociacao_item_seq", sequenceName = "tb_negociacao_itens_id_negociacao_item_seq", allocationSize = 1)
    @Column(name = "id_negociacao_item")
    private Long id;

    @Column(name = "ds_negociacao_item")
    private String descricao;
}
