package br.com.drwars.v1.vo.oferta;


import br.com.drwars.entites.*;
import br.com.drwars.v1.vo.EntregaSazonalVO;
import br.com.drwars.v1.vo.NegociacaoPropostaVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OfertaNegociacaoVO {

    private Long id;
    private LocalDateTime data;
    private Long quantidade;
    private OfertaEmpresaVO empresa;
    private OfertaSituacaoVO ofertaSituacao;
    private OfertaTipoVO ofertaTipo;
    private OfertaEmpresaVO empresaPrivada;
    private List<OfertaNegociacaoRodadaVO> listaNegociacaoRodada;
    private Boolean exibeNomeEmpresa;
    private String tipo;
    private String txBlockchainHash;

    @JsonIgnore
    public OfertaNegociacaoVO(Oferta oferta) {
        this.id = oferta.getId();
        this.data = oferta.getData();
        this.quantidade = oferta.getQuantidade();
        this.empresa = getOfertaEmpresaVO(oferta.getEmpresa());
        this.ofertaSituacao = getOfertaSituacaoVO(oferta.getOfertaSituacao());
        this.ofertaTipo= getOfertaTipoVO(oferta.getOfertaTipo());
        if(oferta.getEmpresaPrivada()!=null)
            this.empresaPrivada= getOfertaEmpresaVO(oferta.getEmpresaPrivada());

        this.listaNegociacaoRodada = getListOfertaNegociacaoRodadaVO(oferta.getListaNegociacaoRodada());
        this.exibeNomeEmpresa = oferta.getExibeNomeEmpresa();
        this.tipo = oferta.getTipo().toString();
        this.txBlockchainHash=oferta.getTxBlockchainHash();
    }
    private OfertaEmpresaVO getOfertaEmpresaVO(Empresa ofertaEmpresa){
        var empresa = new OfertaEmpresaVO();
        empresa.setId(ofertaEmpresa.getId());
        empresa.setCnpj(ofertaEmpresa.getCnpj());
        empresa.setNomeFatasia(ofertaEmpresa.getNomeFatasia());
        empresa.setRazaoSocial(ofertaEmpresa.getRazaoSocial());
        return  empresa;
    }

    private OfertaTipoVO getOfertaTipoVO(OfertaTipo ofertaTipo){
        var  vo = new OfertaTipoVO();
        vo.setId(ofertaTipo.getId());
        vo.setDescricao(ofertaTipo.getDescricao());
        return vo;
    }

    private OfertaSituacaoVO getOfertaSituacaoVO(OfertaSituacao ofertaSituacao){
        var  vo = new OfertaSituacaoVO();
        vo.setId(ofertaSituacao.getId());
        vo.setDescricao(ofertaSituacao.getDescricao());
        return vo;
    }

    private List<OfertaNegociacaoRodadaVO>  getListOfertaNegociacaoRodadaVO(List<NegociacaoRodada> lista){
        if(lista==null
                || lista.isEmpty()){
            return null;
        }
        var listaVO = new ArrayList<OfertaNegociacaoRodadaVO>();
        for (NegociacaoRodada n : lista) {
           var vo = new OfertaNegociacaoRodadaVO();
           vo.setId(n.getId());
           vo.setRodada(n.getRodada());
           vo.setDataRodada(n.getDataRodada());
           vo.setFinalizada(n.getFinalizada());
           vo.setEmpresaProponente(getOfertaEmpresaVO(n.getEmpresaProponente()));
           vo.setListaEntregaSazonais(getEntregaSazonalVOS(n.getListaEntregaSazonais()));
           vo.setListaNegociacaoPropostas(getNegociacaoPropostaVOS(n.getListaNegociacaoPropostas()));
           listaVO.add(vo);
        }

        return  listaVO;
    }

    private List<EntregaSazonalVO> getEntregaSazonalVOS(List<EntregaSazonais> lista){
        if(lista==null
                || lista.isEmpty()){
            return null;
        }
        var listaVO = new ArrayList<EntregaSazonalVO>();
        for (EntregaSazonais n : lista) {
            var vo = new EntregaSazonalVO();
            vo.setMes(n.getMesEntrega());
            vo.setValorMes(n.getValorMes().toString());
            listaVO.add(vo);
        }
        return  listaVO;
    }

    private List<OfertaNegociacaoPropostaVO> getNegociacaoPropostaVOS(List<NegociacaoProposta> lista){
        if(lista==null
                || lista.isEmpty()){
            return null;
        }
        var listaVO = new ArrayList<OfertaNegociacaoPropostaVO>();
        for (NegociacaoProposta n : lista) {
            var vo = new OfertaNegociacaoPropostaVO();
            vo.setId(n.getId());
            vo.setValor(n.getValor());
            vo.setIdItem(n.getNegociacaoItens().getId());
            vo.setDescricaoItem(n.getNegociacaoItens().getDescricao());
            listaVO.add(vo);
        }
        return  listaVO;
    }

}
