package br.com.drwars.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public abstract class PasswordUtil {


    public static String getSenhaCriptografada(String  senha){
        var bCryptPasswordEncoder = new BCryptPasswordEncoder();
        var  password =bCryptPasswordEncoder.encode(senha);
        return password;
    }

    public static String gerarSenha() {
        var generatedString = RandomStringUtils.randomAlphabetic(10);
        return generatedString;
    }
}
