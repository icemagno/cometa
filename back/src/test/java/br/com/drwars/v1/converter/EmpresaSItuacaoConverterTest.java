package br.com.drwars.v1.converter;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.EmpresaSituacao;
import br.com.drwars.entites.NoticiaSituacao;
import br.com.drwars.v1.mock.MockEmpresaSituacao;
import br.com.drwars.v1.mock.MockNoticiaSituacao;
import br.com.drwars.v1.vo.EmpresaSituacaoVO;
import br.com.drwars.v1.vo.NoticiaSituacaoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmpresaSItuacaoConverterTest {

    MockEmpresaSituacao inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockEmpresaSituacao();
    }

    @Test
    public void parseEntityToVOTest() {
        EmpresaSituacaoVO output = DozerConverter.parseObject(inputObject.mockEntity(), EmpresaSituacaoVO.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Descricao0", output.getDescricao());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<EmpresaSituacaoVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), EmpresaSituacaoVO.class);

        EmpresaSituacaoVO outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Descricao0", outputZero.getDescricao());

        EmpresaSituacaoVO outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Descricao12", outputTwelve.getDescricao());
    }

   @Test
    public void parseVOToEntityTest() {
       EmpresaSituacao output = DozerConverter.parseObject(inputObject.mockVO(), EmpresaSituacao.class);
       assertEquals(Long.valueOf(0L), output.getId());
       assertEquals("Descricao0", output.getDescricao());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<EmpresaSituacao> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), EmpresaSituacao.class);

        EmpresaSituacao outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Descricao0", outputZero.getDescricao());

        EmpresaSituacao outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Descricao12", outputTwelve.getDescricao());
    }
}
