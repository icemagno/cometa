package br.com.drwars.v1.service;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.*;
import br.com.drwars.exception.ResourceNotFoundException;
import br.com.drwars.repositories.LocalRepository;
import br.com.drwars.v1.controller.FreteTipoController;
import br.com.drwars.v1.controller.LocalController;
import br.com.drwars.v1.vo.FreteTipoVO;
import br.com.drwars.v1.vo.LocalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static br.com.drwars.util.security.ValidaPermissao.validaPermissaoLocalListar;
import static br.com.drwars.util.security.ValidaPermissao.validaPermissaoLocalPersistir;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class LocalService {

    private Logger logger = Logger.getLogger(LocalService.class.getName());
    @Autowired
    private LocalRepository repository;
    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EmpresaService empresaService;


    public List<LocalVO> findAll() {
        logger.warning("Listar todos locais");
        validaPermissaoLocalListar();
        var lista = DozerConverter.parseListObjects(repository.findAll(), LocalVO.class);
        lista
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(LocalController.class).findById(p.getKey())).withSelfRel()));
        return lista;
    }

    public LocalVO findById(Long id) {
        logger.warning("Buscar Local por id");
        validaPermissaoLocalListar();
        var model = (Local) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Local não encontrado!"));
        var vo =  DozerConverter.parseObject(model, LocalVO.class);
        vo.add(linkTo(methodOn(LocalController.class).findById(id)).withSelfRel());
        return vo;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public LocalVO create(LocalVO vo)  {
        logger.warning("Cadastrar Local");
        validaPermissaoLocalPersistir();
        var model= DozerConverter.parseObject(vo, Local.class);

        if(vo.getEndereco()!=null){
            var endereco= enderecoService.create(vo.getEndereco());
            model.setEndereco(DozerConverter.parseObject(endereco, Endereco.class));
        }

        if(vo.getEmpresa()!=null){
            var empresa  =  empresaService.findEmpresaById(vo.getEmpresa().getId());
        }

        repository.save(model);

        return  DozerConverter.parseObject(model,LocalVO.class);
    }


    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public LocalVO update(Long id,LocalVO vo)  {
        logger.warning("Alterar Local");
        validaPermissaoLocalPersistir();

       var model =repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Local  não  encontrado!"));
        logger.warning("Alterar Local");
        DozerConverter.parseObject(vo, Local.class);
        model.setId(id);
        model.setDescricao(vo.getDescricao());
        model.setNome(vo.getNome());

        if(vo.getEndereco()!=null){
            var endereco= enderecoService.update(model.getEndereco().getId(),vo.getEndereco());
            model.setEndereco(DozerConverter.parseObject(endereco, Endereco.class));
        }

        if(vo.getEmpresa()==null){
            model.setEndereco(null);
        }else{
            var empresa  =  empresaService.findEmpresaById(vo.getEmpresa().getId());
        }

        repository.save(model);

        return  DozerConverter.parseObject(model,LocalVO.class);
    }

}