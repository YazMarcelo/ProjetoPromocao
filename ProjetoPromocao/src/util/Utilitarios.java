/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;

/**
 *
 * @author HELM
 */
public class Utilitarios {

    public static boolean validarCPF(String strCpf) {
        strCpf = retirarMascara(strCpf);
        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
            digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();

            d1 = d1 + (11 - nCount) * digitoCPF;

            d2 = d2 + (12 - nCount) * digitoCPF;
        };

        resto = (d1 % 11);

        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        d2 += 2 * digito1;

        resto = (d2 % 11);

        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());

        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        return nDigVerific.equals(nDigResult);
    }

    public static String dateBRFormat(String data) throws ParseException {

        if (!(data.equals("null")) && !(data.equals(""))) {
            //Primeiro converte de String para Date
            DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd");
            Date date = formatUS.parse(data);

            //Depois formata data
            DateFormat formatBR = new SimpleDateFormat("dd-mm-yyyy");
            String dateFormated = formatBR.format(date);
            dateFormated = dateFormated.replace(("-"), "/");
            return dateFormated;
        }
        return null;

    }
	
	public static String dateToString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}
	
	public static Date stringToDate(String string) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.parse(string);
	}

    public static boolean isDateValid(String strDate) {
        String dateFormat = "dd/MM/yyyy";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat).withResolverStyle(ResolverStyle.STRICT);

        try {
            LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    public static String retirarMascara(String stg){
        stg = stg.replace(" ", "");
        stg = stg.replace("-", "");
        stg = stg.replace(".", "");
        stg = stg.replace("(", "");
        stg = stg.replace(")", "");
        stg = stg.replace("/", "");
        stg = stg.replace(")", "");
        
        return stg;
    }

    public static void someteNumeros(KeyEvent evt) {
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }
    
    public static boolean validarData(){
                String s = "31/02/2009";
        DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
        try {
            System.out.println(df.parse (s));
        } catch (ParseException ex) {
           System.out.println(ex);
        }
        return true;
    }

}
