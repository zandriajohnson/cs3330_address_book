/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zj5fffinal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class NewContactController implements Initializable {
  
    private Stage stage;
    
    private Scene scene1;
    private AddressBookController scene1Controller;
    
     
    @FXML
    public TextField firstName;
    
    @FXML
    public TextField lastName;
    
    @FXML
    public TextField phoneNumber;
    
    @FXML
    private ImageView contactPhoto;
    
   // private Image image;
    @FXML 
    private Label fileSelected;
    
    public String firstN;
    public String lastN;
    public String number;
    public String name;
    ArrayList<String> list = new ArrayList<>();
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
    public NewContactController(String FirstN, String LastN, String Number, String Name)
    {
        this.firstN = FirstN;
        this.lastN = LastN;
        this.number = Number;
        this.name = Name;
    }
    
    public NewContactController(){
    }
    
    
    public void ready(Stage stage) {
        this.stage = stage;
    }
    
    public void setScene1Info(Scene scene1, AddressBookController scene1Controller) {
        this.scene1 = scene1;
        this.scene1Controller = scene1Controller;
    }
    
    @FXML
    public void handleCancel(ActionEvent event) 
    {
        if (scene1 != null) {
            stage.setScene(scene1);
        }
    }
    
    @FXML
    public void handleUploadPhoto(ActionEvent event) throws IOException
    {
     
      FileChooser fileChooser = new FileChooser();
      FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
      fileChooser.getExtensionFilters().add(extFilter);
      
      File imageFile = fileChooser.showOpenDialog(null);
   
    }
       

    
    @FXML
    public void handleSave(ActionEvent event)
    {
      
        firstN = firstName.getText();
        lastN = lastName.getText();
        number = phoneNumber.getText();
        name = firstN + "  " + lastN + "\t" + number;
        
        list.add(name);
        scene1Controller.updateAddressBook();
        
        if (scene1 != null) 
        {   
            stage.setScene(scene1); 
            firstName.setText("");
            lastName.setText("");
            phoneNumber.setText("");
        }
    }
    
   @FXML
   public void handleSavetoFile(ActionEvent event)
   {
       FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try
            {
               FileOutputStream fileOut = new FileOutputStream(file.getPath());
               ObjectOutputStream out = new ObjectOutputStream(fileOut);
               out.writeObject(name);
               out.close();
               fileOut.close();
            }catch(IOException ioex)
            {
                String message = "Exception occurred while saving to " + file.getPath();
                displayExceptionAlert(message, ioex);
            } 
        }        
   }
   
 private void displayExceptionAlert(String message, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Exception!");
        alert.setContentText(message);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }
}
