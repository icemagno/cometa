package br.com.drwars.v1.vo;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EmpresaTipoVO {

    private Long id;

    private String descricao;
}
