package br.com.drwars.entites;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "ibge_estado")
public class Estado {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "codigouf")
    private String codigouf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "uf")
    private String uf;

    @Column(name = "regiao")
    private String regiao;

}
