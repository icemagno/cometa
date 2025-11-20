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
@Table(name = "tb_dutos")
public class Duto implements  Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_dutos_id_duto_seq")
	@SequenceGenerator(name = "tb_dutos_id_duto_seq", sequenceName = "tb_dutos_id_duto_seq", allocationSize = 1)
	@Column(name = "id_duto")
	private Long id;

	@Column(name = "nome_duto")
	private String nome;

	@Column(name = "diametro_duto")
	private Double diametro;
}