package br.com.drwars.v1.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoVO  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codigouf;

    private String nome;

    private String uf;

    private String regiao;
}
