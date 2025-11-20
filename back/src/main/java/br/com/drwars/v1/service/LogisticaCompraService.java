package br.com.drwars.v1.service;

import br.com.drwars.entites.*;
import br.com.drwars.repositories.LogisticaCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;


@Service
public class LogisticaCompraService {


    @Autowired
    private LogisticaCompraRepository logisticaCompraRepository;

    @Autowired
    private LogisticaCompraRepository logisticaRepository;
    private Logger logger = Logger.getLogger(LogisticaCompraService.class.getName());


    @Autowired
    private UsuarioService usuarioService;


    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public LogisticaCompra create(Compra compra, String valorFrete) {
        logger.warning("Cadastrar Compra");

        return null;
    }

}
