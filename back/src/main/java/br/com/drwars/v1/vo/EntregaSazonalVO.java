package br.com.drwars.v1.vo;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EntregaSazonalVO {


    private Long mes;
    private String valorMes;
}
