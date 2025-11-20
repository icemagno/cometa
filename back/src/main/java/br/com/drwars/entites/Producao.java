package br.com.drwars.entites;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_producoes")
public class Producao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_producoes_id_producao_seq")
    @SequenceGenerator(name = "tb_producoes_id_producao_seq", sequenceName = "tb_producoes_id_producao_seq", allocationSize = 1)
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
