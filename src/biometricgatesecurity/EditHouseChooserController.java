package biometricgatesecurity;

import static biometricgatesecurity.RegisterHouseController.conn;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class EditHouseChooserController implements Initializable 
{
    String identifier = "edit";
    
    @FXML private ComboBox selectHouse;
    @FXML private Button edit;
    @FXML private Button delete;
    
    ResultSet rs;
    EditHouseController ehc = new EditHouseController();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        initializeComboBox();
    } 
    
    public void initializeComboBox()
    {
        List<String> house = getHouseDetails();
        selectHouse.setItems(FXCollections.observableList(house));
    }
    
    public void getButtonID(String id)
    {
        if(id.equals(identifier))
            delete.setVisible(false);
        else
            edit.setVisible(false);
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
    
    
    public void editHouseDetails(ActionEvent event) throws IOException
    {
//        EditHouseController ehc = new EditHouseController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(ehc);
        loader.setLocation(getClass().getResource("EditHouse.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        String hNo = selectHouse.getSelectionModel().getSelectedItem().toString();
        
        System.out.println(hNo);
        ehc.initHouseNumber(hNo);
        getSelectedHouseDetails(hNo);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("EDIT HOUSE DETAILS");
        window.show();
    }
    
    public void deleteHouse(ActionEvent event)
    {
        String house = selectHouse.getSelectionModel().getSelectedItem().toString();
        try 
        {
            DbConnector obj1 = new DbConnector();
            obj1.dbconnect();
            conn = obj1.connection;
            System.out.println(conn);
            String sql = "DELETE FROM `house` WHERE `house_no`='"+ house +"'";
            Statement statement = conn.createStatement();
            int returnValue = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this information?","DELETE HOUSE?",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if(returnValue == 0)
            {
                statement.executeUpdate(sql);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EditHouseChooserController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public void getSelectedHouseDetails(String hNo)
    {
        try 
        {
            DbConnector obj1 = new DbConnector();
            obj1.dbconnect();
            conn = obj1.connection;
            System.out.println(conn);
            String sql = "SELECT `no_of_occupants`,`owner_name` FROM `house` WHERE `house_no` ='"+hNo+"'";
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            System.out.println(rs);
            while(rs.next())
            {
                ehc.initOccupantsAndName(String.valueOf(rs.getInt(1)),rs.getString(2));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(SelectHouseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
