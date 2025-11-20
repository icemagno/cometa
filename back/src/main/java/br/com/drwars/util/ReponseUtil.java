package br.com.drwars.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public  abstract class ReponseUtil {

    public  static ResponseEntity<Object> getResponseGet(Object obj){
        return obj==null || obj instanceof List && ((List)obj).isEmpty() ? getNotFound() : getOk(obj) ;
    }

    private static ResponseEntity<Object> getOk(Object obj) {
        return ResponseEntity.ok(obj);
    }

    private static ResponseEntity<Object> getNotFound() {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
    }

    public static ResponseEntity getNoContent() {
        return ResponseEntity.noContent().build();
    }
}
