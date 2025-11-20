package br.com.drwars.v1.vo;

import br.com.drwars.entites.Empresa;
import br.com.drwars.entites.enums.TipoTransportelEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LogisticaVO implements  Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private TipoTransportelEnum tipoTransporte;
	private EmpresaVO empresa;
	private Long idEmpresa;



}