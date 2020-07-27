package biometricgatesecurity;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ResidentLandingController implements Initializable 
{
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
    
    public void gate(ActionEvent event) throws IOException
    {
        VerifyFingerprintController vfc = new VerifyFingerprintController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(vfc);
        loader.setLocation(getClass().getResource("VerifyFingerprint.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("GATE VERIFICATION");
        window.show();
    }
    
    public void visitors(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("ResidentVisitorLanding.fxml"));
        Scene scene = new Scene(root);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("VISITOR");
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
}
