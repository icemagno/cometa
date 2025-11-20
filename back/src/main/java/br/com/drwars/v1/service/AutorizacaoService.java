package br.com.drwars.v1.service;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Autorizacao;
import br.com.drwars.exception.ResourceNotFoundException;
import br.com.drwars.repositories.AutorizacaoRepository;
import br.com.drwars.v1.controller.AutorizacaoController;
import br.com.drwars.v1.vo.autorizadora.AutorizacaoCreateVO;
import br.com.drwars.v1.vo.autorizadora.AutorizacaoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.logging.Logger;

import static br.com.drwars.util.security.ValidaPermissao.validaPermissaoAutorizadora;
import static br.com.drwars.util.security.ValidaPermissao.validaPermissaoAutorizadoraListar;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AutorizacaoService {

    @Autowired
    private AutorizacaoRepository repository;

    @Autowired
    private EmpresaService empresaService;

    private Logger logger = Logger.getLogger(AutorizacaoService.class.getName());


    public Page<AutorizacaoVO> findAll(Pageable pageable) {
        logger.warning("Listar todas Certificado");
        validaPermissaoAutorizadoraListar();
        Page<Autorizacao>  listaPage= repository.findAll(pageable);
        var listaVosPage = listaPage.map(p-> DozerConverter.parseObject(p, AutorizacaoVO.class));
        listaVosPage.stream().forEach(p -> p.add(linkTo(methodOn(AutorizacaoController.class).findById(p.getKey())).withSelfRel()));
        return listaVosPage;
    }

    public AutorizacaoVO findById(Long id) {
        logger.warning("Buscar Autorizacao por id");
        validaPermissaoAutorizadoraListar();
        var model = (Autorizacao) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autorizacao não encontrado!"));
        var vo =  DozerConverter.parseObject(model, AutorizacaoVO.class);
        vo.add(linkTo(methodOn(AutorizacaoController.class).findById(id)).withSelfRel());
        return vo;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public AutorizacaoVO create(AutorizacaoCreateVO voCreate) {
        logger.warning("Cadastrar Autorizacao");
        validaPermissaoAutorizadora();
        var model = new Autorizacao();
        model.setDataAutorizacao(LocalDate.parse(voCreate.getDataAutorizacao()).atTime(00,00));
        model.setDataValidade(LocalDate.parse(voCreate.getDataValidade()).atTime(00,00));
        model.setEmpresaAutorizada(empresaService.findEmpresaById(voCreate.getIdEmpresaAutorizada()));
        repository.save(model);
        var vo =  DozerConverter.parseObject(model, AutorizacaoVO.class);
        vo.add(linkTo(methodOn(AutorizacaoController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public AutorizacaoVO update(Long id, AutorizacaoCreateVO voCreate) {
        logger.warning("Alterar certificado");
        validaPermissaoAutorizadora();
        var model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autorizacao  não  encontrado!"));

        model.setDataAutorizacao(LocalDate.parse(voCreate.getDataAutorizacao()).atTime(00,00));
        model.setDataValidade(LocalDate.parse(voCreate.getDataValidade()).atTime(00,00));
        model.setEmpresaAutorizada(empresaService.findEmpresaById(voCreate.getIdEmpresaAutorizada()));
        repository.save(model);
        var vo =  DozerConverter.parseObject(model, AutorizacaoVO.class);
        vo.add(linkTo(methodOn(AutorizacaoController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }



}
