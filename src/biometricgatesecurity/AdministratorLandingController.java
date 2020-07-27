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

public class AdministratorLandingController implements Initializable 
{
    @FXML private Button residents;
    @FXML private Button vehicle;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }  
    
    public void house(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("HouseLanding.fxml"));
        Scene scene = new Scene(root);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("HOUSE");
        window.show();
    }
    
    public void goToSelectHouse(ActionEvent event) throws IOException 
    {
        SelectHouseController shc = new SelectHouseController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(shc);
        loader.setLocation(getClass().getResource("SelectHouse.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        Node source = (Node)event.getSource();
        String id = source.getId();
        System.out.println(id);
        shc.getButtonID(id);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("SELECT A HOUSE");
        window.show();
    }
    
    public void back(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(root);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("BIOMETRIC GATE SECURITY");
        window.show();
    }
    
    public void enrollFingerprint(ActionEvent event) throws IOException 
    {
        EnrollFingerprintController efc = new EnrollFingerprintController();
        //efc.setaText(Communicator.test);
        FXMLLoader loader = new FXMLLoader();
        loader.setController(efc);
        loader.setLocation(getClass().getResource("EnrollFingerprint.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("ENROLL FINGERPRINT");
        window.show();
    }
}
