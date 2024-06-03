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
public class TaskTest {

    public TaskTest() {
    }

    /**
     * Test of getTaskName method, of class Task.
     */
    @Test
    public void getTaskNameShouldEqualName() {
        System.out.println("testing getTaskName() from Task");
        Task instance = new Task();
        instance.setTaskName("Name");
        String expResult = "Name";
        String result = instance.getTaskName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTaskName method, of class Task.
     */
    @Test
    public void testSetTaskNameShouldEqualName() {
        System.out.println("testing setTaskName() from Task");
        String taskName = "Name";
        String expResult = "Name";
        Task instance = new Task();
        instance.setTaskName(taskName);
        assertEquals(expResult, instance.getTaskName());
    }

    /**
     * Test of getDateCreated method, of class Task.
     */
    @Test
    public void testGetDateCreatedShouldntEqualNull() {
        System.out.println("testing getDateCreated() from Task");
        Task instance = new Task();
        String expResult = "";
        String result = instance.getDateCreated();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of setDateCreated method, of class Task.
     */
    @Test
    public void testSetDateCreatedShouldEqualDateCreated() {
        System.out.println("testing setDateCreated() from Task");
        String dateCreated = "01/01/2000"; //should equal this
        Task instance = new Task();
        instance.setDateCreated(dateCreated);
        assertEquals(instance.getDateCreated(), dateCreated);
    }

    /**
     * Test of setCompleted method, of class Task.
     */
    @Test
    public void testSetCompletedToFalse() {
        System.out.println("testing setCompleted() from Task to false");
        boolean completed = false;
        Task instance = new Task();
        instance.setCompleted(completed);
        assertEquals(completed, instance.isCompleted());
    }

    @Test
    public void testSetCompletedToTrue() {
        System.out.println("testing setCompleted() from Task to true");
        boolean completed = true;
        Task instance = new Task();
        instance.setCompleted(completed);
        assertEquals(completed, instance.isCompleted());
    }

    /**
     * Test of getType method, of class Task.
     */
    @Test
    public void testGetTypeShouldEqualNull() {
        System.out.println("testing getType() from Task");
        Task instance = new Task();
        String expResult = "null";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of isCompleted method, of class Task.
     */
    @Test
    public void testIsCompleted() {
        System.out.println("testing isCompleted from Task");
        Task instance = new Task();
        boolean expResult = false;
        boolean result = instance.isCompleted();
        assertEquals(expResult, result);
    }

}
