package br.com.drwars.v1.vo.compra;


import br.com.drwars.v1.vo.EmpresaVO;
import br.com.drwars.v1.vo.NegociacaoRodadaVO;
import br.com.drwars.v1.vo.certificadora.CertificadoVO;
import br.com.drwars.v1.vo.oferta.OfertaSituacaoVO;
import br.com.drwars.v1.vo.oferta.OfertaTipoVO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CompraOfertaVO {

    private Long id;
    private OfertaTipoVO ofertaTipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OfertaTipoVO getOfertaTipo() {
		return ofertaTipo;
	}

	public void setOfertaTipo(OfertaTipoVO ofertaTipo) {
		this.ofertaTipo = ofertaTipo;
	}
}
