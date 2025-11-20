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
@Table(name = "tb_relatorios_anuais")
public class RelatorioAnual {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_relatorios_anuais_id_relatorio_anual_seq")
    @SequenceGenerator(name = "tb_relatorios_anuais_id_relatorio_anual_seq", sequenceName = "tb_relatorios_anuais_id_relatorio_anual_seq", allocationSize = 1)
    @Column(name = "id_relatorio_anual")
    private Long id;

    @Column(name = "arquivo_relatorio")
    private byte[] documento;

    @Column(name = "dh_relatorio_anual")
    private LocalDateTime dataRelatorio;

    @ManyToOne
    @JoinColumn(name="id_compra",referencedColumnName = "id_compra")
    private Compra compra;
}
