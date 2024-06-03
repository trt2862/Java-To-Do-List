/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TaskManagerPackage;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Caleb
 */
public class TaskCreatorTest {

    public TaskCreatorTest() {
    }

    /**
     * Test of getMaxNameLength method, of class TaskCreator.
     */
    @Test
    public void testGetMaxNameLengthGreaterThanZero() {
        System.out.println("testing getMaxNameLength() from TaskCreator");
        TaskCreator instance = new TaskCreator();
        int result = instance.getMaxNameLength();
        boolean expResult = result > 0;
        assertTrue(expResult);
    }

    /**
     * Test of getMinNameLength method, of class TaskCreator.
     */
    @Test
    public void testGetMinNameLengthLessThanMaxNameLength() {
        System.out.println("testing getMinNameLength() from TaskCreator");
        TaskCreator instance = new TaskCreator();
        boolean expResult = instance.getMinNameLength() < instance.getMaxNameLength();
        assertTrue(expResult);
    }

    /**
     * Test of createTaskDB method, of class TaskCreator.
     */
    @Test
    public void testCreateTaskDbHomeTask() {
        System.out.println("testing createTaskDB() from TaskCreator with Home Type");
        String taskName = "test";
        String taskType = "Home";
        boolean completed = false;
        TaskCreator instance = new TaskCreator();
        assertTrue(instance.createTaskDB(taskName, taskType, completed));
    }

    @Test
    public void testCreateTaskDbWorkTask() {
        System.out.println("testing createTaskDB() from TaskCreator with Work Type");
        String taskName = "test";
        String taskType = "Work";
        boolean completed = false;
        TaskCreator instance = new TaskCreator();
        assertTrue(instance.createTaskDB(taskName, taskType, completed));
    }

    @Test
    public void testCreateTaskDbInvalidTask() {
        System.out.println("testing createTaskDB() from TaskCreator with Invalid Type");
        String taskName = "test";
        String taskType = "null";
        boolean completed = false;
        TaskCreator instance = new TaskCreator();
        assertFalse(instance.createTaskDB(taskName, taskType, completed));
    }
}
