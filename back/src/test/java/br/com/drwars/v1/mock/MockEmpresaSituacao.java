package br.com.drwars.v1.mock;

import br.com.drwars.entites.EmpresaSituacao;
import br.com.drwars.v1.vo.EmpresaSituacaoVO;

import java.util.ArrayList;
import java.util.List;

public class MockEmpresaSituacao {
    public EmpresaSituacao mockEntity() {
        return mockEntity(0l);
    }

    public EmpresaSituacaoVO mockVO() {
        return mockVO(0L);
    }

    public List<EmpresaSituacao> mockEntityList() {
        List<EmpresaSituacao> entity = new ArrayList<EmpresaSituacao>();
        for (long i = 0; i < 14; i++) {
            entity.add(mockEntity(i));
        }
        return entity;
    }

    public List<EmpresaSituacaoVO> mockVOList() {
        List<EmpresaSituacaoVO> list = new ArrayList<>();
        for (long i = 0; i < 14; i++) {
            list.add(mockVO(i));
        }
        return list;
    }



    public EmpresaSituacao mockEntity(Long number) {
        var model = new EmpresaSituacao();
        model.setId(number);
        model.setDescricao("Descricao"+number);
        return model;
    }

    public EmpresaSituacaoVO mockVO(Long number) {
        var vo = new EmpresaSituacaoVO();
        vo.setId(number);
        vo.setDescricao("Descricao"+number);
        return vo;
    }


}
