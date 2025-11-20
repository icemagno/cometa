package br.com.drwars.v1.converter;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Perfil;
import br.com.drwars.v1.mock.MockPerfil;
import br.com.drwars.v1.vo.PerfilVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PerfilConverterTest {

    MockPerfil inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockPerfil();
    }

    @Test
    public void parseEntityToVOTest() {
        PerfilVO output = DozerConverter.parseObject(inputObject.mockEntity(), PerfilVO.class);
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("Perfil0", output.getNome());
        assertEquals("Descricao0", output.getDescricao());
        assertTrue(output.getSituacao());
    }

     @Test
    public void parseEntityListToVOListTest() {
        List<PerfilVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), PerfilVO.class);

        PerfilVO outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("Perfil0", outputZero.getNome());
        assertEquals("Descricao0", outputZero.getDescricao());
        assertTrue(outputZero.getSituacao());

        PerfilVO outputSeven = outputList.get(7);
        assertEquals(Long.valueOf(7L), outputSeven.getKey());
        assertEquals("Perfil7", outputSeven.getNome());
        assertEquals("Descricao7", outputSeven.getDescricao());
        assertTrue(outputSeven.getSituacao());

        PerfilVO outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        assertEquals("Perfil12", outputTwelve.getNome());
        assertEquals("Descricao12", outputTwelve.getDescricao());
        assertTrue(outputTwelve.getSituacao());
    }

   @Test
    public void parseVOToEntityTest() {
       Perfil output = DozerConverter.parseObject(inputObject.mockVO(), Perfil.class);
       assertEquals(Long.valueOf(0L), output.getId());
       assertEquals("Perfil0", output.getNome());
       assertEquals("Descricao0", output.getDescricao());
       assertTrue(output.getSituacao());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Perfil> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Perfil.class);

        Perfil outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Perfil0", outputZero.getNome());
        assertEquals("Descricao0", outputZero.getDescricao());
        assertTrue(outputZero.getSituacao());

        Perfil outputSeven = outputList.get(7);
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Perfil7", outputSeven.getNome());
        assertEquals("Descricao7", outputSeven.getDescricao());
        assertTrue(outputSeven.getSituacao());

        Perfil outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Perfil12", outputTwelve.getNome());
        assertEquals("Descricao12", outputTwelve.getDescricao());
        assertTrue(outputTwelve.getSituacao());
    }
}
