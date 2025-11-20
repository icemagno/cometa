package br.com.drwars.v1.vo;


import br.com.drwars.entites.Compra;
import br.com.drwars.v1.vo.compra.CompraVO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RelatorioAnualVO {
    private Long id;
    private byte[] documento;
    private LocalDateTime dataRelatorio;
    private CompraVO compra;
}
