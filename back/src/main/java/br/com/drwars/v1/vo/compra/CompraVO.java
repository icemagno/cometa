package br.com.drwars.v1.vo.compra;

import br.com.drwars.entites.Oferta;
import br.com.drwars.v1.vo.FreteTipoVO;
import br.com.drwars.v1.vo.LocalVO;
import br.com.drwars.v1.vo.oferta.OfertaVO;
import lombok.*;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CompraVO {

    private Long id;
    private CompraEmpresaVO empresa;
    private Boolean sazonalidade;
    private Boolean flexibilidade;
    private LocalVO localEntrega;
    private LocalVO localRetirada;
    private CompraSituacaoVO compraSituacao;
    private Long quantidade;
    private BigDecimal valor;
    private CompraEmpresaVO empresaFornecedora;
    private FreteTipoVO freteTipo;
    private LocalDate dataInicioEntrega;
    private LocalDate dataFimEntrega;
    private BigDecimal valorCertificado;
    private Long quantidadeCertificado;
    private CompraOfertaVO oferta;
    private BigDecimal valorFrete;

}
