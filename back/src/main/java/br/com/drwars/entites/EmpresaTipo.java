package br.com.drwars.entites;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_empresa_tipos")
public class EmpresaTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_empresa_tipos_id_empresa_tipo_seq")
    @SequenceGenerator(name = "tb_empresa_tipos_id_empresa_tipo_seq", sequenceName = "tb_empresa_tipos_id_empresa_tipo_seq", allocationSize = 1)
    @Column(name = "id_empresa_tipo")
    private Long id;


    @Column(name = "ds_empresa_tipo")
    private String descricao;
}
