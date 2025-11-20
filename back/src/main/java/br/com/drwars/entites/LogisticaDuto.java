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
@Table(name = "tb_logistica_dutos")
public class LogisticaDuto implements  Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_logistica_dutos_id_logistica_duto_seq")
	@SequenceGenerator(name = "tb_logistica_dutos_id_logistica_duto_seq", sequenceName = "tb_logistica_dutos_id_logistica_duto_seq", allocationSize = 1)
	@Column(name = "id_logistica_duto")
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_duto",referencedColumnName = "id_duto")
	private Duto duto;

	@ManyToOne
	@JoinColumn(name="id_duto_tipo",referencedColumnName = "id_duto_tipo")
	private DutoTipo dutoTipo;

	@ManyToOne
	@JoinColumn(name="id_logistica",referencedColumnName = "id_logistica")
	private Logistica logistica;

	@ManyToOne
	@JoinColumn(name="id_empresa",referencedColumnName = "id_empresa")
	private Empresa empresa;


}