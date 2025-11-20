package br.com.drwars.v1.converter;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Empresa;
import br.com.drwars.entites.EmpresaSituacao;
import br.com.drwars.v1.mock.MockEmpresa;
import br.com.drwars.v1.mock.MockEmpresaSituacao;
import br.com.drwars.v1.vo.EmpresaSituacaoVO;
import br.com.drwars.v1.vo.EmpresaVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmpresaConverterTest {

    MockEmpresa inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockEmpresa();
    }

    @Test
    public void parseEntityToVOTest() {
        EmpresaVO output = DozerConverter.parseObject(inputObject.mockEntity(), EmpresaVO.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Cnpj0", output.getCnpj());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<EmpresaVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), EmpresaVO.class);

        EmpresaVO outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Cnpj0", outputZero.getCnpj());

        EmpresaVO outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Cnpj12", outputTwelve.getCnpj());
    }

   @Test
    public void parseVOToEntityTest() {
       Empresa output = DozerConverter.parseObject(inputObject.mockVO(), Empresa.class);
       assertEquals(Long.valueOf(0L), output.getId());
       assertEquals("Cnpj0", output.getCnpj());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Empresa> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Empresa.class);

        Empresa outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Cnpj0", outputZero.getCnpj());

        Empresa outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Cnpj12", outputTwelve.getCnpj());
    }
}
