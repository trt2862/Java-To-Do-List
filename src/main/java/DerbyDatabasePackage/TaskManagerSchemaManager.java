/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DerbyDatabasePackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Caleb
 */
//initialises connection to DB
public class TaskManagerSchemaManager {

    TaskManagerConnectionManager connManager = new TaskManagerConnectionManager();

    public void initialiseDB() {
        //load the Embedded Derby Drivers.
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            System.out.println("Derby Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.out.println("Derby Driver not found.");
            return;
        }

        //open connection and statement
        Connection connection = connManager.openConnection();
        Statement stmt = connManager.openStatement(connection);
        ResultSet rs = null;
        if (connManager.checkConnection(connection)) {
            try {
                //initialise DB
                stmt = connection.createStatement();

                //check if the table exists
                String checkTableSQL = "SELECT 1 FROM SYS.SYSTABLES WHERE TABLENAME = 'TASKS'";
                rs = stmt.executeQuery(checkTableSQL);

                if (!rs.next()) {
                    //table doesn't exist, so create it
                    String initTableSQL = "CREATE TABLE TASKS ("
                            + "taskID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                            + "TaskName VARCHAR(32), "
                            + "DateCreated VARCHAR(10), "
                            + "Complete VARCHAR(1), "
                            + "TaskType VARCHAR(255)"
                            + ")";
                    stmt.executeUpdate(initTableSQL);
                    System.out.println("Table Created Successfully");
                } else {
                    System.out.println("Table TASKS already exists");
                }
            } catch (SQLException ex) {
                System.out.println("Error Creating Table: " + ex.getMessage());
            } finally {
                connManager.closeResultSet(rs);
                connManager.closeStatement(stmt);
                connManager.closeConnection(connection);
            }
        }
    }
}
