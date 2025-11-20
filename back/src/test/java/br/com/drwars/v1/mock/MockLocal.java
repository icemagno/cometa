package br.com.drwars.v1.mock;

import br.com.drwars.entites.Local;
import br.com.drwars.v1.vo.LocalVO;

import java.util.ArrayList;
import java.util.List;

public class MockLocal {

    public Local mockEntity() {
        return mockEntity(0l);
    }

    public LocalVO mockVO() {
        return mockVO(0L);
    }

    public List<Local> mockEntityList() {
        List<Local> lista = new ArrayList<Local>();
        for (long i = 0; i < 14; i++) {
            lista.add(mockEntity(i));
        }
        return lista;
    }

    public List<LocalVO> mockVOList() {
        List<LocalVO> list = new ArrayList<LocalVO>();
        for (long i = 0; i < 14; i++) {
            list.add(mockVO(i));
        }
        return list;
    }



    public Local mockEntity(Long number) {
        var model = new Local();
        model.setId(number);
        model.setDescricao("Descricao"+number);
        model.setEndereco(new MockEndereco().mockEntity(number));
        return model;
    }

    public LocalVO mockVO(Long number) {
        var p = new LocalVO();
        p.setKey(number);
        p.setDescricao("Descricao"+number);
        p.setEndereco(new MockEndereco().mockVO(number));
        return p;
    }

}
