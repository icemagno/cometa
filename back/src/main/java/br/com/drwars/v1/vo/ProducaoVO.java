package br.com.drwars.v1.vo;

import br.com.drwars.entites.Empresa;
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
public class ProducaoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_representantes_id_representante_seq")
    @SequenceGenerator(name = "tb_representantes_id_representante_seq", sequenceName = "tb_representantes_id_representante_seq", allocationSize = 1)
    @Column(name = "id_producao")
    private Long id;

    @Column(name = "dt_producao")
    private LocalDateTime dataProducao;

    @Column(name = "quantidade_produzida")
    private Long quantidade;

    @ManyToOne
    @JoinColumn(name="id_empresa_fornecedora",referencedColumnName = "id_empresa")
    private Empresa empresa;


}
