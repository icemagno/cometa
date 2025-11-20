package br.com.drwars.validacao;

import br.com.drwars.exception.CampoInvalidoException;
import br.com.drwars.exception.InvalidParameterException;
import br.com.drwars.v1.service.LocalService;
import br.com.drwars.v1.vo.LocalVO;
import br.com.drwars.v1.vo.NegociacaoPropostaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidacaoOfertaCompraNormal {

    @Autowired
    private  LocalService  localService;


    public  boolean  validarItem(Long id, String value) throws CampoInvalidoException {
        switch (id.intValue()){
            case 3:
                if(!value.equals("N") && !value.equals("S")){
                    throw  new CampoInvalidoException("O campo sazanolidade deve ser preenchido com os valores N | S ");
                }
                break;
            case 5: {
                if(!ValidationUtil.validarStringToLong(value))
                    throw  new CampoInvalidoException("O campo Local entrega  deve ser numérico. ");

                LocalVO local = localService.findById(Long.valueOf(value));
                if(local==null){
                    throw  new CampoInvalidoException("O campo Local  inválido. ");
                }
                break;
            }
            case 8:
                if(!value.equals("N") && !value.equals("S")){
                    throw  new CampoInvalidoException("O campo flexibilidade deve ser preenchido com os valores N | S ");
                }
                break;
            case 13:
                if(!ValidationUtil.validarStringToBigDecimal(value))
                    throw  new CampoInvalidoException("O campo  valor do produto deve seu numérico. ");
                break;
            case 14:
                if(!value.equals("CIF") && !value.equals("FOB")){
                    throw  new CampoInvalidoException("O campo Tipo de Frete deve ser preenchido com os valores CIF | FOB ");
                }
                break;
            case 15:
                break;
            case 17:
                if(!ValidationUtil.validarStringToBigDecimal(value))
                    throw  new CampoInvalidoException("O campo Valor do Certificado  deve ser monetário. ");
                break;
            case 18:
                if(!ValidationUtil.validarStringToDate(value))
                    throw  new CampoInvalidoException("O campo  deve ser preenchido com data ");

             /*   if(!ValidationUtil.validarDateDataCorrenteParaFrente(value))
                    throw  new CampoInvalidoException("Data selecionada menor que a data corrente ");
*/
                break;
            case 19:
                if(!ValidationUtil.validarStringToBigDecimal(value))
                    throw  new CampoInvalidoException("O campo Valor do Frete  deve ser monetário. ");
                break;

            case 21:
                if(!ValidationUtil.validarStringToDate(value))
                    throw  new CampoInvalidoException("O campo  deve ser preenchido com data ");
              /*  if(!ValidationUtil.validarDateDataCorrenteParaFrente(value))
                    throw  new CampoInvalidoException("Data selecionada menor que a data corrente ");*/
                break;
            case 22:
                if(!ValidationUtil.validarStringToLong(value))
                    throw  new CampoInvalidoException("O campo quantidade certificado deve ser numérico. ");
                break;
            default: throw  new CampoInvalidoException("O informado de com valor errado. ");

        }

        return true;
    }

    public static boolean verficaQuantidadeCertificado(List<NegociacaoPropostaVO> listaNegociacaoProposta) {
        if(listaNegociacaoProposta==null || listaNegociacaoProposta.isEmpty())
            return  false;
        NegociacaoPropostaVO item  = null;
        for (NegociacaoPropostaVO negociacaoPropostaVO: listaNegociacaoProposta) {
            if(negociacaoPropostaVO.getIdItem().equals("22")){
                item = negociacaoPropostaVO;
            }
        }
        if(item==null || !ValidationUtil.validarStringToLong(item.getIdItem()))
            return false;

        if(Long.valueOf(item.getValor())>0){
            return true;
        }

        return false;
    }


    public static void validaQuantidadeItens(List<NegociacaoPropostaVO> negociacaoPropostaVOS, Long tipoOferta) {

        /**
         *  Total 12
         *
         *  1 - Biometano
         *      . 10
         *  2 - Misto
         *      . 12
         *  3 - Certificado
         *      . 2
         */

        if( (tipoOferta.equals(1L) && negociacaoPropostaVOS.size()!= 9)
            || (tipoOferta.equals(2L) && negociacaoPropostaVOS.size() != 11)
            || (tipoOferta.equals(3L) && negociacaoPropostaVOS.size() != 2)
        ){
            throw  new InvalidParameterException("A quantidade de  campos não está condizente com o tipo de oferta");
        }
    }
}
