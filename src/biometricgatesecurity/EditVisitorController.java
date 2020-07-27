package biometricgatesecurity;

import static biometricgatesecurity.RegisterHouseController.conn;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class EditVisitorController implements Initializable
{
    Visitor visitor = null;
    
    @FXML private TextField name;
    @FXML private Label houseNumber;
    @FXML private TextField email;
    @FXML private DatePicker dov;
  
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
    
    public void initVisitor(Visitor visitor)
    {
        this.visitor = visitor;
        houseNumber.setText(visitor.getHouseNo());
        name.setText(visitor.getName());
        email.setText(visitor.getEmail());
        dov.setValue(visitor.getDateOfVisit());
    }
    
    public void updateVisitorDetails()
    {
//        String hNo = houseNumber.getText();
        String em = email.getText();
        String vname = name.getText();
        String date = dov.getValue().toString();
        try 
        {
            DbConnector obj1 = new DbConnector();
            obj1.dbconnect();
            conn = obj1.connection;
            System.out.println(conn);
            String sql = "UPDATE `visitors` SET `name`='"+ vname+"',`date_of_visit`='"+date+"' WHERE `email`='"+em+"'";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Visitor details updated successfully!!");
            email.setEditable(false);
            name.setEditable(false);
            dov.setEditable(false);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(SelectHouseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void back(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("ResidentVisitorLanding.fxml"));
        Scene scene = new Scene(root);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("VISITOR");
        window.show();
    }
}
