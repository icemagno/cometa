package br.com.drwars.v1.vo;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NegociacaoItensVO {

    private Long id;
    private String descricao;
    private String valor;

}
