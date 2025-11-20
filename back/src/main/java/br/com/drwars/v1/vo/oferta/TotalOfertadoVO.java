package br.com.drwars.v1.vo.oferta;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TotalOfertadoVO {

    private Long total;
    private BigDecimal precoMedio;

}
