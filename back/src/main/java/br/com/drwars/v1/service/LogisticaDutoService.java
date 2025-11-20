package br.com.drwars.v1.service;


import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.LogisticaDuto;
import br.com.drwars.exception.ResourceNotFoundException;
import br.com.drwars.repositories.DutoRepository;
import br.com.drwars.repositories.DutoTipoRepository;
import br.com.drwars.repositories.LogisticaDutoRepository;
import br.com.drwars.util.UsuarioLogado;
import br.com.drwars.v1.controller.LogisticaDutoController;
import br.com.drwars.v1.vo.duto.LogisticaDutoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class LogisticaDutoService {

    private Logger logger = Logger.getLogger(LogisticaDutoService.class.getName());
    @Autowired
    private LogisticaDutoRepository repository;

    @Autowired
    private DutoRepository dutoRepository;

    @Autowired
    private DutoTipoRepository dutoTipoRepository;

    @Autowired
    private LogisiticaService logisiticaService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private UsuarioService usuarioService;

    public Page<LogisticaDutoVO> findPagenable(Pageable pageable) {
        logger.warning("Listar todos dutos");
        var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());
        Page<LogisticaDuto>  listaPage = repository.findAllByEmpresa(usuario.getEmpresa().getId(),pageable);
        var listaVosPage = listaPage.map(p-> DozerConverter.parseObject(p,LogisticaDutoVO.class));
        listaVosPage.stream()
                .forEach(p -> p.add(linkTo(methodOn(LogisticaDutoController.class).findById(p.getKey())).withSelfRel()));
        return listaVosPage;
    }

    public LogisticaDutoVO findById(Long id) {
        logger.warning("Buscar dutos por id");
        var model = (LogisticaDuto) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Duto não encontrado!"));
        var vo =  DozerConverter.parseObject(model, LogisticaDutoVO.class);
        vo.add(linkTo(methodOn(LogisticaDutoController.class).findById(id)).withSelfRel());
        return vo;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public LogisticaDutoVO create(LogisticaDutoVO vo)  {
        logger.warning("Cadastrar duto");
        var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());
        var model =  DozerConverter.parseObject(vo, LogisticaDuto.class);
        dutoRepository.save(model.getDuto());
        model.setEmpresa(usuario.getEmpresa());
        model.setDutoTipo(dutoTipoRepository.findBySigla(vo.getDutoTipo().getSigla()));
        model.setLogistica(logisiticaService.create(vo.getLogistica()));
        dutoRepository.save(model.getDuto());
        repository.save(model);
        var retorno =  DozerConverter.parseObject(model, LogisticaDutoVO.class);
        retorno.add(linkTo(methodOn(LogisticaDutoController.class).findById(vo.getKey())).withSelfRel());
        return retorno;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public LogisticaDutoVO update(LogisticaDutoVO vo) {
        logger.warning("Alterar coleta");
        var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());
        var model = repository.findById(vo.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Duto  não  encontrado!"));
        model.getDuto().setNome(vo.getDuto().getNome());
        model.getDuto().setDiametro(vo.getDuto().getDiametro());
        model.setLogistica(logisiticaService.update(model.getLogistica().getId(),vo.getLogistica()));
        model.setEmpresa(usuario.getEmpresa());
        dutoRepository.save(model.getDuto());
        repository.save(model);
        var retorno =  DozerConverter.parseObject(model, LogisticaDutoVO.class);
        retorno.add(linkTo(methodOn(LogisticaDutoController.class).findById(vo.getKey())).withSelfRel());
        return retorno;
    }



}