package biometricgatesecurity;

import static biometricgatesecurity.RegisterHouseController.conn;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class RegisterVisitorController implements Initializable 
{
    ResultSet rs;
    @FXML DatePicker date;
    @FXML ComboBox hNo;
    @FXML Button gotp;
    @FXML Label otp;
    @FXML TextField email;
    @FXML TextField name;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        initializeComboBox();
    }

    public void initializeComboBox()
    {
        List<String> house = getHouseDetails();
        hNo.setItems(FXCollections.observableList(house));
    }
    
    public void setOTP()
    {
        OTP otp1 = new OTP();
        otp.setText(otp1.passcode(otp1.length)+"");
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
    
    public List<String> getHouseDetails()
    {
        List<String> house = new ArrayList<>();
        try 
        {
            DbConnector obj1 = new DbConnector();
            obj1.dbconnect();
            conn = obj1.connection;
            System.out.println(conn);
            String sql = "SELECT `house_no` FROM `house`";
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            System.out.println(rs);
            while(rs.next())
            {
                house.add(rs.getString(1));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(SelectHouseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return house;
    }
    
    public void createVisitor(ActionEvent event) throws IOException
    {
        try 
        {
            DbConnector obj1 = new DbConnector();
            obj1.dbconnect();
            conn = obj1.connection;
            System.out.println(conn);
            String ea = email.getText();
            String vName = name.getText();
            String house = hNo.getSelectionModel().getSelectedItem().toString();
            String datePicked = date.getValue().toString();
            String o = otp.getText();
            String sql = "INSERT INTO `visitors`(`email`, `name`, `date_of_visit`, `house_no`, `otp`)"
                    + "VALUES ('" + ea +"','"+ vName +"','"+ datePicked +"','"+ house +"','"+ o +"')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            
            SendEmail se = new SendEmail();
            se.send("allennjuguna@gmail.com","uthlqbeqgtnhljod",ea,"VISITOR ONE TIME PASSCODE","Your request to visit house "+house+" on "+datePicked+" has been approved. The passcode that you will use at the gate is "+o+".\nBGS SECURITY TEAM.");
            JOptionPane.showMessageDialog(null, "Your visitor has been sent an email.");
            email.setText("");
            name.setText("");
            hNo.setValue(null);
            date.setValue(null);
            otp.setText("");
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RegisterVisitorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
