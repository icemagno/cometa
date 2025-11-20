package br.com.drwars.v1.vo.duto;

import br.com.drwars.entites.DutoTipo;
import br.com.drwars.v1.vo.EmpresaVO;
import br.com.drwars.v1.vo.LogisticaVO;
import br.com.drwars.v1.vo.coleta.LogisticaColetaVO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LogisticaDutoVO extends RepresentationModel<LogisticaColetaVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	private DutoVO duto;
	private LogisticaVO logistica;
	private EmpresaVO empresa;
	private DutoTipoVO dutoTipo;

}