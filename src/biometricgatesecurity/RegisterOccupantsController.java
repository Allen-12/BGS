package biometricgatesecurity;

import static biometricgatesecurity.RegisterHouseController.conn;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class RegisterOccupantsController implements Initializable 
{
    ResultSet rs;
    
    EnrollFingerprintController efc = new EnrollFingerprintController();
    
    public static House selectedHouse;
    @FXML private Label houseNumber;
    @FXML private TextField occupantName;
    @FXML private TextField idNo;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
    
    public void initData(House house)
    {
        selectedHouse = house;
        houseNumber.setText(selectedHouse.getHouseNo());
    }
    
    public void initHouseNumber(String hNo)
    {
        houseNumber.setText(hNo);
    }
    
    public void insertOccupantDetails(ActionEvent event) throws IOException
    {
        try 
        {
            DbConnector obj1 = new DbConnector();
            obj1.dbconnect();
            conn = obj1.connection;
            System.out.println(conn);
            String name = occupantName.getText();
            String id = idNo.getText();
            String hNo = houseNumber.getText();
            String sql = "INSERT INTO `occupants`(`name`, `id_no`, `house_no`)"
                    + " VALUES ('" + name + "','" + id + "','" + hNo + "')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Occupant added successfully");
            
            occupantName.setText("");
            idNo.setText("");
            
            String sql2 = "SELECT `id`, `name` FROM `occupants` WHERE `house_no`='"+ hNo +"'AND `id_no`='"+ id +"'";
            rs = statement.executeQuery(sql2);
            
            FXMLLoader loader = new FXMLLoader();
            loader.setController(efc);
            loader.setLocation(getClass().getResource("EnrollFingerprint.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            while(rs.next())
            {
                efc.initOccupantDetails(rs.getInt("id"), rs.getString("name"));
            }
            
            Stage window = new Stage();
            window.setScene(scene);
            window.setTitle("ENROLL FINGERPRINT");
            window.show();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RegisterOccupantsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goToRegisterVehicle(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RegisterVehicles.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        RegisterVehiclesController rvc = loader.getController();
        rvc.initData(selectedHouse);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("VEHICLES");
        window.show();
    }
    
    public void back(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("AdministratorLanding.fxml"));
        Scene scene = new Scene(root);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("ADMINISTRATOR");
        window.show();
    }
}
