/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TaskManagerPackage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Caleb
 */
public class InputValidatorTest {

    public InputValidatorTest() {
    }

    /**
     * Test of validateTaskName method, of class InputValidator.
     */
    @Test
    public void testValidateTaskNameExceedsMax() {
        TaskCreator tc = new TaskCreator();
        System.out.println("validateTaskName");
        String name = "poipoipoipoipoipoipoipoipoipoipo"; //32
        int max = tc.getMaxNameLength();
        int min = tc.getMinNameLength();
        InputValidator instance = new InputValidator();
        int expResult = 1; //too long
        int result = instance.validateTaskName(name, max, min);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidateTaskNameEmpty() {
        TaskCreator tc = new TaskCreator();
        System.out.println("validateTaskName");
        String name = "";
        int max = tc.getMaxNameLength();
        int min = tc.getMinNameLength();
        InputValidator instance = new InputValidator();
        int expResult = -1; //no value
        int result = instance.validateTaskName(name, max, min);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidateTaskNameIsOkay() {
        TaskCreator tc = new TaskCreator();
        System.out.println("validateTaskName");
        String name = "valid task name";
        int max = tc.getMaxNameLength();
        int min = tc.getMinNameLength();
        InputValidator instance = new InputValidator();
        int expResult = 2; //Valid name
        int result = instance.validateTaskName(name, max, min);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateAlphabetical method, of class InputValidator.
     */
    @Test
    public void testValidateAlphabeticalTrue() {
        System.out.println("validateAlphabetical");
        String input = "abcd";
        InputValidator instance = new InputValidator();
        boolean expResult = true;
        boolean result = instance.validateAlphabetical(input);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidateAlphabeticalFalse() {
        System.out.println("validateAlphabetical");
        String input = "a1b2c3d4";
        InputValidator instance = new InputValidator();
        boolean expResult = false;
        boolean result = instance.validateAlphabetical(input);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateNumber method, of class InputValidator.
     */
    @Test
    public void testValidateNumberTrue() {
        System.out.println("validateNumber");
        String input = "123";
        InputValidator instance = new InputValidator();
        boolean expResult = true;
        boolean result = instance.validateNumber(input);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidateNumberFalse() {
        System.out.println("validateNumber");
        String input = "abc";
        InputValidator instance = new InputValidator();
        boolean expResult = false;
        boolean result = instance.validateNumber(input);
        assertEquals(expResult, result);
    }

}
