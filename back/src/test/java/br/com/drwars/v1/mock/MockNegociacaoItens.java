package br.com.drwars.v1.mock;

import br.com.drwars.entites.NegociacaoItens;
import br.com.drwars.v1.vo.NegociacaoItensVO;
import java.util.ArrayList;
import java.util.List;

public class MockNegociacaoItens {
    public NegociacaoItens mockEntity() {
        return mockEntity(0l);
    }

    public NegociacaoItensVO mockVO() {
        return mockVO(0L);
    }

    public List<NegociacaoItens> mockEntityList() {
        List<NegociacaoItens> entity = new ArrayList<NegociacaoItens>();
        for (long i = 0; i < 14; i++) {
            entity.add(mockEntity(i));
        }
        return entity;
    }

    public List<NegociacaoItensVO> mockVOList() {
        List<NegociacaoItensVO> list = new ArrayList<>();
        for (long i = 0; i < 14; i++) {
            list.add(mockVO(i));
        }
        return list;
    }



    public NegociacaoItens mockEntity(Long number) {
        var model = new NegociacaoItens();
        model.setId(number);
       // model.setComponente("Componente"+number);
        model.setDescricao("Descricao"+number);
        //model.setTipoValor("Tipo"+number);
        return model;
    }

    public NegociacaoItensVO mockVO(Long number) {
        var vo = new NegociacaoItensVO();
        vo.setId(number);
       // vo.setComponente("Componente"+number);
        vo.setDescricao("Descricao"+number);
        //vo.setTipoValor("Tipo"+number);
        return vo;
    }


}
