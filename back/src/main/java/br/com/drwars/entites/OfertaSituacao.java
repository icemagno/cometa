package br.com.drwars.entites;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_oferta_situacoes")
public class OfertaSituacao {

    @Id
    @Column(name = "id_oferta_situacao")
    private Long id;

    @Column(name = "ds_oferta_situacao")
    private String descricao;
}
