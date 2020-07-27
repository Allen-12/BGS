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

public class SelectHouseVisitorController implements Initializable
{
    String identifier = "edit";
    ResultSet rs;
    List<Visitor> visitors = new ArrayList<>();
    List<String> name = new ArrayList<>();
    Visitor visitor = null;
    EditVisitorController evc = new EditVisitorController();
    
    @FXML private Button edit;
    @FXML private Button delete;
    @FXML private ComboBox selectHouse;
    @FXML private ComboBox selectVisitor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        initializeHouseComboBox();
    }
    
    public void initializeHouseComboBox()
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
    
    public void initializeVisitorComboBox(ActionEvent event)
    {
        String hNo = selectHouse.getSelectionModel().getSelectedItem().toString();
        try 
        {
            DbConnector obj1 = new DbConnector();
            obj1.dbconnect();
            conn = obj1.connection;
            System.out.println(conn);
            String sql = "SELECT * FROM `visitors` WHERE `house_no`='"+hNo+"'";
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            while(rs.next())
            {
                name.add(rs.getString(3));
                visitors.add(new Visitor(rs.getString("house_no"),rs.getString("name"),rs.getString("email"),rs.getDate("date_of_visit").toLocalDate(),rs.getInt("otp")));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(SelectHouseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        selectVisitor.setItems(FXCollections.observableList(name));
    }
    
    public void getSelectedVisitor(ActionEvent event)
    {
        visitor = visitors.get(selectVisitor.getSelectionModel().getSelectedIndex());
        System.out.println(visitor.toString());
    }
    
    public void editDetails(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(evc);
        loader.setLocation(getClass().getResource("EditVisitor.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        evc.initVisitor(visitor);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("EDIT VISITOR DETAILS");
        window.show();
    }
    
    public void deleteVisitor(ActionEvent event)
    {
        try 
        {
            DbConnector obj1 = new DbConnector();
            obj1.dbconnect();
            conn = obj1.connection;
            System.out.println(conn);
            String sql = "DELETE FROM `visitors` WHERE `email`='"+visitor.getEmail()+"'";
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
}
