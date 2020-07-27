package biometricgatesecurity;

import static biometricgatesecurity.RegisterHouseController.conn;
import static biometricgatesecurity.RegisterOccupantsController.selectedHouse;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class RegisterVehiclesController implements Initializable
{
    @FXML private TextField regNo;
    @FXML private ColorPicker colour;
    @FXML private Label houseNumber;
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
    
    public void insertVehicleDetails(ActionEvent event) throws IOException
    {
        try 
        {
            DbConnector obj1 = new DbConnector();
            obj1.dbconnect();
            conn = obj1.connection;
            System.out.println(conn);
            String rNo = regNo.getText();
            String hNo = houseNumber.getText();
            String color = colour.getValue().toString();
            String sql = "INSERT INTO `vehicle`(`reg_no`, `house_no`, `colour`)"
                    + " VALUES ('" + rNo + "','" + hNo + "','" + color + "')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Vehicle added successfully");
            
            regNo.setText("");
            colour.setValue(Color.WHITE);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RegisterVehiclesController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
