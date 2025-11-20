package br.com.drwars.v1.mock;

import br.com.drwars.entites.Endereco;
import br.com.drwars.v1.vo.EnderecoVO;

import java.util.ArrayList;
import java.util.List;

public class MockEndereco {
    public Endereco mockEntity() {
        return mockEntity(0l);
    }

    public EnderecoVO mockVO() {
        return mockVO(0L);
    }

    public List<Endereco> mockEntityList() {
        List<Endereco> entity = new ArrayList<Endereco>();
        for (long i = 0; i < 14; i++) {
            entity.add(mockEntity(i));
        }
        return entity;
    }

    public List<EnderecoVO> mockVOList() {
        List<EnderecoVO> list = new ArrayList<>();
        for (long i = 0; i < 14; i++) {
            list.add(mockVO(i));
        }
        return list;
    }



    public Endereco mockEntity(Long number) {
        var model = new Endereco();
        model.setId(number);
        model.setBairro("Bairro"+number);
        model.setCep("Cep"+number);
        model.setCidade("Cidade"+number);
        model.setComplemento("Complemento"+number);
        model.setEmail("Email"+number);
        model.setEstado("Estado"+number);
        model.setLogradouro("Logradouro"+number);
        model.setNumero("Numero"+number);
        model.setTelefone("Telefone"+number);
        model.setPais("Pais"+number);
        return model;
    }

    public EnderecoVO mockVO(Long number) {
        var vo = new EnderecoVO();
        vo.setId(number);
        vo.setBairro("Bairro"+number);
        vo.setCep("Cep"+number);
        vo.setCidade("Cidade"+number);
        vo.setComplemento("Complemento"+number);
        vo.setEmail("Email"+number);
        vo.setEstado("Estado"+number);
        vo.setLogradouro("Logradouro"+number);
        vo.setNumero("Numero"+number);
        vo.setTelefone("Telefone"+number);
        vo.setPais("Pais"+number);
        return vo;
    }


}
