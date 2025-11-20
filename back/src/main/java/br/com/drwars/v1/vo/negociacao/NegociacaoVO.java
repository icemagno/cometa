package br.com.drwars.v1.vo.negociacao;

import br.com.drwars.v1.vo.EntregaSazonalVO;
import br.com.drwars.v1.vo.NegociacaoPropostaVO;
import br.com.drwars.v1.vo.oferta.OfertaVO;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NegociacaoVO {

    private OfertaVO oferta;
    private String observacaoRodada;
    private List<NegociacaoPropostaVO> listaNegociacaoProposta;
    private List<EntregaSazonalVO> entregasSazonais;
	public OfertaVO getOferta() {
		return oferta;
	}
	public void setOferta(OfertaVO oferta) {
		this.oferta = oferta;
	}
	public String getObservacaoRodada() {
		return observacaoRodada;
	}
	public void setObservacaoRodada(String observacaoRodada) {
		this.observacaoRodada = observacaoRodada;
	}
	public List<NegociacaoPropostaVO> getListaNegociacaoProposta() {
		return listaNegociacaoProposta;
	}
	public void setListaNegociacaoProposta(List<NegociacaoPropostaVO> listaNegociacaoProposta) {
		this.listaNegociacaoProposta = listaNegociacaoProposta;
	}
	public List<EntregaSazonalVO> getEntregasSazonais() {
		return entregasSazonais;
	}
	public void setEntregasSazonais(List<EntregaSazonalVO> entregasSazonais) {
		this.entregasSazonais = entregasSazonais;
	}
    
    
    
    
}
