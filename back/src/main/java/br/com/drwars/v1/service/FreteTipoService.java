package br.com.drwars.v1.service;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.FreteTipo;
import br.com.drwars.exception.ResourceNotFoundException;
import br.com.drwars.repositories.FreteTipoRepository;
import br.com.drwars.v1.controller.FreteTipoController;
import br.com.drwars.v1.controller.PerfilController;
import br.com.drwars.v1.vo.FreteTipoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static br.com.drwars.util.security.ValidaPermissao.validaPermissaoFreteListar;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class FreteTipoService {

    @Autowired
    private FreteTipoRepository repository;

    private Logger logger = Logger.getLogger(FreteTipoService.class.getName());


    public List<FreteTipoVO> findAll() {
        logger.warning("Listar Fretes Tipo");
        validaPermissaoFreteListar();
        var lista = DozerConverter.parseListObjects(repository.findAll(), FreteTipoVO.class);
        lista
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(FreteTipoController.class).findById(p.getKey())).withSelfRel()));
        return lista;
    }

    public FreteTipoVO findById(Long id) {
        validaPermissaoFreteListar();
        logger.warning("Buscar Frete Tipo por id");
        var model = (FreteTipo) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Frete Tipo não encontrado!"));
        var vo =  DozerConverter.parseObject(model, FreteTipoVO.class);
        vo.add(linkTo(methodOn(FreteTipoController.class).findById(id)).withSelfRel());
        return vo;
    }

    public FreteTipoVO findFreteTipoBySigla(String sigla) {
        validaPermissaoFreteListar();
        logger.warning("Buscar Frete Tipo por sigla");
        var model = (FreteTipo) repository.findFreteTipoBySigla(sigla);
        var vo =  DozerConverter.parseObject(model, FreteTipoVO.class);
        vo.add(linkTo(methodOn(FreteTipoController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }


}
