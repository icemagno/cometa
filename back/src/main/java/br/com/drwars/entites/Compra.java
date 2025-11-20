package br.com.drwars.entites;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_compras_id_compra_seq")
    @SequenceGenerator(name = "tb_compras_id_compra_seq", sequenceName = "tb_compras_id_compra_seq", allocationSize = 1)
    @Column(name = "id_compra")
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_empresa_compradora",referencedColumnName = "id_empresa")
    private Empresa empresa;

    @Column(name = "sazonalidade")
    private Boolean sazonalidade;

    @Column(name = "flexibilidade")
    private Boolean flexibilidade;

    @ManyToOne
    @JoinColumn(name="id_frete_tipo",referencedColumnName = "id_frete_tipo")
    private FreteTipo freteTipo;

    @ManyToOne
    @JoinColumn(name="id_local_entrega",referencedColumnName = "id_local")
    private Local localEntrega;

    @ManyToOne
    @JoinColumn(name="id_local_retirada",referencedColumnName = "id_local")
    private Local localRetirada;

    @Column(name = "quantidade_comprada")
    private Long quantidade;

    @Column(name = "valor_compra")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name="id_oferta",referencedColumnName = "id_oferta")
    private Oferta oferta;

    @ManyToOne
    @JoinColumn(name="id_empresa_fornecedora",referencedColumnName = "id_empresa")
    private Empresa empresaFornecedora;

    @ManyToOne
    @JoinColumn(name="id_compra_aditivada",referencedColumnName = "id_compra")
    private Compra compraAditivada;

    @ManyToOne
    @JoinColumn(name="id_compra_situacao",referencedColumnName = "id_compra_situacao")
    private CompraSituacao compraSituacao;

    @Column(name = "dt_inicio_entrega")
    private LocalDate dataInicioEntrega;

    @Column(name = "dt_fim_entrega")
    private LocalDate dataFimEntrega;

    @Column(name = "valor_certificados")
    private BigDecimal valorCertificado;


    @Column(name = "quantidade_certificados")
    private Long quantidadeCertificado;

    @Column(name = "valor_frete")
    private BigDecimal valorFrete;

}
