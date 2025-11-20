package br.com.drwars.v1.vo.oferta;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OfertaNegociacaoPropostaVO {

    private Long id;
    private Long idItem;
    private String descricaoItem;
    private String valor;


}
