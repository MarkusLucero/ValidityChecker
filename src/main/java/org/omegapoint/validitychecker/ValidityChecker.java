package org.omegapoint.validitychecker;

import java.util.ArrayList;
import org.omegapoint.validitycheck.*;

/**
 * This class groups and executes ValidityChecks, passing a list of data through all grouped validity checks, checking their validity.
 * <p>
 * This class also logs both failed and passed tests by printing them to the console.
 * 
 * @param <T> This describes my type parameter
 */
public class ValidityChecker {
    private ArrayList<ValidityCheck> validators;
    private ArrayList<String> errors = new ArrayList<String>();
    private ArrayList<String> success = new ArrayList<String>();


   /** 
    * Class constructor.
    *
    * @param checkers an arbitrary amount of checkers to be added to a list of validators to be executed.
    */
    @SafeVarargs
    public <T> ValidityChecker(ValidityCheck ... checkers){
        validators = new ArrayList<ValidityCheck>();
        for( ValidityCheck checker : checkers){
            validators.add(checker);
        }
    }

    /** 
    * This method validates all data by executing all specified validators on it. 
    * <p>
    * All successful and failed checks are added two different lists and printed to the console.
    *
    * @param list the list of data to be checked.
    * @return true of all checks were successful, false if a check failed.
    */
    public <T> boolean validate(ArrayList<T> list) {
        if(list == null){ 
            return false;
        }
  
        for(ValidityCheck check : validators) {
            for(T data : list){

                boolean v = check.check(data);
                if(!v) {
                    errors.add("DATA: " + (data == null ? "null" : data.toString())  + " STATUS: FAILED by " + check.getName() + " checker");
                }
                else {
                    success.add("DATA: " + (data == null ? "null" : data.toString()) + " STATUS: PASSED by " + check.getName() + " checker");
                }
            }
        }

        logSuccess();
        logErrors();
        if(errors.size() > 0 ){
            clearLog();
            return false;
        }
        clearLog();
        return true;
    }

    /** 
     * This method logs all errors found during validation
     */
    private void logErrors(){
        System.out.println("Total Fails: " + Integer.toString(errors.size()));
        if(errors.size() > 0) {
            for(String error : errors) {
                System.out.println(error);
            }
        }
        else { 
            System.out.println("No checkers failed.");
        }
        System.out.println("------------------------------------------------------------------------\n");
    }

    /** 
     * This method logs all successes found during validation
     */
    private void logSuccess(){
        System.out.println("Total Passes: " + Integer.toString(success.size()));
        if(success.size() > 0){
            for(String name : success) {
                System.out.println(name);
            }
        }
        else {
            System.out.println("No checkers passed");
        } 
        System.out.println("\n");
    }

     /** 
     * This method clears the error and success lists from any logged data allowing reusage.
     */
    private void clearLog(){
        this.errors.clear();
        this.success.clear();
    }


}
