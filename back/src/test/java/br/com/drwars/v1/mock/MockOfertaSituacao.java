package br.com.drwars.v1.mock;

import br.com.drwars.entites.OfertaSituacao;
import br.com.drwars.v1.vo.oferta.OfertaSituacaoVO;

import java.util.ArrayList;
import java.util.List;

public class MockOfertaSituacao {
    public OfertaSituacao mockEntity() {
        return mockEntity(0l);
    }

    public OfertaSituacaoVO mockVO() {
        return mockVO(0L);
    }

    public List<OfertaSituacao> mockEntityList() {
        List<OfertaSituacao> entity = new ArrayList<OfertaSituacao>();
        for (long i = 0; i < 14; i++) {
            entity.add(mockEntity(i));
        }
        return entity;
    }

    public List<OfertaSituacaoVO> mockVOList() {
        List<OfertaSituacaoVO> list = new ArrayList<>();
        for (long i = 0; i < 14; i++) {
            list.add(mockVO(i));
        }
        return list;
    }



    public OfertaSituacao mockEntity(Long number) {
        var model = new OfertaSituacao();
        model.setId(number);
        model.setDescricao("Descricao"+number);
        return model;
    }

    public OfertaSituacaoVO mockVO(Long number) {
        var vo = new OfertaSituacaoVO();
        vo.setId(number);
        vo.setDescricao("Descricao"+number);
        return vo;
    }


}
