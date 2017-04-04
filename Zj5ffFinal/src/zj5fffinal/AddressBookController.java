/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zj5fffinal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class AddressBookController implements Initializable, NewPerson {
    
    private Stage stage;
    private Scene scene;
    
    private Scene scene2;
    private NewContactController scene2Controller;
    
    @FXML
    private Menu about;
   
    @FXML
    private Text contactName;
    
    @FXML
    ListView<String> list = new ListView<>();
    
    @FXML
    private Text contactNumber;
    
    @FXML
    private Text noContacts;
   
  
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
    @FXML
    public void updateAddressBook()
    {
        
        ObservableList<String> items = FXCollections.observableArrayList (
        scene2Controller.list);
        
        list.setItems(items);
    }
    
    public void ready(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }
    
    @FXML
    public void goToNewContact(ActionEvent event) {
        if (scene2 == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("NewContact.fxml"));
                Parent root = loader.load();
                scene2Controller = loader.getController();
                scene2 = new Scene(root);
                stage.setScene(scene2);
                scene2Controller.ready(stage);
                scene2Controller.setScene1Info(scene, this);
                return;
            } catch (Exception ex) {
                System.out.println("Unable to load Scene2. Error: " + ex);
                return;
            }
            
        }
        
        stage.setScene(scene2);
    }
    
    @FXML
    private void handleAbout(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("About Zan");
        alert.setContentText("This application was developed by Zandria Johnson for CS3330 "
                + "at the University of Missouri. "
                + "This application is an address book. "
                + "It allows you to add your name to my contact list. "
                + "Just click 'Create New Contact' to get started!");
        
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        alert.getDialogPane().setExpandableContent(expContent);
        
        alert.showAndWait();
    }
    
    @Override
    public String getName() {
        return scene2Controller.name;
    }

   @Override
    public String getNumber() {
        return scene2Controller.number;
    }
}
