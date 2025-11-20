package br.com.drwars.v1.converter;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Endereco;
import br.com.drwars.v1.mock.MockEndereco;
import br.com.drwars.v1.vo.EnderecoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnderecoConverterTest {

    MockEndereco inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockEndereco();
    }

    @Test
    public void parseEntityToVOTest() {
        EnderecoVO output = DozerConverter.parseObject(inputObject.mockEntity(), EnderecoVO.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Bairro0", output.getBairro());
        assertEquals("Cep0", output.getCep());
        assertEquals("Cidade0", output.getCidade());

        assertEquals("Complemento0", output.getComplemento());
        assertEquals("Email0", output.getEmail());
        assertEquals("Estado0", output.getEstado());

        assertEquals("Logradouro0", output.getLogradouro());
        assertEquals("Numero0", output.getNumero());
        assertEquals("Telefone0", output.getTelefone());
        assertEquals("Pais0", output.getPais());

    }

     @Test
    public void parseEntityListToVOListTest() {
        List<EnderecoVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), EnderecoVO.class);

        EnderecoVO outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Bairro0", outputZero.getBairro());
        assertEquals("Cep0", outputZero.getCep());
        assertEquals("Cidade0", outputZero.getCidade());

        assertEquals("Complemento0", outputZero.getComplemento());
        assertEquals("Email0", outputZero.getEmail());
        assertEquals("Estado0", outputZero.getEstado());

        assertEquals("Logradouro0", outputZero.getLogradouro());
        assertEquals("Numero0", outputZero.getNumero());
        assertEquals("Telefone0", outputZero.getTelefone());
        assertEquals("Pais0", outputZero.getPais());
    }

   @Test
    public void parseVOToEntityTest() {
       Endereco output = DozerConverter.parseObject(inputObject.mockVO(), Endereco.class);
       assertEquals(Long.valueOf(0L), output.getId());
       assertEquals("Bairro0", output.getBairro());
       assertEquals("Cep0", output.getCep());
       assertEquals("Cidade0", output.getCidade());

       assertEquals("Complemento0", output.getComplemento());
       assertEquals("Email0", output.getEmail());
       assertEquals("Estado0", output.getEstado());

       assertEquals("Logradouro0", output.getLogradouro());
       assertEquals("Numero0", output.getNumero());
       assertEquals("Telefone0", output.getTelefone());
       assertEquals("Pais0", output.getPais());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Endereco> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Endereco.class);

        Endereco output = outputList.get(0);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Bairro0", output.getBairro());
        assertEquals("Cep0", output.getCep());
        assertEquals("Cidade0", output.getCidade());

        assertEquals("Complemento0", output.getComplemento());
        assertEquals("Email0", output.getEmail());
        assertEquals("Estado0", output.getEstado());

        assertEquals("Logradouro0", output.getLogradouro());
        assertEquals("Numero0", output.getNumero());
        assertEquals("Telefone0", output.getTelefone());
        assertEquals("Pais0", output.getPais());
    }
}
