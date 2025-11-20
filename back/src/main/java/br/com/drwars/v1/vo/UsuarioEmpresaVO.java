package br.com.drwars.v1.vo;

import br.com.drwars.entites.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEmpresaVO {

    private Long id;
    private String email;
    private String senha;
    private String login;
    private String fullName;
    private Long idperfil;
    private EmpresaVO empresa;

}
