package br.com.drwars.v1.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmpresaVO {

    private Long id;
    private String cnpj;
    private String razaoSocial;
    private String nomeFatasia;
    private EnderecoVO endereco;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String nichoMercado;
    private String servicosProdutos;
    private String porteEmpresa;
    private EmpresaSituacaoVO empresaSituacao;
    private List<RepresentanteVO> represetantes;
    private EmpresaTipoVO empresaTipo;
    private List<Long> listaMunicipiosDutos;
    private List<Long> listaMunicipiosRodoviaria;
}