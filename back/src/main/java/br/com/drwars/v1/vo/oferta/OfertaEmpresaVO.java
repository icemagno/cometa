package br.com.drwars.v1.vo.oferta;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OfertaEmpresaVO {

    private Long id;
    private String cnpj;
    private String razaoSocial;
    private String nomeFatasia;
}