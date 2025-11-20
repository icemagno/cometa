package br.com.drwars.entites;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_oferta_tipos")
public class OfertaTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_oferta_tipos_id_oferta_tipo_seq")
    @SequenceGenerator(name = "tb_oferta_tipos_id_oferta_tipo_seq", sequenceName = "tb_oferta_tipos_id_oferta_tipo_seq", allocationSize = 1)
    @Column(name = "id_oferta_tipo")
    private Long id;

    @Column(name = "ds_oferta_tipo")
    private String descricao;
}
