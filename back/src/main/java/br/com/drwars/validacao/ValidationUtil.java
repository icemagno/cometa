package br.com.drwars.validacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class ValidationUtil {

    public static boolean validarStringToLong(String numero){
        try {
            Long.valueOf(numero);
        }catch (NumberFormatException e){
            return  false;
        }
        return true;
    }

    public static boolean validarStringToDate(String strDate){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate d = LocalDate.parse(strDate, formatter);

            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean validarDateDataCorrenteParaFrente(String strDate){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate d = LocalDate.parse(strDate, formatter);

            LocalDate dia = LocalDate.now();

            if(d.isEqual(dia))
                return true;

            if(d.isBefore(dia)){
                return false;
            }

            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean validarDateDataCorrenteParaFrente(String strDataInicio, String strDataFim){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataInicio = LocalDate.parse(strDataInicio, formatter);
            LocalDate dataFim = LocalDate.parse(strDataFim, formatter);;

            if(dataInicio.isAfter(dataFim)){
                return false;
            }

            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    public static boolean validarStringToBigDecimal(String numero){
        try {

            new BigDecimal(numero);
        }catch (Exception e){
            return  false;
        }
        return true;
    }
}
