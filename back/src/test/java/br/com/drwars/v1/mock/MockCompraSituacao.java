package br.com.drwars.v1.mock;

import br.com.drwars.entites.CompraSituacao;
import br.com.drwars.v1.vo.compra.CompraSituacaoVO;

import java.util.ArrayList;
import java.util.List;

public class MockCompraSituacao {
    public CompraSituacao mockEntity() {
        return mockEntity(0l);
    }

    public CompraSituacaoVO mockVO() {
        return mockVO(0L);
    }

    public List<CompraSituacao> mockEntityList() {
        List<CompraSituacao> entity = new ArrayList<CompraSituacao>();
        for (long i = 0; i < 14; i++) {
            entity.add(mockEntity(i));
        }
        return entity;
    }

    public List<CompraSituacaoVO> mockVOList() {
        List<CompraSituacaoVO> list = new ArrayList<>();
        for (long i = 0; i < 14; i++) {
            list.add(mockVO(i));
        }
        return list;
    }



    public CompraSituacao mockEntity(Long number) {
        var model = new CompraSituacao();
        model.setId(number);
        model.setDescricao("Descricao"+number);
        return model;
    }

    public CompraSituacaoVO mockVO(Long number) {
        var vo = new CompraSituacaoVO();
        vo.setId(number);
        vo.setDescricao("Descricao"+number);
        return vo;
    }


}
