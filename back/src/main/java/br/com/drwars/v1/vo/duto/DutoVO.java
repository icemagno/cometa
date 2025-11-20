package br.com.drwars.v1.vo.duto;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DutoVO implements  Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Double diametro;
}