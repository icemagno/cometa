package br.com.drwars.v1.vo;

import br.com.drwars.entites.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioVO {

    private Long id;
    private String email;
    private String login;
    private String fullName;
    private Long idperfil;
    private Long idempresa;

    public UsuarioVO(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.login = usuario.getUsername();
        this.fullName = usuario.getFullName();
        this.idperfil = usuario.getPerfil() !=null ? usuario.getPerfil().getId() : null;
        this.idempresa = usuario.getEmpresa()!=null ? usuario.getEmpresa().getId():null;
    }
}
