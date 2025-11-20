package br.com.drwars.v1.vo;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EntregaSazonaisVO {

    private Long id;
    private NegociacaoRodadaVO negociacaoRodada;
    private Long mesEntrega;
    private BigDecimal valorMes;
    private Long anoEntrega;
}
