/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DerbyDatabasePackage;

import TaskManagerPackage.Task;
import TaskManagerPackage.WorkTask;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Caleb
 */
public class TaskManagerRepositoryTest {

    public TaskManagerRepositoryTest() {
    }

    /**
     * Test of add method, of class TaskManagerRepository.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Task task = new WorkTask();
        TaskManagerRepository instance = new TaskManagerRepository();
        assertTrue(instance.add(task));
    }

    /**
     * Test of removeAll method, of class TaskManagerRepository.
     */
    @Test
    public void testRemoveAll() {
        System.out.println("removeAll");
        TaskManagerRepository instance = new TaskManagerRepository();
        assertTrue(instance.removeAll());
    }

    /**
     * Test of isEmpty method, of class TaskManagerRepository.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        TaskManagerRepository instance = new TaskManagerRepository();
        instance.removeAll();
        assertTrue(instance.isEmpty());
    }

    /**
     * Test of exists method, of class TaskManagerRepository.
     */
    @Test
    public void testExists() {
        System.out.println("exists");
        int primaryKey = -1;
        TaskManagerRepository instance = new TaskManagerRepository();
        assertFalse(instance.exists(primaryKey));
    }

}
