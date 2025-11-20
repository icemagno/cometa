package br.com.drwars.entites;

import br.com.drwars.entites.enums.SituacaoEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_empresa_situacoes")
public class EmpresaSituacao implements  Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_empresa_situacoes_id_empresa_situacao_seq")
	@SequenceGenerator(name = "tb_empresa_situacoes_id_empresa_situacao_seq", sequenceName = "tb_empresa_situacoes_id_empresa_situacao_seq", allocationSize = 1)
	@Column(name = "id_empresa_situacao")
	private Long id;

	@Column(name = "DS_EMPRESA_SITUACAO")
	private String descricao;

}