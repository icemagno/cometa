package br.com.drwars.entites;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_locais")
public class Local implements  Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_locais_id_local_seq")
	@SequenceGenerator(name = "tb_locais_id_local_seq", sequenceName = "tb_locais_id_local_seq", allocationSize = 1)
	@Column(name = "id_local")
	private Long id;

	@Column(name = "ds_local", unique = true)
	private String descricao;

	@ManyToOne
	@JoinColumn(name="ID_ENDERECO",referencedColumnName = "ID_ENDERECO")
	private Endereco endereco;

	@Column(name = "nm_local", unique = true)
	private String nome;

	@ManyToOne
	@JoinColumn(name="id_empresa",referencedColumnName = "id_empresa")
	private Empresa empresa;
}