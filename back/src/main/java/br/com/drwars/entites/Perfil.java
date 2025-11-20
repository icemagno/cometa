package br.com.drwars.entites;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_perfis")
public class Perfil implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_perfis_id_perfil_seq")
	@SequenceGenerator(name = "tb_perfis_id_perfil_seq", sequenceName = "tb_perfis_id_perfil_seq", allocationSize = 1)
	@Column(name = "id_perfil")
	private Long id;

	@Column(name = "nome_perfil")
	private String nome;

	@Column(name = "ds_perfil", unique = true)
	private String descricao;

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


	@Override
	public String getAuthority() {
		return nome.toUpperCase();
	}
}