package br.com.drwars.v1.service;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Estado;
import br.com.drwars.entites.Perfil;
import br.com.drwars.repositories.EstadoRepository;
import br.com.drwars.repositories.PerfilRepository;
import br.com.drwars.v1.vo.EstadoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    private Logger logger = Logger.getLogger(EstadoService.class.getName());


    public List<EstadoVO> findAll() {
        logger.warning("Listar todos estados");
        List<Estado> lista  = repository.findAll();
        var listaVosPage = lista.stream().map(p-> DozerConverter.parseObject(p,EstadoVO.class)).collect(Collectors.toList());
        return listaVosPage;
    }


}
