package biometricgatesecurity;

import static biometricgatesecurity.RegisterHouseController.conn;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewResidentLogsController implements Initializable 
{
    ResultSet rs;
    
    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> timestamp;

    @FXML
    private TableColumn<?, ?> rName;

    @FXML
    private TableColumn<?, ?> vehicle;

    @FXML
    private TableColumn<?, ?> hseNo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
    
    public void getResidentLogs()
    {
        try
        {
            DbConnector obj1 = new DbConnector();
            obj1.dbconnect();
            conn = obj1.connection;
            System.out.println(conn);
            String sql = "SELECT * FROM `resident_logs`";
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            
            while(rs.next())
            {
                
            }
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(ViewResidentLogsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
