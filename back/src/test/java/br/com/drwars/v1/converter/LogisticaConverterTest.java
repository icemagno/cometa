package br.com.drwars.v1.converter;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Logistica;
import br.com.drwars.entites.NoticiaSituacao;
import br.com.drwars.entites.enums.TipoTransportelEnum;
import br.com.drwars.v1.mock.MockLogistica;
import br.com.drwars.v1.mock.MockNoticiaSituacao;
import br.com.drwars.v1.vo.LogisticaVO;
import br.com.drwars.v1.vo.NoticiaSituacaoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogisticaConverterTest {

    MockLogistica inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockLogistica();
    }

    @Test
    public void parseEntityToVOTest() {
        var output = DozerConverter.parseObject(inputObject.mockEntity(), LogisticaVO.class);
        assertEquals(Long.valueOf(0L), output.getId());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<LogisticaVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), LogisticaVO.class);

        LogisticaVO outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals(TipoTransportelEnum.GNC, outputZero.getTipoTransporte());

        LogisticaVO outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals(TipoTransportelEnum.GNC, outputZero.getTipoTransporte());
    }

   @Test
    public void parseVOToEntityTest() {
       Logistica output = DozerConverter.parseObject(inputObject.mockVO(), Logistica.class);
       assertEquals(Long.valueOf(0L), output.getId());
       assertEquals(TipoTransportelEnum.GNL, output.getTipoTransporte());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Logistica> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Logistica.class);

        Logistica outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals(TipoTransportelEnum.GNL, outputZero.getTipoTransporte());

        Logistica outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals(TipoTransportelEnum.GNL, outputTwelve.getTipoTransporte());
    }
}
