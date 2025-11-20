package br.com.drwars.v1.vo.oferta;


import br.com.drwars.v1.vo.EmpresaVO;
import br.com.drwars.v1.vo.NegociacaoRodadaVO;
import br.com.drwars.v1.vo.certificadora.CertificadoVO;
import br.com.drwars.v1.vo.oferta.OfertaSituacaoVO;
import br.com.drwars.v1.vo.oferta.OfertaTipoVO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OfertaVO {

    private Long id;
    private LocalDateTime data;
    private Long quantidade;
    private String tipo;
    private EmpresaVO empresa;
    private OfertaSituacaoVO ofertaSituacao;
    private OfertaTipoVO ofertaTipo;
    private EmpresaVO empresaPrivada;
    private List<NegociacaoRodadaVO> listaNegociacaoRodada;
    private CertificadoVO certificado;
    private Long quantidadeCertificado;
    private Boolean exibeNomeEmpresa;
	private String txBlockchainHash;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public EmpresaVO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaVO empresa) {
		this.empresa = empresa;
	}
	public OfertaSituacaoVO getOfertaSituacao() {
		return ofertaSituacao;
	}
	public void setOfertaSituacao(OfertaSituacaoVO ofertaSituacao) {
		this.ofertaSituacao = ofertaSituacao;
	}
	public OfertaTipoVO getOfertaTipo() {
		return ofertaTipo;
	}
	public void setOfertaTipo(OfertaTipoVO ofertaTipo) {
		this.ofertaTipo = ofertaTipo;
	}
	public EmpresaVO getEmpresaPrivada() {
		return empresaPrivada;
	}
	public void setEmpresaPrivada(EmpresaVO empresaPrivada) {
		this.empresaPrivada = empresaPrivada;
	}
	public List<NegociacaoRodadaVO> getListaNegociacaoRodada() {
		return listaNegociacaoRodada;
	}
	public void setListaNegociacaoRodada(List<NegociacaoRodadaVO> listaNegociacaoRodada) {
		this.listaNegociacaoRodada = listaNegociacaoRodada;
	}
	public CertificadoVO getCertificado() {
		return certificado;
	}
	public void setCertificado(CertificadoVO certificado) {
		this.certificado = certificado;
	}
	public Long getQuantidadeCertificado() {
		return quantidadeCertificado;
	}
	public void setQuantidadeCertificado(Long quantidadeCertificado) {
		this.quantidadeCertificado = quantidadeCertificado;
	}
	public Boolean getExibeNomeEmpresa() {
		return exibeNomeEmpresa;
	}
	public void setExibeNomeEmpresa(Boolean exibeNomeEmpresa) {
		this.exibeNomeEmpresa = exibeNomeEmpresa;
	}

	public String getTxBlockchainHash() {
		return txBlockchainHash;
	}

	public void setTxBlockchainHash(String txBlockchainHash) {
		this.txBlockchainHash = txBlockchainHash;
	}
}
