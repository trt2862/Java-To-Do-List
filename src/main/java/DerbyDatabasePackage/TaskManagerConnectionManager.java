/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DerbyDatabasePackage;

import TaskManagerPackage.DBManager;
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
//handles DB Connections
public class TaskManagerConnectionManager implements ConnectionManager {

    private static String USER_NAME;
    private static String PASSWORD;
    private static String DB_URL;

    public TaskManagerConnectionManager(String user, String pass, String url) {
        USER_NAME = user;
        PASSWORD = pass;
        DB_URL = url;
    }

    public TaskManagerConnectionManager() {
        USER_NAME = "pdc";
        PASSWORD = "pdc";
        DB_URL = "jdbc:derby:TaskManagerDatabase(1);create=true";
    }

    @Override
    public boolean checkConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Connected to the database successfully.");
                return true;
            } else {
                System.out.println("Failed to connect to the database.");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error checking connection to the database: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public Connection openConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    @Override
    public Statement openStatement(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stmt;
    }

    @Override
    public void closeConnection(Connection connection) {
        if (connection != null) {

            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void closeStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
