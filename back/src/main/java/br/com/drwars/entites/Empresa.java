package br.com.drwars.entites;

import br.com.drwars.entites.enums.SituacaoEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_empresas")
public class Empresa implements  Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_empresas_id_empresa_seq")
	@SequenceGenerator(name = "tb_empresas_id_empresa_seq", sequenceName = "tb_empresas_id_empresa_seq", allocationSize = 1)
	@Column(name = "id_empresa")
	private Long id;

	@Column(name = "cnpj", unique = true)
	private String cnpj;

	@Column(name = "razao_social")
	private String razaoSocial;

	@Column(name = "nome_fantasia")
	private String nomeFatasia;


	@ManyToOne
	@JoinColumn(name="ID_ENDERECO",referencedColumnName = "ID_ENDERECO")
	private Endereco endereco;

	@Column(name = "inscricao_estadual")
	private String inscricaoEstadual;

	@Column(name = "inscricao_municipal")
	private String inscricaoMunicipal;

	@Column(name = "nicho_mercado")
	private String nichoMercado;

	@Column(name = "servicos_produtos")
	private String servicosProdutos;

	@Column(name = "port_empr")
	private String porteEmpresa;


	@ManyToOne
	@JoinColumn(name="id_empresa_situacao",referencedColumnName = "id_empresa_situacao")
	private EmpresaSituacao empresaSituacao;

	@ManyToOne
	@JoinColumn(name="id_usuario_inclusao",referencedColumnName = "id_usuario")
	private Usuario usuarioInclusao;

	@ManyToOne
	@JoinColumn(name="id_usuario_alteracao", referencedColumnName = "id_usuario")
	private Usuario usuarioAlteracao;

	@Column(name = "dt_inclusao")
	private LocalDateTime dataInclusao;

	@Column(name = "dt_alteracao")
	private LocalDateTime dataAlteracao;

	@OneToMany(mappedBy = "empresa")
	private List<EmpresaRepresetantes> empresaRepresetantes;

	@ManyToOne
	@JoinColumn(name="id_empresa_tipo", referencedColumnName = "id_empresa_tipo")
	private EmpresaTipo empresaTipo;


}