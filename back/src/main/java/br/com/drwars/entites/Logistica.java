package br.com.drwars.entites;

import br.com.drwars.entites.enums.TipoTransportelEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_logisticas")
public class Logistica implements  Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_logisticas_id_logistica_seq")
	@SequenceGenerator(name = "tb_logisticas_id_logistica_seq", sequenceName = "tb_logisticas_id_logistica_seq", allocationSize = 1)
	@Column(name = "id_logistica")
	private Long id;

	@Column(name = "tipo_transporte")
	private TipoTransportelEnum tipoTransporte;


	@ManyToOne
	@JoinColumn(name="id_empresa_transportadora",referencedColumnName = "id_empresa")
	private Empresa empresa;




}