package br.com.drwars.v1.service;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Endereco;
import br.com.drwars.exception.ResourceNotFoundException;
import br.com.drwars.repositories.EnderecoRepository;
import br.com.drwars.v1.vo.EnderecoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
public class EnderecoService {

    private Logger logger = Logger.getLogger(EnderecoService.class.getName());
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public EnderecoVO create(EnderecoVO vo){
        var model =  DozerConverter.parseObject(vo, Endereco.class);
        enderecoRepository.save(model);
        var enderecoVO = DozerConverter.parseObject(model, EnderecoVO.class);
        return  enderecoVO;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public EnderecoVO update(Long id, EnderecoVO vo){
        var model = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não  encontrado!"));
        model.setBairro(vo.getBairro());
        model.setCep(vo.getCep());
        model.setCidade(vo.getCidade());
        model.setComplemento(vo.getComplemento());
        model.setEmail(vo.getEmail());
        model.setEstado(vo.getEstado());
        model.setLogradouro(vo.getLogradouro());
        model.setNumero(vo.getNumero());
        model.setTelefone(vo.getTelefone());
        model.setPais(vo.getPais());
        enderecoRepository.save(model);
        var enderecoVO = DozerConverter.parseObject(model, EnderecoVO.class);
        return  enderecoVO;
    }
}
