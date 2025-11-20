package br.com.drwars.entites;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_empresa_representantes")
public class EmpresaRepresetantes implements  Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_empresa_representantes_id_empresa_representante_seq")
	@SequenceGenerator(name = "tb_empresa_representantes_id_empresa_representante_seq", sequenceName = "tb_empresa_representantes_id_empresa_representante_seq", allocationSize = 1)
	@Column(name = "id_empresa_representante")
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_empresa",referencedColumnName = "id_empresa")
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name="id_representante",referencedColumnName = "id_representante")
	private Representante representante;

	@Column(name = "ativo")
	private Boolean situacao;

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

}