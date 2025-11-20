package br.com.drwars.util;

import br.com.drwars.entites.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public  abstract class UsuarioLogado {


    public static Usuario getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null){
            if(auth.getPrincipal() instanceof Usuario){
                return  (Usuario)  auth.getPrincipal();
            }
        }
        return null;
    }
}
