package br.com.drwars.v1.vo.certificadora;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CertificadoVO {

    private Long id;
    private EmpresaCertificadoraVO empresa;
    private Long quantidade;
    private Long equivalencia;
    private EmpresaCertificadoraVO empresaCertificadora;
    private String numero;

}
