package org.omegapoint.validitycheck;

/**
* The ValidityCheck interface provides two methods that enable checking (validation) of some data or object and
* fetching a string (name) representation of a validity checker
* @param <T>
*/

public interface ValidityCheck  {
    /**
     * Returns a boolean indicating if the given input data is valid or not.
     * 
     * @param obj the input data of generic type to be checked for validity.
     * @return true if the data is considered valid, false if it is considered invalid.
     */
    <T> boolean check(T obj);

    /**
     * Returns a string representation of a ValidityCheck. 
     * 
     * @return the name of the checker as a string
     */
    public String getName();
}
