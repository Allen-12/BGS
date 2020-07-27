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

public class SelectHouseController implements Initializable
{
    private String id;
    private String identifier = "residents";
    public String getId() 
    {
        return id;
    }

    public void setId(String id) 
    {
        this.id = id;
    }
    
    @FXML private Button registerResidents;
    @FXML private Button registerVehicle;
    @FXML ComboBox selectHouse;
    ResultSet rs;
    //List<String> house = new ArrayList<>();
    
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
//        String identifier = "resdients";
        if(id.equals(identifier))
            registerVehicle.setVisible(false);
        else
            registerResidents.setVisible(false);
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
    
    public void goToRegisterOccupants(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RegisterOccupants.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        String hNo = selectHouse.getSelectionModel().getSelectedItem().toString();
//        System.out.println(hNo);
        RegisterOccupantsController roc = loader.getController();
        roc.initHouseNumber(hNo);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("OCCUPANTS");
        window.show();
    }
    
    public void goToRegisterVehicle(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RegisterVehicles.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        String hNo = selectHouse.getSelectionModel().getSelectedItem().toString();
        RegisterVehiclesController rvc = loader.getController();
        rvc.initHouseNumber(hNo);
        
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
