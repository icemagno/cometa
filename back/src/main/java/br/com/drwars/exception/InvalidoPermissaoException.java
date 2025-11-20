package br.com.drwars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
public class InvalidoPermissaoException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public InvalidoPermissaoException(String exception) {
        super(exception);
    }

}