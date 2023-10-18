/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.entities.User;
import tn.esprit.services.IServiceUser;
import tn.esprit.services.ServiceUser;
import tn.esprit.utils.Session;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ProfilController implements Initializable {

    @FXML
    private TextField firstNameProfilField;
    @FXML
    private PasswordField newPasswordProfilField;
    @FXML
    private Button editProfilSaveButton;
    @FXML
    private TextField EmailProfilField;
    @FXML
    private TextField lastNameProfilField;
    @FXML
    private TextField AdressProfilField;
    @FXML
    private TextField usernameProfilField;
    @FXML
    private TextField NumberProfilField;
    @FXML
    private PasswordField ConfirmPasswordProfilField;
    @FXML
    private PasswordField oldPasswordProfilField;
    
    
    
    private ServiceUser userService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceUser userService = new ServiceUser();
         User currentUser = userService.getById(Session.getInstance().getCurrentUser().getIduser() );
        // Set the user data in the fields
        firstNameProfilField.setText(currentUser.getFirstName());
        lastNameProfilField.setText(currentUser.getLastName());
        usernameProfilField.setText(currentUser.getUsername());
        EmailProfilField.setText(currentUser.getEmail());
        NumberProfilField.setText(currentUser.getTel() );
        AdressProfilField.setText(currentUser.getAddress());
        // Set other fields as needed
    }    

    
    // Add a method to set user data
   
    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
    // Get the user input
    String newFirstName = firstNameProfilField.getText();
    String newUserName = usernameProfilField.getText();

    String newLastName = lastNameProfilField.getText();
    String newEmail = EmailProfilField.getText();
    String newTel = NumberProfilField.getText();
    String newAddress = AdressProfilField.getText();
    String oldPassword = oldPasswordProfilField.getText();
    String newPassword = newPasswordProfilField.getText();
    String confirmPassword = ConfirmPasswordProfilField.getText();
    ServiceUser userService = new ServiceUser();
         User currentUser = userService.getById(Session.getInstance().getCurrentUser().getIduser() );
    // Check if the old password matches the user's current password
    // Check if the old password matches the current password
       if (oldPassword.equals(currentUser.getPassword()) || oldPassword.isEmpty()) {
    // The old password matches or is not provided, proceed with updating user data
        
    // Update the user object with the new data
    

    currentUser.setFirstName(newFirstName);
    currentUser.setLastName(newLastName);
   currentUser.setUsername(newUserName);
    currentUser.setEmail(newEmail);
    currentUser.setTel(newTel);
    currentUser.setAddress(newAddress);
     // Check if a new password is provided
     
    if (!oldPassword.isEmpty()) {
        // Check if the new password and confirm password match
        if (newPassword.equals(confirmPassword)) {
          //   Set the new password
            currentUser.setPassword(newPassword);
        } else {
            showAlert("Error", "New password and confirm password do not match.");
            
        }
    }
    
    // Save the updated user to the database using the modifier method of serviceUser
    userService.modifier(currentUser);

    // Display a success alert to the user
    showAlert("Success", "User data updated successfully.");
 
    }
       else {
            showAlert("Error", "old password incorrect");
            
        }
    }
// A helper method to display alerts
       private void showAlert(String title, String content) {
       Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle(title);
               alert.setHeaderText(null);
           alert.setContentText(content);
            alert.showAndWait();
}
}
