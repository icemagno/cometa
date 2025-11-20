package br.com.drwars.v1.vo;

import br.com.drwars.entites.Empresa;
import br.com.drwars.entites.Endereco;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocalVO extends RepresentationModel<LocalVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    @Mapping("id")
    private Long key;
    private String descricao;
    private EnderecoVO endereco;
    private String nome;
    private EmpresaVO empresa;

}
