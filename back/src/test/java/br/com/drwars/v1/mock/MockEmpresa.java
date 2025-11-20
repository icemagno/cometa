package br.com.drwars.v1.mock;

import br.com.drwars.entites.Empresa;
import br.com.drwars.v1.vo.EmpresaVO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockEmpresa {
    public Empresa mockEntity() {
        return mockEntity(0l);
    }

    public EmpresaVO mockVO() {
        return mockVO(0L);
    }

    public List<Empresa> mockEntityList() {
        List<Empresa> entity = new ArrayList<Empresa>();
        for (long i = 0; i < 14; i++) {
            entity.add(mockEntity(i));
        }
        return entity;
    }

    public List<EmpresaVO> mockVOList() {
        List<EmpresaVO> list = new ArrayList<>();
        for (long i = 0; i < 14; i++) {
            list.add(mockVO(i));
        }
        return list;
    }

    public Empresa mockEntity(Long number) {
        var model = new Empresa();
        model.setId(number);
        model.setCnpj("Cnpj"+number);
        model.setDataAlteracao(LocalDateTime.now());
        model.setDataInclusao(LocalDateTime.now());
        model.setEndereco(new MockEndereco().mockEntity(number));
        model.setEmpresaSituacao(new MockEmpresaSituacao().mockEntity(number));
      //  model.setEmpresaRepresetantes(new MockRepresetante().mockEntityList());
        model.setNichoMercado("NichoMercado"+number);
        model.setNomeFatasia("NomeFatasia"+number);
        model.setRazaoSocial("RazaoSocial"+number);
        model.setInscricaoEstadual("InscricaoEstadual"+number);
        model.setInscricaoMunicipal("InscricaoMunicipal"+number);
        model.setServicosProdutos("ServicosProdutos"+number);
        model.setPorteEmpresa("PorteEmpresa"+number);

        return model;
    }

    public EmpresaVO mockVO(Long number) {
        var vo = new EmpresaVO();
        vo.setId(number);
        vo.setId(number);
        vo.setCnpj("Cnpj"+number);

        vo.setEndereco(new MockEndereco().mockVO(number));
        vo.setEmpresaSituacao(new MockEmpresaSituacao().mockVO(number));
        vo.setRepresetantes(new MockRepresetante().mockVOList());
        vo.setNichoMercado("NichoMercado"+number);
        vo.setNomeFatasia("NomeFatasia"+number);
        vo.setRazaoSocial("RazaoSocial"+number);
        vo.setInscricaoEstadual("InscricaoEstadual"+number);
        vo.setInscricaoMunicipal("InscricaoMunicipal"+number);
        vo.setServicosProdutos("ServicosProdutos"+number);
        vo.setPorteEmpresa("PorteEmpresa"+number);

        return vo;
    }


}
