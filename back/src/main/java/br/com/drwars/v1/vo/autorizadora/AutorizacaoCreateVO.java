package br.com.drwars.v1.vo.autorizadora;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AutorizacaoCreateVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String dataAutorizacao;
    private String dataValidade;
    private Long idEmpresaAutorizada;
}
