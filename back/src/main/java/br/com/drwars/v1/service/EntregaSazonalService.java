package br.com.drwars.v1.service;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.*;
import br.com.drwars.repositories.EntregaSazonaisRepository;
import br.com.drwars.v1.vo.EntregaSazonalVO;
import br.com.drwars.v1.vo.NegociacaoRodadaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
public class EntregaSazonalService {

    @Autowired
    private EntregaSazonaisRepository repository;

    private Logger logger = Logger.getLogger(EntregaSazonalService.class.getName());


    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public NegociacaoRodadaVO create(NegociacaoRodadaVO negociacaoRodadavo, List<EntregaSazonalVO> lista) {
        logger.warning("Cadastrar Entregas Sazonais");
        var negociacaoRodada =  DozerConverter.parseObject(negociacaoRodadavo, NegociacaoRodada.class);
        if(!lista.isEmpty()){
            for(EntregaSazonalVO entregaSazonalVO : lista){
                var entrega = DozerConverter.parseObject(entregaSazonalVO, EntregaSazonais.class);
                entrega.setNegociacaoRodada(negociacaoRodada);
                entrega.setMesEntrega(entregaSazonalVO.getMes());
                repository.save(entrega);
            }
        }

        return  DozerConverter.parseObject(negociacaoRodada,NegociacaoRodadaVO.class);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public EntregaSazonalVO create(EntregaSazonais entregaSazonais) {
        logger.warning("Cadastrar Entregas Sazonais");
        repository.save(entregaSazonais);
        return  DozerConverter.parseObject(entregaSazonais,EntregaSazonalVO.class);
    }





}
