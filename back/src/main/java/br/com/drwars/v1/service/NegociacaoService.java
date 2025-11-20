package br.com.drwars.v1.service;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.*;
import br.com.drwars.entites.enums.OfertaTipoEnum;
import br.com.drwars.exception.CampoInvalidoException;
import br.com.drwars.exception.InvalidParameterException;
import br.com.drwars.exception.ResourceNotFoundException;
import br.com.drwars.repositories.NegociacaoItensRepository;
import br.com.drwars.repositories.NegociacaoPropostaRepository;
import br.com.drwars.repositories.NegociacaoRodadaRepository;
import br.com.drwars.sevice.EmailService;
import br.com.drwars.util.Constantes;
import br.com.drwars.util.UsuarioLogado;
import br.com.drwars.v1.vo.EntregaSazonalVO;
import br.com.drwars.v1.vo.NegociacaoRodadaVO;
import br.com.drwars.v1.vo.NegociacaoPropostaVO;
import br.com.drwars.v1.vo.certificadora.CertificadoVO;
import br.com.drwars.v1.vo.negociacao.NegociacaoVO;
import br.com.drwars.validacao.ValidacaoOfertaCompraNormal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import static br.com.drwars.validacao.ValidacaoOfertaCompraNormal.validaQuantidadeItens;


@Service
public class NegociacaoService {

    @Autowired
    private NegociacaoRodadaRepository repository;
    
    @Autowired
    private NegociacaoItensRepository negociacaoItensRepository;

    @Autowired
    private NegociacaoPropostaRepository negociacaoPropostaRepository;

    @Autowired
    private  EntregaSazonalService entregaSazonalService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private ValidacaoOfertaCompraNormal validacaoOfertaCompraNormal;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CertificadoService certificadoService;

    private Logger logger = Logger.getLogger(NegociacaoService.class.getName());


    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public NegociacaoRodadaVO insertNegocicao(NegociacaoVO vo) throws CampoInvalidoException, MessagingException {
        logger.warning("Cadastrar Negociacao primeira Rodada");
        var negociacaoRodada = new NegociacaoRodada();
        var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());
        var oferta = this.ofertaService.findOfertaById(vo.getOferta().getId());
        var rodada = getListaNegociacaoRodadaUltimaRodada(oferta);

        if(usuario.getEmpresa().getId().equals(rodada.getEmpresaProponente().getId())){
            throw  new InvalidParameterException("Uma empresa não pode preencher duas rodadas seguidas");
        }

        if(oferta.getOfertaTipo().getId().equals(2L)
            || oferta.getOfertaTipo().getId().equals(3L)){
            //Verifica  se tem certificado
            if(oferta.getTipo().equals(OfertaTipoEnum.C)
                && (oferta.getCertificado()==null || oferta.getCertificado().getId()==null)
                    && (vo.getOferta().getCertificado()==null || vo.getOferta().getCertificado().getId()==null)
            ){
                throw  new InvalidParameterException("Esse tipo de oferta deve possuir um certificado");
            }
            if(oferta.getTipo().equals(OfertaTipoEnum.C)){
                vo.getOferta().setCertificado(DozerConverter.parseObject(oferta.getCertificado(), CertificadoVO.class));
                this.ofertaService.updateCertificado(vo.getOferta());
            }
        }

        if(!oferta.getOfertaSituacao().getId().equals(Constantes.OFERTA_SITUACAO_EM_ANDAMENTO)) {
            this.ofertaService.updateStatus(oferta.getId(), Constantes.OFERTA_SITUACAO_EM_ANDAMENTO);

            if(oferta.getEmpresaPrivada()==null
                    || oferta.getEmpresaPrivada().getId()==null
            ){
                oferta.setEmpresaPrivada(usuario.getEmpresa());
                this.ofertaService.update(oferta);
            }
        }

        Integer  valuerodada = oferta.getListaNegociacaoRodada().size() +1;
        negociacaoRodada.setOferta(oferta);
        negociacaoRodada.setRodada(valuerodada.longValue());
        negociacaoRodada.setFinalizada(false);
        negociacaoRodada.setObservacao(vo.getObservacaoRodada());
        negociacaoRodada.setDataRodada(LocalDateTime.now());
        negociacaoRodada.setEmpresaProponente(usuario.getEmpresa());
        repository.save(negociacaoRodada);

        /**
         *  Ofertas Venda V e Compra C
         *
         *  1 - Biometano 95 e 96
         *      . Apenas os campos de Biometano
         *  2 - Misto 106 e 107
         *      .   Oferta informo o Cetificado
         *      .   Compra caso não tenha nenhum certificado o certificado deve ser informado
         *  3 - Certificado 101 e 103
         *      .   Oferta informo o Cetificado
         *      .   Compra caso não tenha nenhum certificado o certificado deve ser informado
         */

