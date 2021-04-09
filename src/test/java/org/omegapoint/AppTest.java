package org.omegapoint; 

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.omegapoint.validitycheck.*;
import org.omegapoint.validitychecker.*;

class AppTest {
    
    ValidityChecker checker1;
    ValidityChecker checker2;
    ValidityChecker checker3;
    ValidityChecker checker4;

    IsSocialSecurityNumber ssn1;
    NotNull null1;
 
    @BeforeEach
    public void setUp(){
        

        checker1 = new ValidityChecker(new IsSocialSecurityNumber());
        checker2 = new ValidityChecker(new NotNull());
        checker3 = new ValidityChecker(new IsSocialSecurityNumber(), new NotNull());
                                       
        ssn1 = new IsSocialSecurityNumber();
        null1 = new NotNull();
    }
    

    /* Simple Unit tests for the public methods */

    @Test
    void testNullSSN() {

       assertEquals(false, ssn1.check(null));
    }

    @Test
    void testValidSsn() {
        ssn1.check("19940410-5059");
        assertEquals(true, ssn1.check("19940410-5059"));
    }

    @Test
    void testInvalidSsnNoDash(){
        assertEquals(false, ssn1.check("199404105058"));
    }
    @Test
    void testInvalidSsnTooLongAgo(){
        assertEquals(false, ssn1.check("18280410-5058"));
    }
    @Test
    void testInvalidSsnFutureYear(){
        assertEquals(false, ssn1.check("20250410-5058"));
    }
 
    @Test
    void testInvalidSsnControlNumber(){
        assertEquals(false, ssn1.check("19940410-5058"));
    }

    @Test
    void testValidNotNull(){
        assertEquals(true, null1.check("19940410-5059"));
    }
    @Test
    void testInvalidNotNull(){
        assertEquals(false, null1.check(null));
    }

    @Test
    void testNotNullGetName(){
        assertEquals("NotNull", null1.getName());
    }
    @Test
    void testIsPersonnummerGetName(){
        assertEquals("IsSocialSecurityNumber", ssn1.getName());
    }
    



    /* Integration testing using ValidityChecker  */
    @Test
    void testValdityCheckerNullList(){
        ArrayList<String> null_list = null;
        assertEquals(false, checker2.validate(null_list));
    }
    @Test
    void testValdityCheckerInvalidSsn(){
        assertEquals(false, checker1.validate(new ArrayList<String>(Arrays.asList("19940410-5059","19940410-5058"))));
    }
    @Test
    void testValdityCheckerValidSsn(){
        assertEquals(true, checker1.validate(new ArrayList<String>(Arrays.asList("19940410-5059"))));
    }

    @Test
    void testValdityCheckerValidNotNull(){
        assertEquals(true, checker2.validate(new ArrayList<String>(Arrays.asList("foo"))));
    }
    @Test
    void testValdityCheckerInvalidNotNull(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(null);
        list.add(null);
        assertEquals(false, checker2.validate(list));
    }
  
    @Test
    void testValidityCheckerWithNotNullAndIsSsn(){
        assertEquals(true, checker3.validate(new ArrayList<String>(Arrays.asList("19940410-5059","19961021-0164"))));
    } 
 
}
