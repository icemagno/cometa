package br.com.drwars.v1.vo.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioTrocaSenhaVO {

    private String senhaAtual;
    private String novaSenha;

    private String confirmaSenha;

}
