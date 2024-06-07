/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DerbyDatabasePackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Caleb
 */
public class TaskManagerConnectionManagerTest {

    public TaskManagerConnectionManagerTest() {
    }

    /**
     * Test of checkConnection method, of class TaskManagerConnectionManager.
     */
    @Test
    public void testCheckConnection() {
        System.out.println("checkConnection");
        TaskManagerConnectionManager instance = new TaskManagerConnectionManager();
        Connection connection = instance.openConnection();
        assertTrue(instance.checkConnection(connection));
    }

    /**
     * Test of openConnection method, of class TaskManagerConnectionManager.
     */
    @Test
    public void testOpenConnection() {
        System.out.println("openConnection");
        TaskManagerConnectionManager instance = new TaskManagerConnectionManager();
        Connection connection = instance.openConnection();
        assertNotNull(connection);
    }

    /**
     * Test of openStatement method, of class TaskManagerConnectionManager.
     */
    @Test
    public void testOpenStatement() {
        System.out.println("openStatement");
        TaskManagerConnectionManager instance = new TaskManagerConnectionManager();
        Connection connection = instance.openConnection();
        Statement stmt = instance.openStatement(connection);
        assertNotNull(stmt);
    }
}
