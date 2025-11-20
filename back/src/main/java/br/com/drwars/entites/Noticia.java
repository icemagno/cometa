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
@Table(name = "tb_noticias")
public class Noticia implements  Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_noticias_id_noticia_seq")
	@SequenceGenerator(name = "tb_noticias_id_noticia_seq", sequenceName = "tb_noticias_id_noticia_seq", allocationSize = 1)
	@Column(name = "id_noticia")
	private Long id;

	@Column(name = "dt_noticia")
	private LocalDateTime data;

	@Column(name = "titulo_noticia")
	private String titulo;

	@Column(name = "texto_noticia")
	private String texto;

	@ManyToOne
	@JoinColumn(name="id_empresa",referencedColumnName = "id_empresa")
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name="id_noticia_situacao", referencedColumnName = "id_noticia_situacao")
	private NoticiaSituacao situacao;
	
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