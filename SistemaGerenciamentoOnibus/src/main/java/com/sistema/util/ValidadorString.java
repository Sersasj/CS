
package com.sistema.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author vini
 */
public class ValidadorString {
    public static final String REGEX_EMAIL_VALIDO = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    public static final Pattern PATTERN_EMAIL_VALIDO = 
    Pattern.compile(REGEX_EMAIL_VALIDO, Pattern.CASE_INSENSITIVE);
    
    public static final String REGEX_NOME_VALIDO = "^[A-Z -']+$";
    public static final Pattern PATTERN_NOME_VALIDO = 
    Pattern.compile(REGEX_NOME_VALIDO, Pattern.CASE_INSENSITIVE);

    public static final String REGEX_CPF_VALIDO = "^([0-9]{3})[\\.]?([0-9]{3})[\\.]?([0-9]{3})[-]?([0-9]{2})$";
    public static final Pattern PATTERN_CPF_VALIDO = 
    Pattern.compile(REGEX_CPF_VALIDO, Pattern.CASE_INSENSITIVE);
    
    public static final String REGEX_RG_VALIDO = "^([0-9]{2})[\\.]?([0-9]{3})[\\.]?([0-9]{3})[-]?([0-9])$";
    public static final Pattern PATTERN_RG_VALIDO = 
    Pattern.compile(REGEX_RG_VALIDO, Pattern.CASE_INSENSITIVE);
    
    public static final String REGEX_TELEFONE_VALIDO = "^[(]?([0-9]{2})[)]?[ ]?([0-9]{4,5})[-]?([0-9]{4})$";
    public static final Pattern PATTERN_TELEFONE_VALIDO = 
    Pattern.compile(REGEX_TELEFONE_VALIDO, Pattern.CASE_INSENSITIVE);
   
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
}
