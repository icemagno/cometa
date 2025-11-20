package br.com.drwars.v1.mock;

import br.com.drwars.entites.Compra;
import br.com.drwars.v1.vo.compra.CompraVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MockCompra {
    public Compra mockEntity() {
        return mockEntity(0l);
    }

    public CompraVO mockVO() {
        return mockVO(0L);
    }

    public List<Compra> mockEntityList() {
        List<Compra> entity = new ArrayList<Compra>();
        for (long i = 0; i < 14; i++) {
            entity.add(mockEntity(i));
        }
        return entity;
    }

    public List<CompraVO> mockVOList() {
        List<CompraVO> list = new ArrayList<>();
        for (long i = 0; i < 14; i++) {
            list.add(mockVO(i));
        }
        return list;
    }

    public Compra mockEntity(Long number) {
        var model = new Compra();
        model.setId(number);
        model.setSazonalidade(true);
        model.setEmpresa(new MockEmpresa().mockEntity(number));
        model.setLocalRetirada(new MockLocal().mockEntity(number));
        model.setLocalRetirada(new MockLocal().mockEntity(number+1));
        model.setQuantidade(1L);
        model.setValor(BigDecimal.TEN);
        model.setEmpresaFornecedora(new MockEmpresa().mockEntity(number + 1));
        return model;
    }

    public CompraVO mockVO(Long number) {
        var vo = new CompraVO();
        vo.setId(number);
        vo.setSazonalidade(true);
        //vo.setEmpresa(new MockEmpresa().mockEntity(number));
        vo.setLocalRetirada(new MockLocal().mockVO(number));
        vo.setLocalRetirada(new MockLocal().mockVO(number+1));
        //vo.setEntregaConcluida(false);
        vo.setQuantidade(1L);
        vo.setValor(BigDecimal.TEN);
        //vo.setEmpresaFornecedora(new MockEmpresa().mockEntity(number + 1));
        return vo;
    }


}
