package biometricgatesecurity;

import static biometricgatesecurity.RegisterHouseController.conn;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class VerifyFingerprintController implements Initializable 
{
    @FXML private TextField fID;
    @FXML private ComboBox selectVehicle;
    
    List<String> vehicles = new ArrayList<>();
    ResultSet rs;
    ResultSet rs2;
    String houseNo;
    String name;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        fID.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
            {
                if(!newValue.isEmpty())
                {
                    fetchData();
                }
            }
            
        });
//        CommunicatorVerify close = new CommunicatorVerify();
//        close.close();
        CommunicatorVerify main = new CommunicatorVerify();
        main.initialize(fID);
        Thread t=new Thread()
        {
            @Override
            public void run() 
            {
                try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
            }
        };
        t.start();
        System.out.println("Started");
    }
    
    public void getVehicles(ActionEvent event)
    {
        fetchData();
    }
    
    public void fetchData()
    {
        try
        {
            String fingerID = fID.getText();
            
            DbConnector obj1 = new DbConnector();
            obj1.dbconnect();
            conn = obj1.connection;
            System.out.println(conn);
            String sql = "SELECT `name`,`house_no` FROM `occupants` WHERE `fingerprint_id`='"+ fingerID +"'";
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            
            if(rs.next())
            {
                houseNo = rs.getString("house_no");
                name = rs.getString("name");
                String sql2 = "SELECT `reg_no` FROM `vehicle` WHERE `house_no`='"+ houseNo +"'";
                Statement statement2 = conn.createStatement();
                rs2 = statement2.executeQuery(sql2);
                while(rs2.next())
                {
                    vehicles.add(rs2.getString(1));
                    System.out.println(vehicles.toString());
                }
                vehicles.add("No vehicle");
                selectVehicle.setItems(FXCollections.observableList(vehicles));
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Sorry! We cannot find a match!","ERROR!INVALID FINGERPRINT!!",JOptionPane.ERROR_MESSAGE);
            }
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(VerifyFingerprintController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void verify(ActionEvent event)
    {
        String vehicle = selectVehicle.getSelectionModel().getSelectedItem().toString();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try
        {
            DbConnector obj1 = new DbConnector();
            obj1.dbconnect();
            conn = obj1.connection;
            System.out.println(conn);
            
            String sql = "INSERT INTO `resident_logs`(`timestamp`, `name`, `vehicle`, `house_no`) "
                    + "VALUES ('"+ timestamp +"','" + name +"','"+ vehicle +"','"+ houseNo +"')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Verified! Welcome!!","WLECOME!!",JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(VerifyFingerprintController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
