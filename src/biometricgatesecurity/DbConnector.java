package biometricgatesecurity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnector 
{
    public Connection connection;
    
    public void dbconnect()
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bgs","root","");
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
}
