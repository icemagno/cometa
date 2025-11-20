package br.com.drwars.entites;


import br.com.drwars.entites.enums.OfertaTipoEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_ofertas")
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_ofertas_id_oferta_seq")
    @SequenceGenerator(name = "tb_ofertas_id_oferta_seq", sequenceName = "tb_ofertas_id_oferta_seq", allocationSize = 1)
    @Column(name = "id_oferta")
    private Long id;

    @Column(name = "dt_oferta")
    private LocalDateTime data;

    @Column(name = "tx_hash")
    private String txBlockchainHash;
    
    @Column(name = "quantidade_ofertada")
    private Long quantidade;

    @ManyToOne
    @JoinColumn(name="id_empresa",referencedColumnName = "id_empresa")
    private Empresa empresa;

    @Enumerated(EnumType.STRING)
    @Column(name = "compra_venda")
    private OfertaTipoEnum tipo;

    @ManyToOne
    @JoinColumn(name="id_oferta_situacao",referencedColumnName = "id_oferta_situacao")
    private OfertaSituacao ofertaSituacao;

    @ManyToOne
    @JoinColumn(name="id_oferta_tipo",referencedColumnName = "id_oferta_tipo")
    private OfertaTipo ofertaTipo;

    @ManyToOne
    @JoinColumn(name="id_empresa_privada",referencedColumnName = "id_empresa")
    private Empresa empresaPrivada;

    @OneToMany(mappedBy = "oferta")
    private List<NegociacaoRodada> listaNegociacaoRodada;

    @ManyToOne
    @JoinColumn(name="id_certificado",referencedColumnName = "id_certificado")
    private Certificado certificado;


    @Column(name = "quantidade_certificados")
    private Long quantidadeCertificado;

    @Column(name = "exibe_nome_empresa")
    private Boolean exibeNomeEmpresa;
    
    public void setTxBlockchainHash(String txBlockchainHash) {
		this.txBlockchainHash = txBlockchainHash;
	}
}
