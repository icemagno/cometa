package br.com.drwars.v1.service;


import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Empresa;
import br.com.drwars.entites.Logistica;
import br.com.drwars.exception.CampoInvalidoException;
import br.com.drwars.exception.ResourceNotFoundException;
import br.com.drwars.repositories.LogisticaRepository;
import br.com.drwars.v1.vo.LogisticaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
public class LogisiticaService {

    private Logger logger = Logger.getLogger(LogisiticaService.class.getName());
    @Autowired
    private LogisticaRepository repository;

    @Autowired
    private EmpresaService empresaService;


    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Logistica create(LogisticaVO vo){
        var model =  DozerConverter.parseObject(vo, Logistica.class);
        if(vo.getIdEmpresa()!=null
                && vo.getIdEmpresa() >0){
            var empresa = empresaService.findEmpresaById(vo.getIdEmpresa());
            model.setEmpresa(empresa);
        }else if(model.getEmpresa()==null){
           throw new ResourceNotFoundException("Empresa  não  encontrado!");
        }
        repository.save(model);
        return  model;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Logistica update(Long id, LogisticaVO vo){
        var model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Logística  não  encontrado!"));

        if(vo.getIdEmpresa()!=null
                && vo.getIdEmpresa() >0){
            var empresa = empresaService.findEmpresaById(vo.getIdEmpresa());
            model.setEmpresa(empresa);
        }else if(model.getEmpresa()==null){
            throw new ResourceNotFoundException("Empresa  não  encontrado!");
        }
        model.setTipoTransporte(vo.getTipoTransporte());
        repository.save(model);
        return  model;
    }

}