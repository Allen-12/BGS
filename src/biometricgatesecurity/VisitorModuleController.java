package biometricgatesecurity;

import static biometricgatesecurity.RegisterHouseController.conn;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
//import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class VisitorModuleController implements Initializable 
{
    ResultSet rs;
    List<String> emails = new ArrayList<>();
    
    @FXML private ComboBox selectEmail;
    @FXML private TextField otp;
//    @FXML private Button verify;
    int maxTriesX = 3;
    int counter = 1;
    boolean success = false;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        List<String> cboemails = getEmails();
        selectEmail.setItems(FXCollections.observableList(cboemails));
    }    
    
    public void verifyPasscode()
    {
        
        if (counter > maxTriesX) 
        {
            JOptionPane.showMessageDialog(null, "You've been locked out!!!");
            otp.setEditable(false);
        } 
        else
        {
            String email = selectEmail.getSelectionModel().getSelectedItem().toString();
            try
            {
                if (!verifyOTP(otp.getText(),email)) 
                {
                    counter += 1;
                    otp.setText("");
                    JOptionPane.showMessageDialog(null, "Invalid Passcode! Please try again!");
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "Welcome "+rs.getString("name")+". Your passcode has been verified successfully");
                }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(VisitorModuleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean verifyOTP(String otpN,String email) throws SQLException
    {
        String sql = "SELECT `email`,`name`,`date_of_visit`,`house_no`,`otp` FROM `visitors` WHERE `otp`='"+otpN+"' AND `email`='"+ email +"'";
        DbConnector obj1 = new DbConnector();
        obj1.dbconnect();
        conn = obj1.connection;
        System.out.println(conn);
        Statement statement = conn.createStatement();
        rs = statement.executeQuery(sql);
        while(rs.next())
        {
            if(rs.getInt("otp") == Integer.parseInt(otpN))
            {
//                JOptionPane.showMessageDialog(null, "Welcome "+rs.getString("name")+". Your passcode has been verified successfully");
                Date timestamp = new Date();
                String em = rs.getString("email");
                String name = rs.getString("name");
                String hNo = rs.getString("house_no");
                String sql1 = "INSERT INTO `visitor_log`(`timestamp`, `email`, `name`, `house_no`)"
                        + "VALUES('"+timestamp+"','"+ em +"','"+ name +"','"+ hNo +"')";
                Statement statement1 = conn.createStatement();
                statement1.executeUpdate(sql1);
                otp.setText("");
                selectEmail.setValue(null);
                return true;
            }
        }
        return false;
    }
    
    public List<String> getEmails()
    {
        try 
        {
            DbConnector obj1 = new DbConnector();
            obj1.dbconnect();
            conn = obj1.connection;
            System.out.println(conn);
            String sql = "SELECT `email` FROM `visitors`";
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            System.out.println(rs);
            while(rs.next())
            {
                emails.add(rs.getString(1));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(SelectHouseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emails;
    }
}
