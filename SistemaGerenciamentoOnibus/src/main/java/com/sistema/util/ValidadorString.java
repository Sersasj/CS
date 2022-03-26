package com.sistema.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author vini
 */
public class ValidadorString {

    public static final String REGEX_EMAIL_VALIDO = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    public static final Pattern PATTERN_EMAIL_VALIDO
            = Pattern.compile(REGEX_EMAIL_VALIDO, Pattern.CASE_INSENSITIVE);

    public static final String REGEX_NOME_VALIDO = "^[A-Z -']+$";
    public static final Pattern PATTERN_NOME_VALIDO
            = Pattern.compile(REGEX_NOME_VALIDO, Pattern.CASE_INSENSITIVE);

    public static final String REGEX_CPF_VALIDO = "^([0-9]{3})[\\.]?([0-9]{3})[\\.]?([0-9]{3})[-]?([0-9]{2})$";
    public static final Pattern PATTERN_CPF_VALIDO
            = Pattern.compile(REGEX_CPF_VALIDO, Pattern.CASE_INSENSITIVE);

    public static final String REGEX_CPF_INCOMPLETO = "([0-9]{0,3})[\\.]?([0-9]{0,3})[\\.]?([0-9]{0,3})[-]?([0-9]{0,2})";
    public static final Pattern PATTERN_CPF_INCOMPLETO
            = Pattern.compile(REGEX_CPF_INCOMPLETO, Pattern.CASE_INSENSITIVE);

    public static final String REGEX_RG_VALIDO = "^([0-9]{2})[\\.]?([0-9]{3})[\\.]?([0-9]{3})[-]?([0-9])$";
    public static final Pattern PATTERN_RG_VALIDO
            = Pattern.compile(REGEX_RG_VALIDO, Pattern.CASE_INSENSITIVE);

    public static final String REGEX_TELEFONE_VALIDO = "^[(]?([0-9]{2})[)]?[ ]?([0-9]{4,5})[-]?([0-9]{4})$";
    public static final Pattern PATTERN_TELEFONE_VALIDO
            = Pattern.compile(REGEX_TELEFONE_VALIDO, Pattern.CASE_INSENSITIVE);

    public static final String REGEX_PLACA_VALIDA_1 = "^([A-Z]{3})[-]([0-9]{4})$";
    public static final Pattern PATTERN_PLACA_VALIDA_1
            = Pattern.compile(REGEX_PLACA_VALIDA_1, Pattern.CASE_INSENSITIVE);

    public static final String REGEX_PLACA_VALIDA_2 = "^([A-Z]{3})[-][0-9][A-Z]([0-9]{2})$";
    public static final Pattern PATTERN_PLACA_VALIDA_2
            = Pattern.compile(REGEX_PLACA_VALIDA_2, Pattern.CASE_INSENSITIVE);

        public static final String REGEX_PLACA_INCOMPLETA = "^([A-Z0-9]{0,3})[-]?([A-Z0-9]{0,4})$";
    public static final Pattern PATTERN_PLACA_INCOMPLETA
            = Pattern.compile(REGEX_PLACA_INCOMPLETA, Pattern.CASE_INSENSITIVE);
    
    public ValidadorString() {
    }

    public boolean validarEmail(String emailStr) {
        Matcher matcher = PATTERN_EMAIL_VALIDO.matcher(emailStr);
        return matcher.find();
    }

    public boolean validarNome(String nomeStr) {
        Matcher matcher = PATTERN_NOME_VALIDO.matcher(nomeStr);
        return matcher.find();
    }

    public boolean validarCPF(String cpfStr) {
        Matcher matcher = PATTERN_CPF_VALIDO.matcher(cpfStr);
        return matcher.find();
    }

    public String formatarCPF(String cpfStr) {
        return cpfStr.replaceAll(REGEX_CPF_VALIDO, "$1.$2.$3-$4");
    }

    public String formatarCPFIncompleto(String cpfStr) {
        int tamanhoMax = 15;
        if (cpfStr.length() > tamanhoMax) {
            cpfStr = cpfStr.substring(0, tamanhoMax - 1);
        }

        if (cpfStr.length() < 3) {
            return cpfStr;
        } else if (cpfStr.length() >= 3 && cpfStr.length() < 6) {
            return cpfStr.replaceFirst(REGEX_CPF_INCOMPLETO, "$1.$2");
        } else if (cpfStr.length() >= 6 && cpfStr.length() < 9) {
            return cpfStr.replaceFirst(REGEX_CPF_INCOMPLETO, "$1.$2.$3");
        } else {
            return cpfStr.replaceFirst(REGEX_CPF_INCOMPLETO, "$1.$2.$3-$4");
        }
    }

    public boolean validarRG(String rgStr) {
        Matcher matcher = PATTERN_RG_VALIDO.matcher(rgStr);
        return matcher.find();
    }

    public String formatarRG(String rgStr) {
        return rgStr.replaceAll(REGEX_RG_VALIDO, "$1.$2.$3-$4");
    }

    public boolean validarTelefone(String telefoneStr) {
        Matcher matcher = PATTERN_TELEFONE_VALIDO.matcher(telefoneStr);
        return matcher.find();
    }

    public String formatarTelefone(String telefoneStr) {
        return telefoneStr.replaceAll(REGEX_TELEFONE_VALIDO, "$1 $2-$3");
    }
    public String formatarPlaca(String placaStr) {
        return placaStr.replaceAll(REGEX_PLACA_VALIDA_1, "$1$2");
    }    
}
