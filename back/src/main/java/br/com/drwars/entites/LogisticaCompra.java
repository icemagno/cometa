package br.com.drwars.entites;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_logistica_compras")
public class LogisticaCompra implements  Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_logistica_compras_id_logistica_compra_seq")
	@SequenceGenerator(name = "tb_logistica_compras_id_logistica_compra_seq", sequenceName = "tb_logistica_compras_id_logistica_compra_seq", allocationSize = 1)
	@Column(name = "id_logistica_compra")
	private Long id;


	@ManyToOne
	@JoinColumn(name="id_compra",referencedColumnName = "id_compra")
	private Compra compra;


	@ManyToOne
	@JoinColumn(name="id_logistica",referencedColumnName = "id_logistica")
	private Logistica logistica;

	@Column(name = "valor_logistica")
	private BigDecimal valor;

}