package br.com.drwars.v1.converter;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Compra;
import br.com.drwars.v1.mock.MockCompra;
import br.com.drwars.v1.vo.compra.CompraVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompraConverterTest {

    MockCompra inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockCompra();
    }

    @Test
    public void parseEntityToVOTest() {
        CompraVO output = DozerConverter.parseObject(inputObject.mockEntity(), CompraVO.class);
        assertEquals(Long.valueOf(0L), output.getId());

    }

     @Test
    public void parseEntityListToVOListTest() {
        List<CompraVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), CompraVO.class);

        CompraVO outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertTrue(outputZero.getSazonalidade());

        CompraVO outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertTrue(outputTwelve.getSazonalidade());
    }

   @Test
    public void parseVOToEntityTest() {
       Compra output = DozerConverter.parseObject(inputObject.mockVO(), Compra.class);
       assertEquals(Long.valueOf(0L), output.getId());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Compra> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Compra.class);

        Compra outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertTrue(outputZero.getSazonalidade());

        Compra outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertTrue(outputTwelve.getSazonalidade());
    }
}
