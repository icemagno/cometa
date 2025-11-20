package br.com.drwars.v1.vo;


import br.com.drwars.v1.vo.negociacao.NegociacaoEmpresaVO;
import br.com.drwars.v1.vo.oferta.OfertaVO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NegociacaoRodadaVO {

    private Long id;
    private OfertaVO oferta;
    private Long rodada;
    private LocalDateTime dataRodada;
    private String observacao;
    private Boolean finalizada;
    private NegociacaoEmpresaVO empresaProponente;
}
