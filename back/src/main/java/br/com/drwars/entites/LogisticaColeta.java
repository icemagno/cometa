package br.com.drwars.entites;

import br.com.drwars.entites.enums.TipoEntradaSaidaEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_logistica_coletas")
public class LogisticaColeta implements  Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_logistica_coletas_id_logistica_coleta_seq")
	@SequenceGenerator(name = "tb_logistica_coletas_id_logistica_coleta_seq", sequenceName = "tb_logistica_coletas_id_logistica_coleta_seq", allocationSize = 1)
	@Column(name = "id_logistica_coleta")
	private Long id;

	@Column(name = "entrada")
	private TipoEntradaSaidaEnum entrada;

	@ManyToOne
	@JoinColumn(name="id_endereco",referencedColumnName = "id_endereco")
	private Endereco endereco;


	@ManyToOne
	@JoinColumn(name="id_logistica",referencedColumnName = "id_logistica")
	private Logistica logistica;

	@ManyToOne
	@JoinColumn(name="id_empresa",referencedColumnName = "id_empresa")
	private Empresa empresa;

}