package br.com.drwars.v1.vo.certificadora;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmpresaCertificadoraVO {

    private Long id;
    private String cnpj;
    private String razaoSocial;
    private String nomeFatasia;
}