package br.com.drwars.v1.service;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.*;
import br.com.drwars.entites.enums.OfertaTipoEnum;
import br.com.drwars.exception.*;
import br.com.drwars.repositories.OfertaRepository;
import br.com.drwars.repositories.OfertaSituacaoRepository;
import br.com.drwars.repositories.OfertaTipoRepository;
import br.com.drwars.sevice.EmailService;
import br.com.drwars.util.Constantes;
import br.com.drwars.util.UsuarioLogado;
import br.com.drwars.v1.vo.EntregaSazonaisVO;
import br.com.drwars.v1.vo.EntregaSazonalVO;
import br.com.drwars.v1.vo.NegociacaoPropostaVO;
import br.com.drwars.v1.vo.UsuarioVO;
import br.com.drwars.v1.vo.negociacao.NegociacaoVO;
import br.com.drwars.v1.vo.oferta.OfertaNegociacaoVO;
import br.com.drwars.v1.vo.oferta.OfertaVO;
import br.com.drwars.v1.vo.oferta.TotalOfertadoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static br.com.drwars.validacao.ValidacaoOfertaCompraNormal.verficaQuantidadeCertificado;


@Service
public class OfertaService {

    @Autowired
    private OfertaRepository repository;

    @Autowired
    private OfertaTipoRepository ofertaTipoRepository;

    @Autowired
    private OfertaSituacaoRepository ofertaSituacaoRepository;

    @Autowired
    private NegociacaoService negociacaoService;

    @Autowired
    private EntregaSazonalService entregaSazonalService;

    @Autowired
    private CertificadoService certificadoService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioService usuarioService;

    private Logger logger = Logger.getLogger(OfertaService.class.getName());

    
    @Autowired private BlockchainService blockchainService;

    public List<OfertaVO> findAll() {
        logger.warning("Listar todas ofertas");
        var  lista = (ArrayList<Oferta>) repository.findAll();
        var listaVosPage = lista.stream().map(p-> DozerConverter.parseObject(p,OfertaVO.class)).collect(Collectors.toList());
        return listaVosPage;
    }

    public Page<OfertaNegociacaoVO> findByTipoOfeta(List<Long> idTIpoOferta, List<Long> situacoes, List<String> tipo, Pageable pageable) {
        logger.warning("Listar todas ofertas por tipo");
        var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());
        Page<Oferta>  lista = null;
        List<OfertaTipoEnum> listaTipoOferta = new ArrayList<>();
        tipo.stream().forEach(t-> listaTipoOferta.add(OfertaTipoEnum.valueOf(t)));
       // var tipoOferta =OfertaTipoEnum.valueOf(tipo);

        if(situacoes!=null && !situacoes.isEmpty())
           lista= repository.findOfetaByTipoOfertaSituacao(situacoes,listaTipoOferta,idTIpoOferta,usuario.getEmpresa().getId(),pageable);
        else
            lista =  repository.findOfetaByTipoOferta(listaTipoOferta,idTIpoOferta,usuario.getEmpresa().getId(),pageable);

