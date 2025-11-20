package br.com.drwars.v1.vo.autorizadora;



import br.com.drwars.v1.vo.EmpresaVO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutorizacaoVO extends RepresentationModel<AutorizacaoVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    @Mapping("id")
    private Long key;
    private LocalDateTime dataAutorizacao;
    private LocalDateTime dataValidade;
    private EmpresaVO empresaAutorizada;
}
