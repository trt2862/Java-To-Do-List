/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DerbyDatabasePackage;

import java.sql.*;

/**
 *
 * @author Caleb
 */
public interface ConnectionManager {

    public boolean checkConnection(Connection conn);

    public Connection openConnection();

    public Statement openStatement(Connection conn);

    public void closeConnection(Connection conn);

    public void closeStatement(Statement stmt);

    public void closeStatement(PreparedStatement stmt);

    public void closeResultSet(ResultSet rs);

}
