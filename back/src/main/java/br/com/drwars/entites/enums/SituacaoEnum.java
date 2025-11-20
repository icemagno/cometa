package br.com.drwars.entites.enums;

public enum SituacaoEnum {
    A("Ativo"),I("Inativo");


    public String descricao;
    SituacaoEnum(String descricao) {
        this.descricao = descricao;
    }
}
