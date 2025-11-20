package br.com.drwars.entites;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_contrato_itens")
public class ContratoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_contrato_itens_id_contrato_item_seq")
    @SequenceGenerator(name = "tb_contrato_itens_id_contrato_item_seq", sequenceName = "tb_contrato_itens_id_contrato_item_seq", allocationSize = 1)
    @Column(name = "id_contrato_item")
    private Long id;

    @Column(name = "ds_contrato_item")
    private String descricao;

}
