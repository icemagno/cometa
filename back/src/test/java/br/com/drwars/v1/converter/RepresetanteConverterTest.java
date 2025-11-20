package br.com.drwars.v1.converter;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Perfil;
import br.com.drwars.entites.Representante;
import br.com.drwars.v1.mock.MockPerfil;
import br.com.drwars.v1.mock.MockRepresetante;
import br.com.drwars.v1.vo.PerfilVO;
import br.com.drwars.v1.vo.RepresentanteVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RepresetanteConverterTest {

    MockRepresetante inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockRepresetante();
    }

    @Test
    public void parseEntityToVOTest() {
        RepresentanteVO output = DozerConverter.parseObject(inputObject.mockEntity(), RepresentanteVO.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Nome0", output.getNome());
        assertEquals("Cpf0", output.getCpf());
        assertNotNull(output.getEndereco());
        assertEquals(Long.valueOf(0L), output.getEndereco().getId());


    }

     @Test
    public void parseEntityListToVOListTest() {
        List<RepresentanteVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), RepresentanteVO.class);

        RepresentanteVO output = outputList.get(0);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Nome0", output.getNome());
        assertEquals("Cpf0", output.getCpf());
        assertNotNull(output.getEndereco());
        assertEquals(Long.valueOf(0L), output.getEndereco().getId());

    }

   @Test
    public void parseVOToEntityTest() {
       Representante output = DozerConverter.parseObject(inputObject.mockVO(), Representante.class);
       assertEquals(Long.valueOf(0L), output.getId());
       assertEquals("Nome0", output.getNome());
       assertEquals("Cpf0", output.getCpf());
       assertNotNull(output.getEndereco());
       assertEquals(Long.valueOf(0L), output.getEndereco().getId());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Representante> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Representante.class);

        Representante outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Nome0", outputZero.getNome());
        assertEquals("Cpf0", outputZero.getCpf());
        assertNotNull(outputZero.getEndereco());
        assertEquals(Long.valueOf(0L), outputZero.getEndereco().getId());

        Representante outputSeven = outputList.get(7);
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Nome7", outputSeven.getNome());
        assertEquals("Cpf7", outputSeven.getCpf());
        assertNotNull(outputSeven.getEndereco());
        assertEquals(Long.valueOf(7L), outputSeven.getEndereco().getId());


    }
}
