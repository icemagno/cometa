package br.com.drwars.v1.converter;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.OfertaTipo;
import br.com.drwars.v1.mock.MockOfertaTipos;
import br.com.drwars.v1.vo.oferta.OfertaTipoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OfertaTiposConverterTest {

    MockOfertaTipos inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockOfertaTipos();
    }

    @Test
    public void parseEntityToVOTest() {
        OfertaTipoVO output = DozerConverter.parseObject(inputObject.mockEntity(), OfertaTipoVO.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Descricao0", output.getDescricao());
    }

     @Test
    public void parseEntityListToVOListTest() {
        List<OfertaTipoVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), OfertaTipoVO.class);

        OfertaTipoVO outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Descricao0", outputZero.getDescricao());

        OfertaTipoVO outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Descricao12", outputTwelve.getDescricao());
    }

   @Test
    public void parseVOToEntityTest() {
       OfertaTipo output = DozerConverter.parseObject(inputObject.mockVO(), OfertaTipo.class);
       assertEquals(Long.valueOf(0L), output.getId());
       assertEquals("Descricao0", output.getDescricao());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<OfertaTipo> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), OfertaTipo.class);

        OfertaTipo outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Descricao0", outputZero.getDescricao());

        OfertaTipo outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Descricao12", outputTwelve.getDescricao());
    }
}
