package br.com.drwars.entites;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_noticia_situacoes")
public class NoticiaSituacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_noticia_situacoes_id_noticia_situacao_seq")
    @SequenceGenerator(name = "tb_noticia_situacoes_id_noticia_situacao_seq", sequenceName = "tb_noticia_situacoes_id_noticia_situacao_seq", allocationSize = 1)
    @Column(name = "id_noticia_situacao")
    private Long id;

    @Column(name = "ds_noticia_situacao")
    private String descricao;
}
