package br.com.drwars.v1.service;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Perfil;
import br.com.drwars.exception.CampoInvalidoException;
import br.com.drwars.exception.InvalidoPermissaoException;
import br.com.drwars.exception.RequiredObjectIsNullException;
import br.com.drwars.exception.ResourceNotFoundException;
import br.com.drwars.repositories.PerfilRepository;
import br.com.drwars.util.UsuarioLogado;
import br.com.drwars.v1.controller.PerfilController;
import br.com.drwars.v1.vo.PerfilVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static br.com.drwars.util.security.ValidaPermissao.validaPermissaoPerfilListar;
import static br.com.drwars.util.security.ValidaPermissao.validaPermissaoPerfilPersistir;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository repository;

    private Logger logger = Logger.getLogger(PerfilService.class.getName());


    public Page<PerfilVO> findPerfilByNome(String nome, Pageable pageable) {
        logger.warning("Listar todas perfis");
        validaPermissaoPerfilPersistir();
        Page<Perfil>  listaPage = null;
        if(nome==null || nome.isBlank()){
            listaPage= repository.findAll(pageable);
        }else {
            listaPage = repository.findPerfilByNome(nome, pageable);
        }
        var listaVosPage = listaPage.map(p-> DozerConverter.parseObject(p,PerfilVO.class));

        listaVosPage.stream()
                .forEach(p -> p.add(linkTo(methodOn(PerfilController.class).findById(p.getKey())).withSelfRel()));
        return listaVosPage;
    }

    public PerfilVO findById(Long id) {
        logger.warning("Buscar perfil por id");
        var model = (Perfil) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil  não  encontrado!"));
        var vo =  DozerConverter.parseObject(model, PerfilVO.class);
        vo.add(linkTo(methodOn(PerfilController.class).findById(id)).withSelfRel());
        return vo;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public PerfilVO create(PerfilVO vo) throws CampoInvalidoException {
        logger.warning("Cadastrar perfil");
        validaPermissaoPerfilPersistir();
        if(vo==null) throw  new RequiredObjectIsNullException("Preencher campos de Perfil!");
        var perfilExist = repository.findByNome(vo.getNome());
        if(perfilExist!=null)  {
            throw new CampoInvalidoException("Já existe perfil com esse nome!");
        }
        var model = new Perfil();

        model.setNome(vo.getNome());
        model.setDescricao(vo.getDescricao());
        model.setSituacao(true);
        model.setDataInclusao(LocalDateTime.now());
        model.setUsuarioInclusao(UsuarioLogado.getUser());
        model.setDataAlteracao(LocalDateTime.now());
        model.setUsuarioAlteracao(UsuarioLogado.getUser());

        repository.save(model);
        var retorno =  DozerConverter.parseObject(model, PerfilVO.class);
        retorno.add(linkTo(methodOn(PerfilController.class).findById(vo.getKey())).withSelfRel());
        return retorno;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public PerfilVO update(PerfilVO vo) throws CampoInvalidoException {
        logger.warning("Alterar perfil");
        validaPermissaoPerfilPersistir();
        if(vo==null) throw  new RequiredObjectIsNullException("Preencher campos de Perfil!");
        if(validaPermissaoPerfilPersistir()) {
        	throw new InvalidoPermissaoException("Usuário não tem credenciais para criação de tipo de perfil!");
        }
       var model = repository.findById(vo.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Perfil  não  encontrado!"));

       var perfilExist = repository.findByNome(vo.getNome());

       if(perfilExist!=null
               && !perfilExist.getId().equals(model.getId()))  {
            throw new CampoInvalidoException("Já existe perfil com esse nome!");
        }
        
        model.setNome(vo.getNome());
        model.setDescricao(vo.getDescricao());
        model.setDataAlteracao(LocalDateTime.now());
        model.setUsuarioAlteracao(UsuarioLogado.getUser());
        repository.save(model);
        var retorno =  DozerConverter.parseObject(model, PerfilVO.class);
        retorno.add(linkTo(methodOn(PerfilController.class).findById(vo.getKey())).withSelfRel());
        return retorno;
    }



}
