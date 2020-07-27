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

public class ResidentVisitorLandingController implements Initializable 
{
    SelectHouseVisitorController shvc = new SelectHouseVisitorController();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
    
    public void back(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("ResidentLanding.fxml"));
        Scene scene = new Scene(root);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("BIOMETRIC GATE SECURITY");
        window.show();
    }
    
    public void registorVisitor(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("RegisterVisitor.fxml"));
        Scene scene = new Scene(root);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("ADD VISITOR");
        window.show();
    }
    
    public void editVisitor(ActionEvent event) throws IOException
    {
//        SelectHouseVisitorController shvc = new SelectHouseVisitorController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(shvc);
        loader.setLocation(getClass().getResource("SelectHouseVisitor.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        Node source = (Node)event.getSource();
        String id = source.getId();
        System.out.println(id);
        shvc.getButtonID(id);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("CHANGE VISITOR DETAILS");
        window.show();
    }
    
    public void deleteVisitor(ActionEvent event) throws IOException
    {
//        SelectHouseVisitorController shvc = new SelectHouseVisitorController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(shvc);
        loader.setLocation(getClass().getResource("SelectHouseVisitor.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        Node source = (Node)event.getSource();
        String id = source.getId();
        System.out.println(id);
        shvc.getButtonID(id);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("CHANGE VISITOR DETAILS");
        window.show();
    }
}
