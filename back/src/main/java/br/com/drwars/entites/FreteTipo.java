package br.com.drwars.entites;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_frete_tipos")
public class FreteTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_frete_tipos_id_frete_tipo_seq")
    @SequenceGenerator(name = "tb_frete_tipos_id_frete_tipo_seq", sequenceName = "tb_frete_tipos_id_frete_tipo_seq", allocationSize = 1)
    @Column(name = "id_frete_tipo")
    private Long id;

    @Column(name = "sg_frete_tipo")
    private String sigla;

    @Column(name = "ds_frete_tipo")
    private String descricao;
}
