package org.omegapoint.validitycheck;

import java.util.regex.*;
import java.time.*;
import java.time.format.*;
/**
 * This class represents a valdity check for a swedish social security number (SSN).
 * 
 * @param <T> This describes my type parameter
 */
public class IsSocialSecurityNumber implements ValidityCheck {

    private static final int MIN_YEAR = 1900;
    private static final int MAX_YEAR = 2021;

    /**
     * @see check in ValidityCheck interface
     */
    @Override
    public <T> boolean check(T data){

        if(data == null) {
            return false;
        }
        if(data instanceof String){
            if(!parseSocialSecurityNumber((String) data)){
                return false;
            }
            if(!checkControlNumber((String) data)){
                return false;
            }
        }
        return true;
    }

    /**
     * @see getName in ValidityCheck interface
     */
    @Override
    public String getName(){   
        return "IsSocialSecurityNumber";
    }


     /**
     * Returns a boolean indicating if the swedish SSN has a correct format.
     * More specifically, this method checks if dash, length and if the letters preceeding
     * the dash follows the basic ISO format yyyyMMdd.
     * 
     * @param ssn A string representation of the social security number.
     * @return true if the SSN is valid. False if it is not.
     */
    private boolean parseSocialSecurityNumber(String ssn){

        if(ssn.length() != 13 || ssn.charAt(8) != '-') {
            return false;
        }
        
        String ssnYear = ssn.substring(0,4);
        String ssnDate = ssn.substring(0,8);

        if(Integer.parseInt(ssnYear) < MIN_YEAR || Integer.parseInt(ssnYear) > MAX_YEAR) {
            return false;
        }
        try {
            DateTimeFormatter.BASIC_ISO_DATE.parse(ssnDate);
        } 
        catch (DateTimeParseException e) {
                return false;
        }
        return true;
    }

     /**
     * Returns a boolean indicating if the swedish SSN has a valid control number or not.
     * @param ssn A string representation of the social security number.
     * @return the name of the checker as a string
     */
    private boolean checkControlNumber(String ssn) {
        String formattedSsn = ssn.replace("-", "");
        int sum = 0;

        for(int i = 2; i < formattedSsn.length()-1; i++) {
            int num = Character.getNumericValue(formattedSsn.charAt(i));
            if( i % 2 == 0) {
                num = num * 2;
                num = num / 10 + num % 10;
            }
            sum += num;
        }
        sum = 10 - (sum % 10);
        return sum == Character.getNumericValue(ssn.charAt(ssn.length()-1));
    }
}
