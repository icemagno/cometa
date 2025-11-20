package br.com.drwars.v1.service;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.*;
import br.com.drwars.exception.CampoInvalidoException;
import br.com.drwars.exception.InvalidParameterException;
import br.com.drwars.exception.NotInsertException;
import br.com.drwars.exception.ResourceNotFoundException;
import br.com.drwars.repositories.CompraRepository;
import br.com.drwars.repositories.CompraSiituacaoRepository;
import br.com.drwars.util.Constantes;
import br.com.drwars.util.UsuarioLogado;
import br.com.drwars.util.Util;
import br.com.drwars.v1.vo.certificadora.CertificadoVO;
import br.com.drwars.v1.vo.certificadora.EmpresaCertificadoraVO;
import br.com.drwars.v1.vo.compra.CompraVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static br.com.drwars.util.Util.convertToBigDecimal;


@Service
public class CompraService {

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private CompraRepository repository;

    @Autowired
    private LocalService localService;

    @Autowired
    private FreteTipoService freteTipoService;

    @Autowired
    private CompraSiituacaoRepository compraSiituacaoRepository;

    @Autowired
    private LogisticaCompraService logisticaCompraService;

    @Autowired
    private CertificadoService certificadoService;

    private Logger logger = Logger.getLogger(CompraService.class.getName());

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ContratoService contratoService;

