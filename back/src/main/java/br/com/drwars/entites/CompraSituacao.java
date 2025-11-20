package br.com.drwars.entites;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_compra_situacoes")
public class CompraSituacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_compra_situacoes_id_compra_situacao_seq")
    @SequenceGenerator(name = "tb_compra_situacoes_id_compra_situacao_seq", sequenceName = "tb_compra_situacoes_id_compra_situacao_seq", allocationSize = 1)
    @Column(name = "id_compra_situacao")
    private Long id;

    @Column(name = "ds_compra_situacao")
    private String descricao;
}
