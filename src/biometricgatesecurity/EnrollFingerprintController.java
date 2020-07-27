package biometricgatesecurity;

import static biometricgatesecurity.RegisterHouseController.conn;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class EnrollFingerprintController implements Initializable
{
    private String aText;
    Node node;
    
    @FXML private TextArea arduinoContent;
    @FXML private TextField id;
    @FXML private Label oID;
    @FXML private Label oName;

    public String getaText() 
    {
        return aText;
    }

    public void setaText(String aText) 
    {
        this.aText = aText;
    }

    public TextArea getArduinoContent() 
    {
        return arduinoContent;
    }

    public void setArduinoContent(TextArea arduinoContent)
    {
        this.arduinoContent = arduinoContent;
    }

    public TextField getId()
    {
        return id;
    }

    public void setId(TextField id)
    {
        this.id = id;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
////        Communicator close = new Communicator();
////        close.close();
        Communicator main = new Communicator();
        Thread t=new Thread()
        {
            @Override
            public void run() 
            {   
                while(true)
                {
                    main.initialize(arduinoContent);
//                    try {Thread.sleep(5000);} catch (InterruptedException ie) {}

                }
            }
        };
        t.start();
            
        System.out.println("Started");
    }   
    
    public void initOccupantDetails(int id,String name)
    {
        oID.setText(String.valueOf(id));
        oName.setText(name);
    }
    
    public void enroll(ActionEvent event) throws IOException
    {
        try 
        {
            DbConnector obj1 = new DbConnector();
            obj1.dbconnect();
            conn = obj1.connection;
            System.out.println(conn);
            String fingerprintID = id.getText();
            String occupantID = oID.getText();
            String sql = "UPDATE `occupants` SET `fingerprint_id`='"+ fingerprintID +"' WHERE `id`='"+ occupantID +"'";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Fingerprint for "+ oName.getText() +" added successfully!");
            id.setEditable(false);
            
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(EnrollFingerprintController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setScrollPane(String aText)
    {
        
    }
}