        if(!vo.getListaNegociacaoProposta().isEmpty()){
            validaQuantidadeItens(vo.getListaNegociacaoProposta(),oferta.getOfertaTipo().getId());
            for(NegociacaoPropostaVO negociacaoPropostaVO : vo.getListaNegociacaoProposta()){
                if(itemValidoParaInsercao(negociacaoPropostaVO,oferta.getOfertaTipo().getId())){
                    var idItem =Long.valueOf(negociacaoPropostaVO.getIdItem());
                    var negociacaoItem = this.negociacaoItensRepository.findById(idItem)
                            .orElseThrow(() -> new ResourceNotFoundException("Item  não  encontrado!"));
                    validacaoOfertaCompraNormal.validarItem(idItem, negociacaoPropostaVO.getValor());
                    var negociacaoProposta = new NegociacaoProposta();
                    negociacaoProposta.setNegociacaoRodada(negociacaoRodada);
                    negociacaoProposta.setValor(negociacaoPropostaVO.getValor());
                    negociacaoProposta.setNegociacaoItens(negociacaoItem);

                    negociacaoPropostaRepository.save(negociacaoProposta);
                }

            }
        }else
            throw  new InvalidParameterException("A lista de itens está vazia");
        if(!oferta.getOfertaTipo().getId().equals(3L)){
            if(vo.getEntregasSazonais()!=null && !vo.getEntregasSazonais().isEmpty()){
                for(EntregaSazonalVO entregaSazonais : vo.getEntregasSazonais()){
                    var entrega = new EntregaSazonais();
                    entrega.setMesEntrega(entregaSazonais.getMes());
                    entrega.setValorMes( new BigDecimal(entregaSazonais.getValorMes()));
                    entrega.setNegociacaoRodada(negociacaoRodada);
                    entregaSazonalService.create(entrega);
                }
            }else
                throw  new InvalidParameterException("A lista de entregas sazonais está vazia");
        }

        //Uma nova Rodada foi inserida

        if(oferta.getEmpresaPrivada()!=null && oferta.getEmpresaPrivada().getId()!=null){
            var lista = usuarioService.findByEmpresa(oferta.getEmpresaPrivada().getId());
            for(Usuario usuarioVO : lista) {
                emailService.sendNovaRodadaOferta(usuarioVO, oferta);
            }
        }

        if(oferta.getEmpresa()!=null && oferta.getEmpresa().getId()!=null){
            var lista = usuarioService.findByEmpresa(oferta.getEmpresa().getId());
            for(Usuario usuarioVO : lista) {
                emailService.sendNovaRodadaOferta(usuarioVO, oferta);
            }
        }

        return  DozerConverter.parseObject(negociacaoRodada,NegociacaoRodadaVO.class);
    }


    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public NegociacaoRodadaVO insertNegocicao(Oferta oferta, String observacao, List<NegociacaoPropostaVO> negociacaoPropostaVOS) throws CampoInvalidoException {
        logger.warning("Cadastrar Negociacao primeira Rodada");
        var negociacaoRodada = new NegociacaoRodada();
        negociacaoRodada.setOferta(oferta);
        negociacaoRodada.setRodada(1L);
        negociacaoRodada.setFinalizada(false);
        negociacaoRodada.setObservacao(observacao);
        negociacaoRodada.setDataRodada(LocalDateTime.now());
        negociacaoRodada.setEmpresaProponente(oferta.getEmpresa());
        repository.save(negociacaoRodada);
        if(!negociacaoPropostaVOS.isEmpty()){
            validaQuantidadeItens(negociacaoPropostaVOS,oferta.getOfertaTipo().getId());
            for(NegociacaoPropostaVO negociacaoPropostaVO : negociacaoPropostaVOS){
                if(itemValidoParaInsercao(negociacaoPropostaVO,oferta.getOfertaTipo().getId())) {
                    var idItem = Long.valueOf(negociacaoPropostaVO.getIdItem());
                    var negociacaoItem = this.negociacaoItensRepository.findById(idItem)
                            .orElseThrow(() -> new ResourceNotFoundException("Item  não  encontrado!"));

                    validacaoOfertaCompraNormal.validarItem(idItem, negociacaoPropostaVO.getValor());
                    var negociacaoProposta = new NegociacaoProposta();
                    negociacaoProposta.setNegociacaoRodada(negociacaoRodada);
                    negociacaoProposta.setValor(negociacaoPropostaVO.getValor());
                    negociacaoProposta.setNegociacaoItens(negociacaoItem);

                    negociacaoPropostaRepository.save(negociacaoProposta);

                }
            }
        }else
            throw  new InvalidParameterException("A lista de itens está vazia");

        return  DozerConverter.parseObject(negociacaoRodada,NegociacaoRodadaVO.class);
    }




    private NegociacaoRodada getListaNegociacaoRodadaUltimaRodada(Oferta oferta){
        var lista = oferta.getListaNegociacaoRodada();
        lista.sort(Comparator.comparing(NegociacaoRodada::getId));
        return lista.get(lista.size()-1);
    }

    private boolean itemValidoParaInsercao(NegociacaoPropostaVO negociacaoPropostaVO, Long item) {

        /**
         *  Ofertas O e Compra C
         *
         *  1 - Biometano
         *      . Itens  3, 5, 8, 13, 14, 15,18,19,20,21
         *  2 - Misto
         *      .
         *  3 - Certificado
         *      .   17,22
         */

        if(item.equals(1L)){
            return negociacaoPropostaVO.getIdItem().equals("3")
                   || negociacaoPropostaVO.getIdItem().equals("5") || negociacaoPropostaVO.getIdItem().equals("8")
                    || negociacaoPropostaVO.getIdItem().equals("13") || negociacaoPropostaVO.getIdItem().equals("14")
                    || negociacaoPropostaVO.getIdItem().equals("15") || negociacaoPropostaVO.getIdItem().equals("18")
                    || negociacaoPropostaVO.getIdItem().equals("19")
                    || negociacaoPropostaVO.getIdItem().equals("21");
        }

        if(item.equals(2L)){
            return true;
        }

        if(item.equals(3L)){
            return negociacaoPropostaVO.getIdItem().equals("17") || negociacaoPropostaVO.getIdItem().equals("22") ;
        }

        return false;
    }

}
