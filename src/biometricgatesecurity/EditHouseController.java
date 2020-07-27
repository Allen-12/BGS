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

public class EditHouseController implements Initializable 
{
    ResultSet rs;
    
    @FXML private Label houseNumber;
    @FXML private TextField noOfOccupants;
    @FXML private TextField oName;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }
    
    public void initHouseNumber(String hNo)
    {
        houseNumber.setText(hNo);
    }
    
    public void initOccupantsAndName(String occ,String name)
    {
        noOfOccupants.setText(occ);
        oName.setText(name);
    }
    public void updateHouseDetails()
    {
        String hNo = houseNumber.getText();
        String noc = noOfOccupants.getText();
        String name = oName.getText();
        try 
        {
            DbConnector obj1 = new DbConnector();
            obj1.dbconnect();
            conn = obj1.connection;
            System.out.println(conn);
            String sql = "UPDATE `house` SET`no_of_occupants`='"+noc+"',`owner_name`='"+name+"' WHERE `house_no`='"+hNo+"'";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "House details updated successfully!!");
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(SelectHouseController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
