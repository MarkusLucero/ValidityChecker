# ValidityChecker

ValidityChecker is a validation tool that assigns ValidityChecks during creation that are used an ArrayList of data of your choice.  There are currently two kinds of ValidityChecks implmented: NotNull validates that an object is not null and IsSocialSecurityNumber validates swedish social security numbers of the format YYYYMMDD-XXXX.


# Testing
Stand in the validator folder and type `mvn clean test`.

# Usage
1. Create a `ValidityChecker`, adding any `ValidityChecks` to be used as parameters.
2. Create an `ArrayList` of data you want valited using the validators.
3. Run `ValidityChecker.validate()`. 

# Example
```
 ValidityChecker checker = new ValidityChecker(new IsSocialSecurityNumber(), new NotNull());
 ArrayList<String> data = new Arraylist(Arrays.asList("19940410-5059","19961021-0164","19940410-5058"));
 checker.validate(data);
 
```
This results in the following output, logged to the console:
```
Total Passes: 5
DATA: 19940410-5059 STATUS: PASSED by IsSocialSecurityNumber checker
DATA: 19961021-0164 STATUS: PASSED by IsSocialSecurityNumber checker
DATA: 19940410-5059 STATUS: PASSED by NotNull checker
DATA: 19961021-0164 STATUS: PASSED by NotNull checker
DATA: 19940410-5058 STATUS: PASSED by NotNull checker


Total Fails: 1
DATA: 19940410-5058 STATUS: FAILED by IsSocialSecurityNumber checker
```
 
