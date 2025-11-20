package br.com.drwars.v1.service;


import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Endereco;
import br.com.drwars.entites.LogisticaColeta;
import br.com.drwars.entites.enums.TipoEntradaSaidaEnum;
import br.com.drwars.exception.NotInsertException;
import br.com.drwars.exception.ResourceNotFoundException;
import br.com.drwars.repositories.LogisticaColetaRepository;
import br.com.drwars.util.UsuarioLogado;
import br.com.drwars.v1.controller.LogisticaColetaController;
import br.com.drwars.v1.vo.coleta.LogisticaColetaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Level;
import java.util.logging.Logger;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class LogisticaColetaService {

    private Logger logger = Logger.getLogger(LogisticaColetaService.class.getName());
    @Autowired
    private LogisticaColetaRepository repository;

    @Autowired
    private LogisiticaService logisiticaService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private UsuarioService usuarioService;

    public Page<LogisticaColetaVO> findPagenable(Pageable pageable) {
        logger.warning("Listar todas coletas");
        var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());
        Page<LogisticaColeta>  listaPage = repository.findAllByEmpresa(usuario.getEmpresa().getId(),pageable);
        var listaVosPage = listaPage.map(p-> DozerConverter.parseObject(p,LogisticaColetaVO.class));
        listaVosPage.stream()
                .forEach(p -> p.add(linkTo(methodOn(LogisticaColetaController.class).findById(p.getKey())).withSelfRel()));
        return listaVosPage;
    }

    public LogisticaColetaVO findById(Long id) {
        logger.warning("Buscar coleta por id");
        var model = (LogisticaColeta) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coleta não encontrado!"));
        var vo =  DozerConverter.parseObject(model, LogisticaColetaVO.class);
        vo.add(linkTo(methodOn(LogisticaColetaController.class).findById(id)).withSelfRel());
        return vo;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public LogisticaColetaVO create(LogisticaColetaVO vo)  {
        logger.warning("Cadastrar coleta");
        try {
            var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());
            var model =  DozerConverter.parseObject(vo, LogisticaColeta.class);
            model.setEmpresa(usuario.getEmpresa());
            var enderecoVO = enderecoService.create(vo.getEndereco());
            model.setEndereco(DozerConverter.parseObject(enderecoVO, Endereco.class));
            model.setLogistica(logisiticaService.create(vo.getLogistica()));
            repository.save(model);
            var retorno =  DozerConverter.parseObject(model, LogisticaColetaVO.class);
            retorno.add(linkTo(methodOn(LogisticaColetaController.class).findById(vo.getKey())).withSelfRel());
            return retorno;
        }catch (Exception e){
            logger.log(Level.SEVERE,e.getMessage());
            e.printStackTrace();
            throw  new NotInsertException("Error ao inserir coleta");
        }

    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public LogisticaColetaVO update(LogisticaColetaVO vo) {
        logger.warning("Alterar coleta");
        var model = repository.findById(vo.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Coleta  não  encontrado!"));
        var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());
        model.setLogistica(logisiticaService.update(model.getLogistica().getId(),vo.getLogistica()));
        var enderecoVO = enderecoService.update(model.getEndereco().getId(),vo.getEndereco());
        model.setEntrada(TipoEntradaSaidaEnum.valueOf(vo.getEntrada()));
        model.setEmpresa(usuario.getEmpresa());
        model.setEndereco(DozerConverter.parseObject(enderecoVO, Endereco.class));
        repository.save(model);
        var retorno =  DozerConverter.parseObject(model, LogisticaColetaVO.class);
        retorno.add(linkTo(methodOn(LogisticaColetaController.class).findById(vo.getKey())).withSelfRel());
        return retorno;
    }



}