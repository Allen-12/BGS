package biometricgatesecurity;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class RegisterHouseController implements Initializable 
{
    House h1;
    public static Connection conn;
    Statement statement;
    @FXML
    private TextField houseNo;
    @FXML
    private TextField noOfOccupants;
    @FXML
    private TextField oName;
    @FXML
    private Button registerHouse;
    @FXML
    private Button back;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       
    }    
    
    public void insertHouseDetails(ActionEvent event) throws IOException
    {
        try 
        {
            DbConnector obj1 = new DbConnector();
            obj1.dbconnect();
            conn = obj1.connection;
            System.out.println(conn);
            String hNo = houseNo.getText();
            String occupants = noOfOccupants.getText();
            String owner = oName.getText();
            h1 = new House(hNo,owner,Integer.parseInt(occupants));
            String sql = "INSERT INTO `house`(`house_no`, `no_of_occupants`, `owner_name`)"
                    + " VALUES ('" + hNo + "','" + occupants + "','" + owner + "')";
            statement = conn.createStatement();
            statement.executeUpdate(sql);
//            System.out.println(h1.getHouseNo());
            JOptionPane.showMessageDialog(null, "House added successfully");
            houseNo.setText("");
            noOfOccupants.setText("");
            oName.setText("");
            houseNo.setEditable(false);
            noOfOccupants.setEditable(false);
            oName.setEditable(false);
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(RegisterHouseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goToRegisterOccupants(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RegisterOccupants.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        RegisterOccupantsController roc = loader.getController();
        roc.initData(h1);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("OCCUPANTS");
        window.show();
    }
    
    public void back(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("HouseLanding.fxml"));
        Scene scene = new Scene(root);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("HOUSE");
        window.show();
    }
}
