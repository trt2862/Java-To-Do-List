/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskManagerPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Caleb
 */
public class DBManager {

    private static final String DB_URL = "jdbc:derby://localhost:1527/TaskManagerDatabase;user=pdc;password=pdc";
    Connection conn = null;

    //to handle DB actions
    //to replace FileManager and tasks.txt
    public DBManager() {
    }

    //called by TaskManagerController.
    public void initialiseDB() {
        // Load the Derby driver
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Derby Driver not found.");
            e.printStackTrace();
        }
        if (checkConnection()) {

            Statement stmt = null;
            ResultSet rs = null;
            try {
                // Initialise DB
                conn = DriverManager.getConnection(DB_URL);
                stmt = conn.createStatement();

                // Check if the table exists
                String checkTableSQL = "SELECT 1 FROM SYS.SYSTABLES WHERE TABLENAME = 'TASKS'";
                rs = stmt.executeQuery(checkTableSQL);

                if (!rs.next()) {
                    // Table does not exist, create it
                    String initTableSQL = "CREATE TABLE TASKS ("
                            + "taskID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                            + "TaskName VARCHAR(32), "
                            + "DateCreated VARCHAR(10), "
                            + "Complete CHAR(1), "
                            + "TaskType VARCHAR(255)"
                            + ")";
                    stmt.executeUpdate(initTableSQL);
                    System.out.println("Table Created Successfully");
                } else {
                    System.out.println("Table TASKS already exists");
                }
            } catch (SQLException ex) {
                System.out.println("Error Creating Table: " + ex.getMessage());
                ex.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    //verifies connection to database.
    //displays stack trace if error occurs.
    private static boolean checkConnection() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            if (connection != null) {
                System.out.println("Connected to the database successfully.");
                return true;
            } else {
                System.out.println("Failed to connect to the database.");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error connecting to the database: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    private Connection openConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    private Statement openStatement(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stmt;
    }

    public void add(Task task) {
        Connection conn = openConnection();
        Statement stmt = openStatement(conn);
        try {
            //add task to DB.
            //takes details from task creator.
            char complete = 'n';
            String SQL = "INSERT INTO TASKS (TaskName, DateCreated, Complete, TaskType) VALUES ("
                    + "'" + task.getTaskName() + "', "
                    + "'" + task.getDateCreated() + "', "
                    + "'" + complete + "', "
                    + "'" + task.getType() + "')";
            stmt.executeUpdate(SQL);
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove() {
        //remove task from DB..
    }

    public void update() {
        //update existing task in DB
    }

    public boolean exists() {
        //checks if task exists in DB.
        return false;
    }

    public void search() {
        //returns task being searched for.
    }

    public void undo() {
        //undo previous operation.
    }

    public void sort() {
        //sort list based on a comparator.
    }
}
