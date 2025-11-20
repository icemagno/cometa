package br.com.drwars.v1.mock;

import br.com.drwars.entites.OfertaTipo;
import br.com.drwars.v1.vo.oferta.OfertaTipoVO;

import java.util.ArrayList;
import java.util.List;

public class MockOfertaTipos {
    public OfertaTipo mockEntity() {
        return mockEntity(0l);
    }

    public OfertaTipoVO mockVO() {
        return mockVO(0L);
    }

    public List<OfertaTipo> mockEntityList() {
        List<OfertaTipo> entity = new ArrayList<OfertaTipo>();
        for (long i = 0; i < 14; i++) {
            entity.add(mockEntity(i));
        }
        return entity;
    }

    public List<OfertaTipoVO> mockVOList() {
        List<OfertaTipoVO> list = new ArrayList<>();
        for (long i = 0; i < 14; i++) {
            list.add(mockVO(i));
        }
        return list;
    }



    public OfertaTipo mockEntity(Long number) {
        var model = new OfertaTipo();
        model.setId(number);
        model.setDescricao("Descricao"+number);
        return model;
    }

    public OfertaTipoVO mockVO(Long number) {
        var vo = new OfertaTipoVO();
        vo.setId(number);
        vo.setDescricao("Descricao"+number);
        return vo;
    }


}
