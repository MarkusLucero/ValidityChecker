package org.omegapoint.validitycheck;

import java.util.Objects;
/**
 * This class represents a not null check for some generic object.
 * 
 * @param <T> This describes my type parameter
 */
public class NotNull implements ValidityCheck {

    /**
     * @see check in ValidityCheck interface
     */
    @Override
    public <T> boolean check(T obj){
        if(obj == null){
            return false;
        }
        return true;
    }
    
    /**
     * @see getName in ValidityCheck interface
     */
    @Override
    public String getName(){
        return "NotNull";

    }

}
