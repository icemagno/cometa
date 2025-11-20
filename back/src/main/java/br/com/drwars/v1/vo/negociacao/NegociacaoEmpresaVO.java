package br.com.drwars.v1.vo.negociacao;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NegociacaoEmpresaVO {

    private Long id;
    private String cnpj;
    private String razaoSocial;
    private String nomeFatasia;
}