package br.com.drwars.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Util {



    public static LocalDate converterStringToLocalDate(String dataString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTime = LocalDate.parse(dataString, formatter);
        return  dateTime;
    }

    public static BigDecimal convertToBigDecimal(String strNumber) {
        if (strNumber == null) return BigDecimal.ZERO;
        // BR 10,25 US 10.25
        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number)) return new BigDecimal(number);
        return BigDecimal.ZERO;
    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
