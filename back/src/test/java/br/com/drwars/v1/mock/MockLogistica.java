package br.com.drwars.v1.mock;

import br.com.drwars.entites.Logistica;
import br.com.drwars.entites.enums.TipoTransportelEnum;
import br.com.drwars.v1.vo.LogisticaVO;

import java.util.ArrayList;
import java.util.List;

public class MockLogistica {
    public Logistica mockEntity() {
        return mockEntity(0l);
    }

    public LogisticaVO mockVO() {
        return mockVO(0L);
    }

    public List<Logistica> mockEntityList() {
        List<Logistica> entity = new ArrayList<Logistica>();
        for (long i = 0; i < 14; i++) {
            entity.add(mockEntity(i));
        }
        return entity;
    }

    public List<LogisticaVO> mockVOList() {
        List<LogisticaVO> list = new ArrayList<>();
        for (long i = 0; i < 14; i++) {
            list.add(mockVO(i));
        }
        return list;
    }



    public Logistica mockEntity(Long number) {
        var model = new Logistica();
        model.setId(number);
        model.setTipoTransporte(TipoTransportelEnum.GNC);
        model.setEmpresa(new MockEmpresa().mockEntity(number));
        return model;
    }

    public LogisticaVO mockVO(Long number) {
        var vo = new LogisticaVO();
        vo.setId(number);
        vo.setTipoTransporte(TipoTransportelEnum.GNL);
        vo.setEmpresa(new MockEmpresa().mockVO(number));
        return vo;
    }


}
