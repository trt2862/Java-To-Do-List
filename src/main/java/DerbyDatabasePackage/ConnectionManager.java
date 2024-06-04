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

    //CHECKS CONNECTION TO DATABASE
    public boolean checkConnection(Connection conn);

    //OPENS A CONNECTION TO A DATABASE
    public Connection openConnection();

    //CREATES A STATEMENT
    public Statement openStatement(Connection conn);

    //CLOSES RESOURCES OF A CONNECTION
    public void closeConnection(Connection conn);

    //CLOSES RESOURCES OF A STATEMENT (STATEMENT)
    public void closeStatement(Statement stmt);

    //CLOSES RESOURCES OF A STATEMENT (PREPAREDSTATEMENT)
    public void closeStatement(PreparedStatement stmt);

    //CLOSES RESOURCES OF A RESULTSET
    public void closeResultSet(ResultSet rs);

}
