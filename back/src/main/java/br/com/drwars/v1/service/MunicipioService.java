package br.com.drwars.v1.service;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Municipio;
import br.com.drwars.repositories.MunicipioRepository;
import br.com.drwars.v1.vo.MunicipioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository repository;

    private Logger logger = Logger.getLogger(MunicipioService.class.getName());


    public List<MunicipioVO> findAll() {
        logger.warning("Listar todos municipios");
        List<Municipio> lista  = repository.findAll();
        var listaVosPage = lista.stream().map(p-> DozerConverter.parseObject(p,MunicipioVO.class)).collect(Collectors.toList());
        return listaVosPage;
    }

    public List<MunicipioVO> findByUf(String uf) {
        logger.warning("Listar todos municipios por estado");
        List<Municipio> lista  = repository.findByUf(uf);
        var listaVosPage = lista.stream().map(p-> DozerConverter.parseObject(p,MunicipioVO.class)).collect(Collectors.toList());
        return listaVosPage;
    }

}
