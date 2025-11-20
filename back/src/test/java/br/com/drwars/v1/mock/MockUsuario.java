package br.com.drwars.v1.mock;


import br.com.drwars.entites.Perfil;
import br.com.drwars.entites.Usuario;
import br.com.drwars.v1.vo.UsuarioVO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockUsuario {
    public Usuario mockEntity() {
        return mockEntity(0l);
    }

    public UsuarioVO mockVO() {
        return mockVO(0L);
    }

    public List<Usuario> mockEntityList() {
        var lista = new ArrayList<Usuario>();
        for (long i = 0; i < 14; i++) {
            lista.add(mockEntity(i));
        }
        return lista;
    }

    public List<UsuarioVO> mockVOList() {
        var lista = new ArrayList<UsuarioVO>();
        for (long i = 0; i < 14; i++) {
            lista.add(mockVO(i));
        }
        return lista;
    }

    public Usuario mockEntity(Long number) {
        var model = new Usuario();
        model.setId(number);
        model.setUserName("UserName"+number);
        model.setFullName("FullName"+number);
        model.setPerfil(mockPerfilEntity(number));

        model.setAccountNonExpired(true);
        model.setAccountNonLocked(true);
        model.setCredentialsNonExpired(true);
        model.setEnabled(true);

        model.setDataInclusao(LocalDateTime.now());
        model.setDataAlteracao(LocalDateTime.now());
        model.setUsuarioAlteracao(mockUsuarioEntity(1l));
        model.setUsuarioAlteracao(mockUsuarioEntity(2l));
        return model;
    }

    public UsuarioVO mockVO(Long number) {
        var vo = new UsuarioVO();

        return vo;
    }

    private Usuario mockUsuarioEntity(Long number) {
        var model = new Usuario();
        model.setId(number);
        model.setUserName("username"+number);
        model.setFullName("fullname"+number);
        return model;
    }

    private Perfil mockPerfilEntity(Long number) {
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
}
