package br.com.drwars.v1.vo.oferta;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OfertaTipoVO {

    private Long id;
    private String descricao;

    public String getNome(){
        if(descricao.equals("OT_BIOMETANO")){
            return "Biometano";
        } else if(descricao.equals("OT_BIOMETANO_CERTIFICADO")){
            return "Biometano e Certificado";
        }else if(descricao.equals("OT_CERTIFICADO")){
            return "Certificado";
        }

        return " ";
    }
}
