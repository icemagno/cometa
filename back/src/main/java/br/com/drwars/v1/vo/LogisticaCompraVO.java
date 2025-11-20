package br.com.drwars.v1.vo;

import br.com.drwars.v1.vo.compra.CompraVO;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LogisticaCompraVO implements  Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private CompraVO compra;
	private LogisticaVO logistica;
	private BigDecimal valor;

}