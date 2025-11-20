package br.com.drwars.entites;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_contrato_dados")
public class ContratoDado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_contrato_dados_id_contrato_dado_seq")
    @SequenceGenerator(name = "tb_contrato_dados_id_contrato_dado_seq", sequenceName = "tb_contrato_dados_id_contrato_dado_seq", allocationSize = 1)
    @Column(name = "id_contrato_dado")
    private Long id;

    @Column(name = "valor_contrato_dado")
    private String valor;


    @ManyToOne
    @JoinColumn(name="id_contrato",referencedColumnName = "id_contrato")
    private Contrato contrato;


    @ManyToOne
    @JoinColumn(name="id_contrato_item",referencedColumnName = "id_contrato_item")
    private ContratoItem contratoItem;
}
