package br.com.drwars.v1.vo.contrato;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ContratoItemVO {
    private Long id;
    private String descricao;
    private String valor;
}
