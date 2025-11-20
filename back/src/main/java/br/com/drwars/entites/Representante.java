package br.com.drwars.entites;

import br.com.drwars.entites.enums.SituacaoEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_representantes")
public class Representante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_representantes_id_representante_seq")
    @SequenceGenerator(name = "tb_representantes_id_representante_seq", sequenceName = "tb_representantes_id_representante_seq", allocationSize = 1)
    @Column(name = "id_representante")
    private Long id;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name="id_endereco",referencedColumnName = "id_endereco")
    private Endereco endereco;


}
