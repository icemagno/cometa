package br.com.drwars.v1.vo;

import br.com.drwars.entites.Representante;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class RepresentanteVO {

    private Long id;
    private String cpf;
    private String nome;
    private EnderecoVO endereco;


}
