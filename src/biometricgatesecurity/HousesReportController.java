package biometricgatesecurity;

import static biometricgatesecurity.RegisterHouseController.conn;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;

public class HousesReportController implements Initializable
{
    ResultSet rs;
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
    
    public List<House> getHouses()
    {
        List<House> houses = new ArrayList<>();
        
        try 
        {
            DbConnector obj1 = new DbConnector();
            obj1.dbconnect();
            conn = obj1.connection;
            System.out.println(conn);
            String sql = "SELECT * FROM `house`";
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            System.out.println(rs);
            while(rs.next())
            {
                houses.add(new House(rs.getString("house_no"), rs.getString("owner_name"),rs.getInt("no_of_occupants")));
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(SelectHouseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return houses;
    }
}
