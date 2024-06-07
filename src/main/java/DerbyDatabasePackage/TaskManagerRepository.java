/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DerbyDatabasePackage;

import TaskManagerPackage.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Caleb
 */
//handles CRUD
public class TaskManagerRepository implements Repository<Task> {

    TaskManagerConnectionManager connManager;

    //default login credentials
    public TaskManagerRepository() {
        connManager = new TaskManagerConnectionManager();
    }

    //custom login credentials
    public TaskManagerRepository(String user, String pass, String url) {
        connManager = new TaskManagerConnectionManager(user, pass, url);
    }

    @Override
    public boolean add(Task task) {
        Connection connection = connManager.openConnection();
        Statement stmt = connManager.openStatement(connection);
        try {
            //add task to DB.
            //takes details from task creator.
            char complete;
            if (task.isCompleted()) {
                complete = 'Y';
            } else {
                complete = 'N';
            }
            //SQL Statement
            String SQL = "INSERT INTO TASKS (TaskName, DateCreated, Complete, TaskType) VALUES ("
                    + "'" + task.getTaskName() + "', "
                    + "'" + task.getDateCreated() + "', "
                    + "'" + complete + "', "
                    + "'" + task.getType() + "')";
            stmt.executeUpdate(SQL);
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            connManager.closeStatement(stmt);
            connManager.closeConnection(connection);
        }
        System.out.println("Task Added to DB Successfully.");
        return true;
    }

    @Override
    public boolean remove(int taskId) {
        //remove task from DB..
        if (exists(taskId)) {

            //SQL Statement
            String SQL = "DELETE FROM TASKS WHERE TASKID = ?";
            Connection connection = connManager.openConnection();
            PreparedStatement stmt = null;
            try {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(SQL);
                //sets '?' to taskId in SQL Statement
                stmt.setInt(1, taskId);
                stmt.executeUpdate();
                connection.commit();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                connManager.closeStatement(stmt);
                connManager.closeConnection(connection);
                System.out.println("Task Removed Successfully.");
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeAll() {
        //remove all tasks from DB.
        if (!isEmpty()) {

            String SQL = "DELETE FROM TASKS";
            Connection connection = connManager.openConnection();
            PreparedStatement stmt = null;
            try {
                //begin transaction with DB
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(SQL);
                stmt.executeUpdate();
                //end transaction with DB
                connection.commit();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                connManager.closeStatement(stmt);
                connManager.closeConnection(connection);
                System.out.println("All Tasks Removed Successfully.");
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isEmpty() {
        String SQL = "SELECT COUNT(*) FROM TASKS";
        Connection connection = connManager.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.prepareStatement(SQL);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0; // isnt empty
            } else {
                return true; // is empty
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connManager.closeResultSet(rs);
            connManager.closeConnection(connection);
            connManager.closeStatement(stmt);
        }
        return false;
    }

    @Override
    public boolean exists(int primaryKey) {
        //SQL query to check if a task with the given taskId exists
        String SQL = "SELECT 1 FROM TASKS WHERE TASKID = ?";
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = connManager.openConnection();
            if (connection == null) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, "Failed to establish connection.");
                return false;
            }

            stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, primaryKey);  //set '?' to taskID
            rs = stmt.executeQuery();

            //if a row is returned, the task exists.
            return rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, "Error checking if task exists.", ex);
            return false;
        } finally {
            connManager.closeResultSet(rs);
            connManager.closeStatement(stmt);
            connManager.closeConnection(connection);
        }
    }

    @Override
    public ResultSet getElement(int primaryKey) {
        String SQL = "SELECT * FROM TASKS WHERE TASKID = ?";
        Connection connection = connManager.openConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, primaryKey); //set '?' to taskID
            return stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        connManager.closeConnection(connection);
        return null;
    }

    @Override
    public ResultSet getAllElements() {
        String SQL = "SELECT * FROM TASKS";
        Connection connection = connManager.openConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement stmt = connection.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            return stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            connManager.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void update(int primaryKey, String columnName, Object value) {
        //update existing task in DB
        String SQL = "UPDATE TASKS SET " + columnName + " = ? WHERE TASKID = ?";
        Connection connection = connManager.openConnection();
        PreparedStatement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(SQL);

            if (value instanceof String) {
                //capitalise first letter.
                String str = (String) value;
                String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
                stmt.setString(1, cap); //sets columnName
            } else if (value instanceof Character) {
                stmt.setString(1, value.toString());
            } else {
                //more supported types can be added in the future.
                throw new IllegalArgumentException("Unsupported type: ");
            }

            stmt.setInt(2, primaryKey); //sets taskID
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, "Update failed", ex);
        } finally {
            connManager.closeStatement(stmt);
            connManager.closeConnection(connection);
        }
    }

}
