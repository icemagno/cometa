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
@Table(name = "tb_duto_tipos")
public class DutoTipo implements  Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_duto_tipos_id_duto_tipo_seq")
	@SequenceGenerator(name = "tb_duto_tipos_id_duto_tipo_seq", sequenceName = "tb_duto_tipos_id_duto_tipo_seq", allocationSize = 1)
	@Column(name = "id_duto_tipo")
	private Long id;

	@Column(name = "sg_duto_tipo")
	private String sigla;

	@Column(name = "ds_duto_tipo")
	private String diametro;
}