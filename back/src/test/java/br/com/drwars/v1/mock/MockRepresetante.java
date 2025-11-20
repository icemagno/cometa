package br.com.drwars.v1.mock;

import br.com.drwars.entites.Representante;
import br.com.drwars.v1.vo.RepresentanteVO;

import java.util.ArrayList;
import java.util.List;

public class MockRepresetante {

    public Representante mockEntity() {
        return mockEntity(0l);
    }

    public RepresentanteVO mockVO() {
        return mockVO(0L);
    }

    public List<Representante> mockEntityList() {
        List<Representante> lista = new ArrayList<Representante>();
        for (long i = 0; i < 14; i++) {
            lista.add(mockEntity(i));
        }
        return lista;
    }

    public List<RepresentanteVO> mockVOList() {
        List<RepresentanteVO> list = new ArrayList<>();
        for (long i = 0; i < 14; i++) {
            list.add(mockVO(i));
        }
        return list;
    }



    public Representante mockEntity(Long number) {
        var model = new Representante();
        model.setId(number);
        model.setNome("Nome"+number);
        model.setCpf("Cpf"+number);
        model.setEndereco(new MockEndereco().mockEntity(number));
        return model;
    }

    public RepresentanteVO mockVO(Long number) {
        var p = new RepresentanteVO();
        p.setId(number);
        p.setNome("Nome"+number);
        p.setCpf("Cpf"+number);
        p.setEndereco(new MockEndereco().mockVO(number));
        return p;
    }

}
