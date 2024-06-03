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
public class WorkTaskTest {

    public WorkTaskTest() {
    }

    /**
     * Test of getType method, of class WorkTask.
     */
    @Test
    public void testWorkTaskGetTypeEqualWork() {
        System.out.println("testing getType() from WorkTask");
        Task instance = new WorkTask();
        String expResult = "Work";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

}
