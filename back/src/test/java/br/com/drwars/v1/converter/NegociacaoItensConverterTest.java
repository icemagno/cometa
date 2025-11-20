package br.com.drwars.v1.converter;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.NegociacaoItens;
import br.com.drwars.entites.Perfil;
import br.com.drwars.v1.mock.MockNegociacaoItens;
import br.com.drwars.v1.mock.MockPerfil;
import br.com.drwars.v1.vo.NegociacaoItensVO;
import br.com.drwars.v1.vo.PerfilVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NegociacaoItensConverterTest {

    MockNegociacaoItens inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockNegociacaoItens();
    }

    /*@Test
    public void parseEntityToVOTest() {
        NegociacaoItensVO output = DozerConverter.parseObject(inputObject.mockEntity(), NegociacaoItensVO.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Componente0", output.getComponente());
        assertEquals("Descricao0", output.getDescricao());
        assertEquals("Tipo0", output.getTipoValor());
    }

     @Test
    public void parseEntityListToVOListTest() {
        List<NegociacaoItensVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), NegociacaoItensVO.class);

        NegociacaoItensVO outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Componente0", outputZero.getComponente());
        assertEquals("Descricao0", outputZero.getDescricao());
        assertEquals("Tipo0", outputZero.getTipoValor());

        NegociacaoItensVO outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Componente12", outputTwelve.getComponente());
        assertEquals("Descricao12", outputTwelve.getDescricao());
        assertEquals("Tipo12", outputTwelve.getTipoValor());
    }
*/
 /*  @Test
    public void parseVOToEntityTest() {
       NegociacaoItens output = DozerConverter.parseObject(inputObject.mockVO(), NegociacaoItens.class);
       assertEquals(Long.valueOf(0L), output.getId());
       assertEquals("Componente0", output.getComponente());
       assertEquals("Descricao0", output.getDescricao());
       assertEquals("Tipo0", output.getTipoValor());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<NegociacaoItens> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), NegociacaoItens.class);

        NegociacaoItens outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Componente0", outputZero.getComponente());
        assertEquals("Descricao0", outputZero.getDescricao());
        assertEquals("Tipo0", outputZero.getTipoValor());

        NegociacaoItens outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Componente12", outputTwelve.getComponente());
        assertEquals("Descricao12", outputTwelve.getDescricao());
        assertEquals("Tipo12", outputTwelve.getTipoValor());
    }*/
}
