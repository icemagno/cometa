package br.com.drwars.v1.mock;

import br.com.drwars.entites.NoticiaSituacao;
import br.com.drwars.v1.vo.NoticiaSituacaoVO;

import java.util.ArrayList;
import java.util.List;

public class MockNoticiaSituacao {
    public NoticiaSituacao mockEntity() {
        return mockEntity(0l);
    }

    public NoticiaSituacaoVO mockVO() {
        return mockVO(0L);
    }

    public List<NoticiaSituacao> mockEntityList() {
        List<NoticiaSituacao> entity = new ArrayList<NoticiaSituacao>();
        for (long i = 0; i < 14; i++) {
            entity.add(mockEntity(i));
        }
        return entity;
    }

    public List<NoticiaSituacaoVO> mockVOList() {
        List<NoticiaSituacaoVO> list = new ArrayList<NoticiaSituacaoVO>();
        for (long i = 0; i < 14; i++) {
            list.add(mockVO(i));
        }
        return list;
    }



    public NoticiaSituacao mockEntity(Long number) {
        var model = new NoticiaSituacao();
        model.setId(number);
        model.setDescricao("Descricao"+number);
        return model;
    }

    public NoticiaSituacaoVO mockVO(Long number) {
        var vo = new NoticiaSituacaoVO();
        vo.setId(number);
        vo.setDescricao("Descricao"+number);
        return vo;
    }


}
