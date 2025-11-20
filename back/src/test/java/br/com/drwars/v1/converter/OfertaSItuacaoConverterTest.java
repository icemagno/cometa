package br.com.drwars.v1.converter;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.OfertaSituacao;
import br.com.drwars.v1.mock.MockOfertaSituacao;
import br.com.drwars.v1.vo.oferta.OfertaSituacaoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OfertaSItuacaoConverterTest {

    MockOfertaSituacao inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockOfertaSituacao();
    }

    @Test
    public void parseEntityToVOTest() {
        OfertaSituacaoVO output = DozerConverter.parseObject(inputObject.mockEntity(), OfertaSituacaoVO.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Descricao0", output.getDescricao());
    }

     @Test
    public void parseEntityListToVOListTest() {
        List<OfertaSituacaoVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), OfertaSituacaoVO.class);

        OfertaSituacaoVO outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Descricao0", outputZero.getDescricao());

        OfertaSituacaoVO outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Descricao12", outputTwelve.getDescricao());
    }

   @Test
    public void parseVOToEntityTest() {
       OfertaSituacao output = DozerConverter.parseObject(inputObject.mockVO(), OfertaSituacao.class);
       assertEquals(Long.valueOf(0L), output.getId());
       assertEquals("Descricao0", output.getDescricao());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<OfertaSituacao> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), OfertaSituacao.class);

        OfertaSituacao outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Descricao0", outputZero.getDescricao());

        OfertaSituacao outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Descricao12", outputTwelve.getDescricao());
    }
}
