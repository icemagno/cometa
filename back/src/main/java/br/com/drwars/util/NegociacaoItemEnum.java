package br.com.drwars.util;

import br.com.drwars.exception.CampoInvalidoException;

public enum NegociacaoItemEnum {
    SAZONALIDADE(3L,"Continua ou  Sazanalidade (C | S) ")
    ,CRONOGRAMA_ENTREGA(4L,"Itens da Sazonalidade")
    ,LOCAL_ENTREGA(5L,"Deve estar cadastrado o Endereço (Deve ser incluido o ID ?)")
    ,FLEXIBILIDADE(8L,"Firme ou Flexível  (0 | 1) ")
    ,VALOR_MOLÉCULA(13L,"Quanto está cobrando  por molécula")
    ,TIPO_FRETE(14L,"CIF ou FOB")
    ,ESTADO(15L,"GNC | GNL (Aonde está isso)")
    ,VALOR_CERTIFICADO(17L,"Valor monetário")
    ,PERÍODO_ENTREGA(18L,"Duração do Fornecimento ?")
    ,VALOR_FRETE(19L,"Valor monetário")
    ,MEIO_TRANSPORTE(20L,"Caminhão, duto");


    private Long id;
    public String descricao;
    NegociacaoItemEnum(Long id,String descricao) {
        this.id = id;
        this.descricao = descricao;
    }



}
