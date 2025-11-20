package br.com.drwars.v1.mock;

import br.com.drwars.entites.Perfil;
import br.com.drwars.entites.Usuario;
import br.com.drwars.v1.vo.PerfilVO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class MockPerfil {
    public Perfil mockEntity() {
        return mockEntity(0l);
    }

    public PerfilVO mockVO() {
        return mockVO(0L);
    }

    public List<Perfil> mockEntityList() {
        List<Perfil> perfil = new ArrayList<Perfil>();
        for (long i = 0; i < 14; i++) {
            perfil.add(mockEntity(i));
        }
        return perfil;
    }

    public List<PerfilVO> mockVOList() {
        List<PerfilVO> list = new ArrayList<>();
        for (long i = 0; i < 14; i++) {
            list.add(mockVO(i));
        }
        return list;
    }



    public Perfil mockEntity(Long number) {
        var model = new Perfil();
        model.setId(number);
        model.setNome("Perfil"+number);
        model.setDescricao("Descricao"+number);
        model.setSituacao(true);
        model.setDataInclusao(LocalDateTime.now());
        model.setDataAlteracao(LocalDateTime.now());
        model.setUsuarioAlteracao(mockUsuarioEntity(1l));
        model.setUsuarioAlteracao(mockUsuarioEntity(2l));
        return model;
    }

    public PerfilVO mockVO(Long number) {
        var p = new PerfilVO();
        p.setKey(number);
        p.setNome("Perfil"+number);
        p.setDescricao("Descricao"+number);
        p.setSituacao(true);
        p.setSituacao(true);
        return p;
    }

    public Usuario mockUsuarioEntity(Long number) {
        var model = new Usuario();
        model.setId(number);
        model.setUserName("username"+number);
        model.setFullName("fullname"+number);
        return model;
    }
}
