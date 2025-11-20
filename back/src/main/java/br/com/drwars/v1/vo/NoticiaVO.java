package br.com.drwars.v1.vo;



import br.com.drwars.entites.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticiaVO {

    private Long id;
    private LocalDateTime data;
    private String texto;
    private String titulo;
    private NoticiaSituacao situacao;
}
