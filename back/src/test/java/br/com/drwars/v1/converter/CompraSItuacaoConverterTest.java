package br.com.drwars.v1.converter;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.CompraSituacao;
import br.com.drwars.v1.mock.MockCompraSituacao;
import br.com.drwars.v1.vo.compra.CompraSituacaoVO;
import br.com.drwars.v1.vo.oferta.OfertaSituacaoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompraSItuacaoConverterTest {

    MockCompraSituacao inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockCompraSituacao();
    }

    @Test
    public void parseEntityToVOTest() {
        CompraSituacaoVO output = DozerConverter.parseObject(inputObject.mockEntity(), CompraSituacaoVO.class);
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
       CompraSituacao output = DozerConverter.parseObject(inputObject.mockVO(), CompraSituacao.class);
       assertEquals(Long.valueOf(0L), output.getId());
       assertEquals("Descricao0", output.getDescricao());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<CompraSituacao> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), CompraSituacao.class);

        CompraSituacao outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Descricao0", outputZero.getDescricao());

        CompraSituacao outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Descricao12", outputTwelve.getDescricao());
    }
}
