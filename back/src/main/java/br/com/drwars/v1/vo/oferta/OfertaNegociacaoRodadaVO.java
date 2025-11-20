package br.com.drwars.v1.vo.oferta;



import br.com.drwars.v1.vo.EntregaSazonalVO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OfertaNegociacaoRodadaVO {

    private Long id;
    private Long rodada;
    private LocalDateTime dataRodada;
    private String observacao;
    private Boolean finalizada;
    private OfertaEmpresaVO empresaProponente;
    private List<EntregaSazonalVO> listaEntregaSazonais;
    private List<OfertaNegociacaoPropostaVO> listaNegociacaoPropostas;

}
