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
import java.sql.Savepoint;
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
        //load the Derby driver
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Derby Driver not found.");
        }
        if (checkConnection()) {
            ResultSet rs = null;
            try {
                //initialise DB
                stmt = connection.createStatement();

                //check if the table exists
                String checkTableSQL = "SELECT 1 FROM SYS.SYSTABLES WHERE TABLENAME = 'TASKS'";
                rs = stmt.executeQuery(checkTableSQL);

                if (!rs.next()) {
                    //table does not exist, create it
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

    //opens a new connection to DB using given URL
    public Connection openConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    //closes connection to DB
    public void closeConnection(Connection connection) {
        if (connection != null) {

            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //creates a statement from given connection
    public Statement openStatement(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stmt;
    }

    //closes a Prepared Statement
    public void closeStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //closes a regular statement
    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //closes a result set
    public void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //adds a task to the DB
    public void add(Task task) {
        Connection connection = openConnection();
        Statement stmt = openStatement(connection);
        try {
            //add task to DB.
            //takes details from task creator.
            String saveMessage = "Before adding: " + task.getTaskName();
            connection.setAutoCommit(false);
            Savepoint savepoint = connection.setSavepoint(saveMessage);

            char complete = 'n';
            String SQL = "INSERT INTO TASKS (TaskName, DateCreated, Complete, TaskType) VALUES ("
                    + "'" + task.getTaskName() + "', "
                    + "'" + task.getDateCreated() + "', "
                    + "'" + complete + "', "
                    + "'" + task.getType() + "')";
            stmt.executeUpdate(SQL);
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
            System.out.println("Task Added Successfully.");
        }
    }

    //removes task from DB using matching taskID
    public void remove(int taskId) {
        //remove task from DB..
        String saveMessage = "Before removing: " + taskId;
        String SQL = "DELETE FROM TASKS WHERE TASKID = ?";
        Connection connection = openConnection();
        PreparedStatement stmt = null;
        try {
            connection.setAutoCommit(false);
            Savepoint savepoint = connection.setSavepoint(saveMessage);
            stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, taskId);
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
            System.out.println("Task Removed Successfully.");
        }
    }

    //removes all tasks from DB
    public void removeAll() {
        //remove all tasks from DB.
        String SQL = "DELETE FROM TASKS";
        String saveMessage = "Before removing all tasks";
        Connection connection = openConnection();
        PreparedStatement stmt = null;
        try {
            connection.setAutoCommit(false); //begin transaction with DB
            Savepoint savepoint = connection.setSavepoint(saveMessage); //save state of DB
            stmt = connection.prepareStatement(SQL);
            stmt.executeUpdate();
            connection.commit(); //end transaction with DB
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
            System.out.println("All Tasks Removed Successfully.");
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

    //set value using generics
    public <E> void update(int taskId, String columnName, E value) {
        // Update existing task in DB
        String SQL = "UPDATE TASKS SET " + columnName + " = ? WHERE TASKID = ?";
        Connection connection = openConnection();
        PreparedStatement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(SQL);

            if (value instanceof String) {
                //Capitalise first letter.
                String str = (String) value;
                String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
                stmt.setString(1, cap);
            } else if (value instanceof Character) {
                stmt.setString(1, value.toString());
            } else {
                throw new IllegalArgumentException("Unsupported type: " + value.getClass().getName());
            }

            stmt.setInt(2, taskId);
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackEx) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, "Rollback failed", rollbackEx);
            }
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, "Update failed", ex);
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
        }
    }

    //checks if given ID exists in DB
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
            stmt.setInt(1, taskId);  //set taskID
            rs = stmt.executeQuery();

            //if a row is returned, the task exists.
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

    //not sure it will be very easy to implement this with DB
    //will leave till last. may have to forfeit given time
    //constrains with assignment.
    public void undo() {
//        String SQL = "ROLLBACK TO SAVEPOINT";
//        Connection connection = openConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = connection.prepareStatement(SQL);
//            stmt.executeUpdate();
//            //undo previous operation.
//        } catch (SQLException ex) {
//            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            closeConnection(connection);
//            closeStatement(stmt);
//        }
    }

    public void sort() {
        //sort list based on a comparator.
    }

    //gets resultset of task to be parsed for printing
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

    //gets a result set of all tasks to be parsed for printing
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
