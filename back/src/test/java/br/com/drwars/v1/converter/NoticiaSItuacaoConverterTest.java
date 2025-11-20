package br.com.drwars.v1.converter;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.NoticiaSituacao;
import br.com.drwars.v1.mock.MockNoticiaSituacao;
import br.com.drwars.v1.vo.NoticiaSituacaoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoticiaSItuacaoConverterTest {

    MockNoticiaSituacao inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockNoticiaSituacao();
    }

    @Test
    public void parseEntityToVOTest() {
        NoticiaSituacaoVO output = DozerConverter.parseObject(inputObject.mockEntity(), NoticiaSituacaoVO.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Descricao0", output.getDescricao());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<NoticiaSituacaoVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), NoticiaSituacaoVO.class);

        NoticiaSituacaoVO outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Descricao0", outputZero.getDescricao());

        NoticiaSituacaoVO outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Descricao12", outputTwelve.getDescricao());
    }

   @Test
    public void parseVOToEntityTest() {
       NoticiaSituacao output = DozerConverter.parseObject(inputObject.mockVO(), NoticiaSituacao.class);
       assertEquals(Long.valueOf(0L), output.getId());
       assertEquals("Descricao0", output.getDescricao());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<NoticiaSituacao> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), NoticiaSituacao.class);

        NoticiaSituacao outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Descricao0", outputZero.getDescricao());

        NoticiaSituacao outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Descricao12", outputTwelve.getDescricao());
    }
}
