package br.com.drwars.entites;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_negociacao_rodadas")
public class NegociacaoRodada {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_negociacao_rodadas_id_negociacao_rodada_seq")
    @SequenceGenerator(name = "tb_negociacao_rodadas_id_negociacao_rodada_seq", sequenceName = "tb_negociacao_rodadas_id_negociacao_rodada_seq", allocationSize = 1)
    @Column(name = "id_negociacao_rodada")
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_oferta",referencedColumnName = "id_oferta")
    private Oferta oferta;

    @Column(name = "nr_rodada")
    private Long rodada;

    @Column(name = "dh_rodada")
    private LocalDateTime dataRodada;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "finalizada")
    private Boolean finalizada;

    @ManyToOne
    @JoinColumn(name="id_empresa_proponente",referencedColumnName = "id_empresa")
    private Empresa empresaProponente;

    @ManyToOne
    @JoinColumn(name="id_negociacao_rodada_anterior",referencedColumnName = "id_negociacao_rodada")
    private NegociacaoRodada negociacaoRodadaAnterior;


    @OneToMany(mappedBy = "negociacaoRodada")
    private List<EntregaSazonais> listaEntregaSazonais;

    @OneToMany(mappedBy = "negociacaoRodada")
    private List<NegociacaoProposta> listaNegociacaoPropostas;

}