    public List<CompraVO> findAllByEmpresa(Pageable pageable) {
        logger.warning("Listar todas compras");
        var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());
        var lista = repository.findByEmpresa(usuario.getEmpresa().getId(),pageable);
        var listaVos = lista.stream().map(p-> DozerConverter.parseObject(p,CompraVO.class)).collect(Collectors.toList());
        return listaVos;
    }

    public CompraVO findById(Long id) {
        logger.warning("Buscar compra por id");
        var model = (Compra) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compra não encontrado!"));
        var vo =  DozerConverter.parseObject(model, CompraVO.class);
        return vo;
    }

    public Compra findCompraById(Long id) {
        logger.warning("Buscar compra por id");
        var model = (Compra) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compra não encontrado!"));
        return model;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public CompraVO create(Long idOferta) throws CampoInvalidoException {
        logger.warning("Cadastrar Compra");

        try{
            var oferta = ofertaService.findOfertaById(idOferta);
            var rodada = getListaNegociacaoRodadaUltimaRodada(oferta);
            var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());

            //validar compra já existe

            List<Compra> lista =repository.findByOferta(idOferta);

            if(lista!=null && !lista.isEmpty())
                throw  new  CampoInvalidoException("A Oferta já está associada a uma compra!");

            /**
             *  Ofertas Venda V e Compra C
             *
             *  1 - Biometano
             *      . Apenas os campos de Biometano
             *  2 - Misto e 3 - Certificado
             *      .   Debitar certificado
             */

            var model = new Compra();
            model.setQuantidade(oferta.getQuantidade());

            if(!oferta.getOfertaTipo().getId().equals(3L)){
                model.setSazonalidade(this.getValorProposta(Constantes.ITEM_SAZONALIDADE,rodada.getListaNegociacaoPropostas()).equals("S"));
                var freteVO = freteTipoService.findFreteTipoBySigla(this.getValorProposta(Constantes.ITEM_TIPO_FRETE,rodada.getListaNegociacaoPropostas()));
                model.setFreteTipo(DozerConverter.parseObject(freteVO, FreteTipo.class));
                model.setFlexibilidade(this.getValorProposta(Constantes.ITEM_FLEXIBILIDADE,rodada.getListaNegociacaoPropostas()).equals("0"));
                model.setDataInicioEntrega(Util.converterStringToLocalDate(this.getValorProposta(Constantes.ITEM_INICIO_ENTREGA,rodada.getListaNegociacaoPropostas())));
                model.setDataFimEntrega(Util.converterStringToLocalDate(this.getValorProposta(Constantes.ITEM_FIM_ENTREGA,rodada.getListaNegociacaoPropostas())));
                model.setValor(new BigDecimal(this.getValorProposta(Constantes.ITEM_VALOR_PRODUTO,rodada.getListaNegociacaoPropostas())));
                var localEntregaVO = localService.findById(Long.valueOf(this.getValorProposta(Constantes.ITEM_LOCAL_ENTREGA,rodada.getListaNegociacaoPropostas())));
                if(model.getFreteTipo().getSigla().equals("CIF"))
                    model.setLocalEntrega(DozerConverter.parseObject(localEntregaVO, Local.class));
                if(model.getFreteTipo().getSigla().equals("FOB"))
                    model.setLocalRetirada(DozerConverter.parseObject(localEntregaVO, Local.class));

                model.setValorFrete(new BigDecimal(this.getValorProposta(Constantes.ITEM_VALOR_FRETE,rodada.getListaNegociacaoPropostas())));

            }
            model.setOferta(oferta);
            model.setEmpresaFornecedora(oferta.getEmpresa());
            model.setEmpresa(getEmpresaCompradora(usuario,oferta));
            model.setCompraSituacao(this.compraSiituacaoRepository.findById(1L)
                    .orElseThrow(() -> new ResourceNotFoundException("Compra não encontrado!")));


            if(model.getOferta().getOfertaTipo().getId().equals(2L)
                    || model.getOferta().getOfertaTipo().getId().equals(3L)
            ){
                model.setQuantidadeCertificado(Long.valueOf(this.getValorProposta(Constantes.ITEM_QUANTIDAD_CERTIFICADO,rodada.getListaNegociacaoPropostas())));
                model.setValorCertificado(convertToBigDecimal(this.getValorProposta(Constantes.ITEM_VALOR_CERTIFICADO,rodada.getListaNegociacaoPropostas())));

                if(oferta.getCertificado().getQuantidade() < model.getQuantidadeCertificado()){
                    throw new InvalidParameterException("Não possui a quantidade suficiente de certificado!");
                }
                /**
                 * Debitar do certificado o saldo
                 */
                var quantidadeCertificado = oferta.getCertificado().getQuantidade() - model.getQuantidadeCertificado();
                var certificadoVO = DozerConverter.parseObject(oferta.getCertificado(), CertificadoVO.class);
                certificadoVO.setQuantidade(quantidadeCertificado);
                certificadoService.update(certificadoVO);

                /***
                 *  Criar um novo certificado
                 */
                var criarCertificadoVO = new CertificadoVO();
                criarCertificadoVO.setQuantidade(model.getQuantidadeCertificado());
                criarCertificadoVO.setNumero(certificadoVO.getNumero());
                criarCertificadoVO.setEquivalencia(certificadoVO.getEquivalencia());
                criarCertificadoVO.setEmpresa(DozerConverter.parseObject(oferta.getEmpresa(), EmpresaCertificadoraVO.class));
                criarCertificadoVO.setEmpresaCertificadora(certificadoVO.getEmpresaCertificadora());
                certificadoService.create(criarCertificadoVO);
            }
            //data inicio e data fim

            repository.save(model);

            ofertaService.updateStatus(idOferta, Constantes.OFERTA_SITUACAO_FINALIZADA);

            //Gerar Contrato
            contratoService.create(model);
            return  DozerConverter.parseObject(model, CompraVO.class);
        }catch (Exception exception){
            logger.warning("ERROR  INCLUSAO "+exception.getMessage());
            throw new NotInsertException("Erro  na inclusõo da compra");
        }

    }

    private String getValorProposta(Long id, List<NegociacaoProposta> lista){
        for (NegociacaoProposta negociacaoProposta: lista) {
            if(negociacaoProposta.getNegociacaoItens().getId().equals(id)){
                return negociacaoProposta.getValor();
            }
        }
        return null;
    }

    private Empresa getEmpresaCompradora(Usuario usuario,Oferta oferta){
        if(oferta.getEmpresaPrivada()!=null && oferta.getEmpresaPrivada().getId()!=null){
            return oferta.getEmpresaPrivada();
        }else if(!usuario.getEmpresa().getId().equals(oferta.getEmpresa().getId())){
            return usuario.getEmpresa();
        }
        for (NegociacaoRodada negociacaoRodada : oferta.getListaNegociacaoRodada()) {
            if(!negociacaoRodada.getEmpresaProponente().getId().equals(oferta.getEmpresa().getId())){
                return negociacaoRodada.getEmpresaProponente();
            }
        }

        return null;
    }

    private NegociacaoRodada getListaNegociacaoRodadaUltimaRodada(Oferta oferta){
        var lista = oferta.getListaNegociacaoRodada();
        lista.sort(Comparator.comparing(NegociacaoRodada::getId));
        return lista.get(lista.size()-1);
    }

}
