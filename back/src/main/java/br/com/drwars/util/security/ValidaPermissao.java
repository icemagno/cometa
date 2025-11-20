package br.com.drwars.util.security;

import br.com.drwars.exception.InvalidoPermissaoException;
import br.com.drwars.util.UsuarioLogado;

import static br.com.drwars.util.Constantes.PERFIL_ADMIN;
import static br.com.drwars.util.Constantes.PERFIL_DRWARSLABS;

public class ValidaPermissao {

    public static boolean validaPermissaoAutorizadora() {
//        if(UsuarioLogado.getUser()==null ||  !UsuarioLogado.getUser().getPerfil().getNome().equals(PERFIL_DRWARSLABS))
 //           throw new InvalidoPermissaoException("Usuário não tem credenciais para essa ação!");
        return true;
    }

    public static boolean validaPermissaoAutorizadoraListar() {
//        if(UsuarioLogado.getUser()==null ||  !UsuarioLogado.getUser().getPerfil().getNome().equals(PERFIL_DRWARSLABS))
//            throw new InvalidoPermissaoException("Usuário não tem credenciais para essa ação!");
        return true;
    }

    public static boolean validaPermissaoCertificadora() {
//        if(UsuarioLogado.getUser()==null ||  !UsuarioLogado.getUser().getPerfil().getNome().equals(PERFIL_DRWARSLABS))
//            throw new InvalidoPermissaoException("Usuário não tem credenciais para essa ação!");
        return true;
    }

    public static boolean validaPermissaoCertificadoraListar() {
//        if(UsuarioLogado.getUser()==null)
//            throw new InvalidoPermissaoException("Usuário não tem credenciais para essa ação!");
        return true;
    }

    public static boolean validaPermissaoEmpresa() {
//        if(validaLogado() ||  !UsuarioLogado.getUser().getPerfil().getNome().equals(PERFIL_DRWARSLABS))
//            throw new InvalidoPermissaoException("Usuário não tem credenciais para essa ação!");
        return true;
    }

    public static boolean validaPermissaoEmpresaListar() {
//        if(validaLogado() ||  !UsuarioLogado.getUser().getPerfil().getNome().equals(PERFIL_DRWARSLABS))
//            throw new InvalidoPermissaoException("Usuário não tem credenciais para essa ação!");
        return true;
    }

    public static boolean validaPermissaoFreteListar() {
//        if(!validaLogado())
//            throw new InvalidoPermissaoException("Usuário não tem credenciais para essa ação!");
        return true;
    }

    public static boolean validaPermissaoLocalPersistir() {
//        if(!validaLogado())
//            throw new InvalidoPermissaoException("Usuário não tem credenciais para essa ação!");
        return true;
    }

    public static boolean validaPermissaoLocalListar() {
//        if(!validaLogado())
//            throw new InvalidoPermissaoException("Usuário não tem credenciais para essa ação!");
        return true;
    }

    public static boolean validaPermissaoPerfilPersistir() {
//        if(UsuarioLogado.getUser()==null ||  !UsuarioLogado.getUser().getPerfil().getNome().equals(PERFIL_DRWARSLABS))
//            throw new InvalidoPermissaoException("Usuário não tem credenciais para essa ação!");
        return true;
    }

    public static boolean validaPermissaoPerfilListar() {
//        if(UsuarioLogado.getUser()==null ||  !UsuarioLogado.getUser().getPerfil().getNome().equals(PERFIL_DRWARSLABS))
//            throw new InvalidoPermissaoException("Usuário não tem credenciais para essa ação!");
        return true;
    }

    public static boolean validaPermissaoUsuarioPersistir() {
/*
    	if(validaLogado()
                ||
                (!UsuarioLogado.getUser().getPerfil().getNome().equals(PERFIL_DRWARSLABS)
                && !UsuarioLogado.getUser().getPerfil().getNome().equals(PERFIL_ADMIN))
        )
            throw new InvalidoPermissaoException("Usuário não tem credenciais para essa ação!");
*/
        return true;
    }

    public static boolean validaPermissaoUsuarioListar() {
/*
    	if(validaLogado()
                ||
                (!UsuarioLogado.getUser().getPerfil().getNome().equals(PERFIL_DRWARSLABS)
                        && !UsuarioLogado.getUser().getPerfil().getNome().equals(PERFIL_ADMIN))
        )
            throw new InvalidoPermissaoException("Usuário não tem credenciais para essa ação!");
*/            
        return true;
    }

    public static boolean validaLogado() {
//        if(UsuarioLogado.getUser()==null)
//            throw new InvalidoPermissaoException("Usuário não tem credenciais para essa ação!");
        return true;
    }
}
