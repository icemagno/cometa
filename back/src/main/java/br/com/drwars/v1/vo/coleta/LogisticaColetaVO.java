package br.com.drwars.v1.vo.coleta;

import br.com.drwars.v1.vo.EmpresaVO;
import br.com.drwars.v1.vo.EnderecoVO;
import br.com.drwars.v1.vo.LogisticaVO;
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
public class LogisticaColetaVO extends RepresentationModel<LogisticaColetaVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    @Mapping("id")
    private Long key;
    private String entrada;
    private EnderecoVO endereco;
    private LogisticaVO logistica;
    private EmpresaVO empresa;
}
