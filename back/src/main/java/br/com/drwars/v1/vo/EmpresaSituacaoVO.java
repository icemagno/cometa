package br.com.drwars.v1.vo;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EmpresaSituacaoVO implements  Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String descricao;

}