package br.com.drwars.v1.service;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Noticia;
import br.com.drwars.exception.ResourceNotFoundException;
import br.com.drwars.repositories.NoticiaRepository;
import br.com.drwars.repositories.NoticiaSituacaoRepository;
import br.com.drwars.util.Constantes;
import br.com.drwars.util.UsuarioLogado;
import br.com.drwars.v1.vo.NoticiaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository repository;
    
    @Autowired 
    private UsuarioService usuarioService;
    
    @Autowired
    private NoticiaSituacaoRepository situacaoRepository;

    private Logger logger = Logger.getLogger(NoticiaService.class.getName());


    public Page<NoticiaVO> findAllPaginado(Pageable pageable) {
        logger.warning("Listar todas notícias");
        Page<Noticia>  listaPage = repository.findNoticiaPaginada(pageable);
        var listaVosPage = listaPage.map(p-> DozerConverter.parseObject(p,NoticiaVO.class));
        return listaVosPage;
    }

    public NoticiaVO findById(Long id) {
        logger.warning("Buscar notícias por id");
        var model = (Noticia) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notícia  não  encontrada!"));
        return DozerConverter.parseObject(model, NoticiaVO.class);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public NoticiaVO create(NoticiaVO vo) {
        logger.warning("Cadastrar Notícia");
        var model = new Noticia();
        var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());
        model.setData(LocalDateTime.now());
        model.setTitulo(vo.getTitulo());
        model.setTexto(vo.getTexto());
        model.setDataInclusao(LocalDateTime.now());
        model.setUsuarioInclusao(usuario);
        model.setDataAlteracao(LocalDateTime.now());
        model.setUsuarioAlteracao(usuario);
        model.setEmpresa(usuario.getEmpresa());
        model.setSituacao(situacaoRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Notícia  não  encontrada!")));
        repository.save(model);
        return  DozerConverter.parseObject(model,NoticiaVO.class);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public NoticiaVO update(NoticiaVO vo) {
        logger.warning("Alterar notícias");
        var model = repository.findById(vo.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Notícia  não  encontrado!"));
        model.setTitulo(vo.getTitulo());
        model.setTexto(vo.getTexto());
        model.setDataAlteracao(LocalDateTime.now());
        model.setUsuarioAlteracao(UsuarioLogado.getUser());
        repository.save(model);
        return DozerConverter.parseObject(model,NoticiaVO.class);
    }
    
    
    public NoticiaVO aprovar(Long idNoticia) {
    	return this.alterarTipoNoticia(idNoticia, Constantes.SITUACAO_NOTICIA_APROVADO);
    }
    
    public NoticiaVO reprovar(Long idNoticia) {
    	return this.alterarTipoNoticia(idNoticia, Constantes.SITUACAO_NOTICIA_REPROVADO);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    private NoticiaVO alterarTipoNoticia(Long idNoticia, Long idNoticiaSituacao) {
        logger.warning("Alterar situação notícias");
        var model = repository.findById(idNoticia)
                .orElseThrow(() -> new ResourceNotFoundException("Notícia  não  encontrado!"));
        
        var situacao = situacaoRepository.findById(idNoticiaSituacao)
                .orElseThrow(() -> new ResourceNotFoundException("Situação Notícia inválido!"));	
        model.setDataAlteracao(LocalDateTime.now());
        model.setUsuarioAlteracao(UsuarioLogado.getUser());
        model.setSituacao(situacao);
        repository.save(model);
        return DozerConverter.parseObject(model,NoticiaVO.class);
    }
    
    public Page<NoticiaVO> listarNotciasPendentes(Pageable pageable) {
        logger.warning("Listar todas notícias pendentes");
        Page<Noticia>  listaPage = repository.findNoticiaPorTipoPaginada(pageable,Constantes.SITUACAO_NOTICIA_PENDENTE);
        var listaVosPage = listaPage.map(p-> DozerConverter.parseObject(p,NoticiaVO.class));
        return listaVosPage;
    }
    
    public Page<NoticiaVO> listarNotciasAprovadas(Pageable pageable) {
        logger.warning("Listar todas notícias aprovadas");
        Page<Noticia>  listaPage = repository.findNoticiaPorTipoPaginada(pageable,Constantes.SITUACAO_NOTICIA_APROVADO);
        var listaVosPage = listaPage.map(p-> DozerConverter.parseObject(p,NoticiaVO.class));
        return listaVosPage;
    }


}
