package br.com.drwars.v1.vo.contrato;

import br.com.drwars.v1.vo.compra.CompraVO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ContratoVO {

    private Long id;
    private byte[] documento;
    private CompraVO compra;
    private List<ContratoItemVO> contratoItems;
}
