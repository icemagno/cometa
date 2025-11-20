package br.com.drwars.v1.converter;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Local;
import br.com.drwars.entites.Representante;
import br.com.drwars.v1.mock.MockLocal;
import br.com.drwars.v1.mock.MockRepresetante;
import br.com.drwars.v1.vo.LocalVO;
import br.com.drwars.v1.vo.RepresentanteVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LocalConverterTest {

    MockLocal inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockLocal();
    }

    @Test
    public void parseEntityToVOTest() {
        LocalVO output = DozerConverter.parseObject(inputObject.mockEntity(), LocalVO.class);
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("Descricao0", output.getDescricao());
        assertNotNull(output.getEndereco());
        assertEquals(Long.valueOf(0L), output.getEndereco().getId());


    }

     @Test
    public void parseEntityListToVOListTest() {
        List<LocalVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), LocalVO.class);

        LocalVO output = outputList.get(0);
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("Descricao0", output.getDescricao());
        assertNotNull(output.getEndereco());
        assertEquals(Long.valueOf(0L), output.getEndereco().getId());

    }

   @Test
    public void parseVOToEntityTest() {
       Local output = DozerConverter.parseObject(inputObject.mockVO(), Local.class);
       assertEquals(Long.valueOf(0L), output.getId());
       assertEquals("Descricao0", output.getDescricao());
       assertNotNull(output.getEndereco());
       assertEquals(Long.valueOf(0L), output.getEndereco().getId());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Local> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Local.class);

        Local outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Descricao0", outputZero.getDescricao());
        assertNotNull(outputZero.getEndereco());
        assertEquals(Long.valueOf(0L), outputZero.getEndereco().getId());

        Local outputSeven = outputList.get(7);
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Descricao7", outputSeven.getDescricao());
        assertNotNull(outputSeven.getEndereco());
        assertEquals(Long.valueOf(7L), outputSeven.getEndereco().getId());


    }
}