        if(lista==null)
            return  null;
        var listaVosPage = lista.map(o-> {
            var  oferta = new OfertaNegociacaoVO(o);

            if(oferta.getListaNegociacaoRodada()!=null && oferta.getListaNegociacaoRodada().size() >1) {
                oferta.getListaNegociacaoRodada().sort((p1, p2) -> Long.compare(p1.getId(), p2.getId()));
                Collections.reverse(oferta.getListaNegociacaoRodada());
            }
            return oferta;
        });
        return listaVosPage;
    }

    public OfertaNegociacaoVO findById(Long id) {
        logger.warning("Buscar oferta por id");
        var model = (Oferta) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Oferta não encontrado!"));
        var vo =  new OfertaNegociacaoVO(model);
        return vo;
    }

    public Oferta findOfertaById(Long id) {
        logger.warning("Buscar oferta por id");
        var model = (Oferta) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Oferta não encontrado!"));
        return model;
    }


    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public OfertaVO create(NegociacaoVO vo) throws CampoInvalidoException, MessagingException {
        logger.warning("Cadastrar Oferta");
        if(vo==null) throw  new RequiredObjectIsNullException("Preencher campos de Ofeta!");
        var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());
        var model =   DozerConverter.parseObject(vo.getOferta(), Oferta.class);

        var quantidade = getQuantidade(vo);

        if(!vo.getOferta().getOfertaTipo().getId().equals(3L) && quantidade==0){
            throw  new CampoInvalidoException("Por favor informe valor do m3");
        }

        model.setData(LocalDateTime.now());
        model.setOfertaSituacao(ofertaSituacaoRepository.findById(Constantes.OFERTA_SITUACAO_INICIADA)
                .orElseThrow(() -> new ResourceNotFoundException("Erro ao inserir uma oferta!")));
        model.setOfertaTipo(ofertaTipoRepository.findById(vo.getOferta().getOfertaTipo().getId()).orElseThrow(() -> new ResourceNotFoundException("Tipo oferta mão foi inserido corretamente!")));
        model.setEmpresa(usuario.getEmpresa());

        if(vo.getOferta()!=null && vo.getOferta().getEmpresaPrivada()!=null && vo.getOferta().getEmpresaPrivada().getId()!=null
            &&  vo.getOferta().getEmpresaPrivada().getId().equals(usuario.getEmpresa().getId())){
            throw  new CampoInvalidoException("O campo empresa privada tem que ser diferente da do usuário logado.");
        }

        if(vo.getOferta()!=null && vo.getOferta().getEmpresaPrivada()!=null && vo.getOferta().getEmpresaPrivada().getId()!=null) {
            model.setEmpresaPrivada(empresaService.findEmpresaById(vo.getOferta().getEmpresaPrivada().getId()));
        }

        if(vo.getOferta().getEmpresaPrivada()!=null && vo.getOferta().getEmpresaPrivada().getId()==null){
            model.setEmpresaPrivada(null);

        }

        /**
         *  Ofertas Venda V e Compra C
         *
         *  1 - Biometano
         *      . Apenas os campos de Biometano
         *  2 - Misto
         *      .   Oferta informo o Cetificado
         *      .   Compra não informo o certificado apenas a quantidade
         *  3 - Certificado
         *      .   Oferta informo o Cetificado
         *      .   Compra não informo o certificado apenas a quantidade
         */

        if(vo.getOferta().getOfertaTipo().getId().equals(2L)
            || vo.getOferta().getOfertaTipo().getId().equals(3L)){
           // Quantidade de Certificados deve ser informado
           if(!verficaQuantidadeCertificado(vo.getListaNegociacaoProposta())){
               throw  new CampoInvalidoException("O campo quantidade do certificado deve ser informado");
           }
           //Caso seja  de venda o campo certificado deve ser informado
           if(vo.getOferta().getTipo().equals("V")) {
               //O campo certicado deve ser Obrigatório
               if(vo.getOferta().getCertificado()==null){
                   throw  new CampoInvalidoException("O campo  certificado deve ser informado");
               }
               model.setCertificado(certificadoService.findCertificadoById(vo.getOferta().getCertificado().getId()));
           }else
               model.setCertificado(certificadoService.findCertificadoById(vo.getOferta().getCertificado().getId()));
            model.setQuantidadeCertificado(getRetornaQuantidadeCertificado(vo.getListaNegociacaoProposta()));
        }



      //  model.setQuantidade(quantidade);
        repository.save(model);

        var negociacaoRodadaVO = negociacaoService.insertNegocicao(model, vo.getObservacaoRodada(), vo.getListaNegociacaoProposta());
        if(!vo.getOferta().getOfertaTipo().getId().equals(3L)) {
           entregaSazonalService.create(negociacaoRodadaVO, vo.getEntregasSazonais());
        }

        var retorno =  DozerConverter.parseObject(model, OfertaVO.class);

        try {
        	blockchainService.createOferta( retorno );
        } catch (Exception e) {
			e.printStackTrace();
		}

        //  //Caso seja  uma oferta privada avisar ao usuarios sobre a oferta

        if(model.getEmpresaPrivada()!=null && model.getEmpresaPrivada().getId()!=null){
            var lista = usuarioService.findByEmpresa(model.getEmpresaPrivada().getId());
            for(Usuario usuarioVO : lista) {
                emailService.sendCriacaoOferta(usuarioVO, model);
            }
        }
        
        return retorno;
    }


    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateStatus(Long idOferta, Long idSituacao) {
        logger.warning("Alterar Compra Status");
        var model = repository.findById(idOferta)
                .orElseThrow(() -> new ResourceNotFoundException("Oferta não encontrado!"));


        model.setOfertaSituacao(ofertaSituacaoRepository.findById(idSituacao)
                .orElseThrow(() -> new ResourceNotFoundException("Situação não encontrado!")));

        repository.save(model);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public NegociacaoVO update(Long idOferta,NegociacaoVO vo) {
        logger.warning("Alterar Ofeta");
       if(vo==null) throw  new RequiredObjectIsNullException("Preencher campos de Ofeta!");
        var model = repository.findById(idOferta)
                .orElseThrow(() -> new ResourceNotFoundException("Ofeta  não  encontrado!"));

        if(model.getListaNegociacaoRodada().size() >1){
            throw  new InvalidParameterException("Essa oferta já se encontra em negociação.");
        }
        repository.save(model);

        return vo;
    }



    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateCertificado(OfertaVO vo) {
        logger.warning("Alterar Certificado");
        if(vo==null) throw  new RequiredObjectIsNullException("Preencher campos de Ofeta!");
        var model = repository.findById(vo.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Ofeta  não  encontrado!"));
        model.setCertificado(DozerConverter.parseObject(vo.getCertificado(), Certificado.class));
        repository.save(model);

    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(Oferta oferta) {
        logger.warning("Alterar Ofeta");

        repository.save(oferta);

    }

    private Long getRetornaQuantidadeCertificado(List<NegociacaoPropostaVO> listaNegociacaoProposta) {

        if(listaNegociacaoProposta==null || listaNegociacaoProposta.isEmpty())
            return  0L;
        NegociacaoPropostaVO item  = null;
        for (NegociacaoPropostaVO negociacaoPropostaVO: listaNegociacaoProposta) {
            if(negociacaoPropostaVO.getIdItem().equals("22")){
                item = negociacaoPropostaVO;
            }
        }
        if(item!=null)
            return Long.valueOf(item.getValor());
        return 0L;
    }

    private static long getQuantidade(NegociacaoVO vo) {
        var quantidade = 0L;
        if(vo.getEntregasSazonais()!=null && !vo.getEntregasSazonais().isEmpty()){
            for (EntregaSazonalVO entregaSazonais: vo.getEntregasSazonais()) {
                quantidade += Long.valueOf(entregaSazonais.getValorMes());
            }
        }
        return quantidade;
    }

    public TotalOfertadoVO buscarTotalOfertado() {
        var totalOfertado = new  TotalOfertadoVO();
        totalOfertado.setTotal(0L);
        totalOfertado.setPrecoMedio(BigDecimal.ZERO);

        logger.warning("Buscar Total Ofertado");
        var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());
        var quantidade = this.repository.findOfetaByTipoOfertaSituacao(usuario.getEmpresa().getId());

        if(quantidade!=null)
            totalOfertado.setTotal(quantidade);

        return  totalOfertado;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void cancelar(Long id) {
        logger.warning("Cancelar oferta por id");
        var model = (Oferta) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Oferta não encontrado!"));

        var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());

     /*   if(!model.getOfertaSituacao().getId().equals(Constantes.OFERTA_SITUACAO_INICIADA)
        ){
            throw  new NotInsertException("Essa oferta não pode ser cancelada");
        }
*/


        model.setOfertaSituacao(ofertaSituacaoRepository.findById(Constantes.OFERTA_SITUACAO_CANCELADA)
                .orElseThrow(() -> new ResourceNotFoundException("Situação não encontrado!")));

        repository.save(model);
    }
}
