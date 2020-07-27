package biometricgatesecurity;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HouseLandingController implements Initializable 
{
    
    @FXML private Button edit;
    @FXML private Button delete;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
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
    
    public void registerHouse(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("RegisterHouse.fxml"));
        Scene scene = new Scene(root);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("HOUSE");
        window.show();
    }
    
    public void changeHouse(ActionEvent event) throws IOException
    {
        EditHouseChooserController ehcc = new EditHouseChooserController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(ehcc);
        loader.setLocation(getClass().getResource("EditHouseChooser.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        Node source = (Node)event.getSource();
        String id = source.getId();
        System.out.println(id);
        ehcc.getButtonID(id);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("EDIT HOUSE DETAILS");
        window.show();
    }
}
