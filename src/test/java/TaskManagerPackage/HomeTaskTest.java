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
public class HomeTaskTest {

    public HomeTaskTest() {
    }

    /**
     * Test of getType method, of class HomeTask.
     */
    @Test
    public void testHomeTaskGetTypeEqualHome() {
        System.out.println("testing getType() from HomeTask");
        Task instance = new HomeTask();
        String expResult = "Home";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

}
