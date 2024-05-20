/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskManagerPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    //to handle DB actions
    //to replace FileManager and tasks.txt
    public DBManager() {
    }

    //called by TaskManagerController.
    public void initialiseDB() {
        Connection connection = openConnection();
        Statement stmt = openStatement(connection);
        // Load the Derby driver
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Derby Driver not found.");
        }
        if (checkConnection()) {
            ResultSet rs = null;
            try {
                // Initialise DB
                stmt = connection.createStatement();

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
            } finally {
                closeResultSet(rs);
                closeConnection(connection);
                closeStatement(stmt);
            }
        }
    }

    //verifies connection to database.
    private static boolean checkConnection() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            if (connection != null) {
                System.out.println("Connected to the database successfully.");
                connection.close();
                return true;
            } else {
                System.out.println("Failed to connect to the database.");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error connecting to the database: " + ex.getMessage());
            return false;
        }
    }

    public Connection openConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {

            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Statement openStatement(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stmt;
    }

    public void closeStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void add(Task task) {
        Connection connection = openConnection();
        Statement stmt = openStatement(connection);
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
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
        }
    }

    public void remove(int taskId) {
        //remove task from DB..
        String SQL = "DELETE FROM TASKS WHERE TASKID = ?";
        Connection connection = openConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, taskId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
        }
    }

    public void removeAll() {
        //remove all tasks from DB.
        String SQL = "DELETE FROM TASKS";
        Connection connection = openConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(SQL);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
        }
    }

    //returns true if list is empty.
    //returns false if list has content.
    public boolean isEmpty() {
        String SQL = "SELECT COUNT(*) FROM TASKS";
        Connection connection = openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.prepareStatement(SQL);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closeConnection(connection);
            closeStatement(stmt);
        }
        return false;
    }

    public void update(int taskId) {
        //update existing task in DB
    }

    public boolean exists(int taskId) {
        // SQL query to check if a task with the given taskId exists
        String SQL = "SELECT 1 FROM TASKS WHERE TASKID = ?";
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = openConnection();
            if (connection == null) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, "Failed to establish connection.");
                return false;
            }

            stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, taskId);  // Set taskId parameter
            rs = stmt.executeQuery();

            // If any row is returned, the task exists
            return rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, "Error checking if task exists.", ex);
            return false;
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(connection);
        }
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

    public ResultSet getTask(int taskId) {
        String SQL = "SELECT * FROM TASKS WHERE TASKID = ?";
        Connection connection = openConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, taskId);
            return stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection(connection);
        return null;
    }

    public ResultSet getAllTasks() {
        String SQL = "SELECT * FROM TASKS";
        Connection connection = openConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement stmt = connection.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            return stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            closeConnection(connection);
        }
        return null;
    }
}
