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
public class TaskRemoverTest {

    DBManager dbm = new DBManager();

    public TaskRemoverTest() {
    }

    /**
     * Test of removeTaskFromDB method, of class TaskRemover.
     */
    @Test
    public void testRemoveTaskFromDB() {
        System.out.println("testing removeTaskFromDB() from TaskRemover");
        int taskId = -1;
        TaskRemover instance = new TaskRemover();
        assertFalse(instance.removeTaskFromDB(taskId));
    }

    /**
     * Test of removeAllTasksFromDB method, of class TaskRemover.
     */
    @Test
    public void testRemoveAllTasksFromDB() {
        System.out.println("testing removeAllTasksFromDB() from TaskRemover");
        TaskRemover instance = new TaskRemover();
        if (dbm.isEmpty()) {
            assertFalse(instance.removeAllTasksFromDB());
        } else {
            assertTrue(instance.removeAllTasksFromDB());
        }
    }
}
