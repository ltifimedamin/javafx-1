/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.utils.UserData;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class CodeConfirmationController implements Initializable {

    @FXML
    private TextField codeField;
    @FXML
    private Button submitButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

    @FXML
    private void verifyCode(ActionEvent event) throws IOException {
        String enteredCode = codeField.getText().trim(); 
          UserData userData = UserData.getInstance();
        if (enteredCode.isEmpty()) { 
            showAlert("Warning", "Code is required", "Please fill the required field.");
        } else if (!enteredCode.equals(userData.getResetCode())) { 
            showAlert("Warning", "Invalid code", "Please enter a valid Code.");
        } else { 
            loadChangePasswordInterface(event);
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void loadChangePasswordInterface(ActionEvent event) throws IOException  {
        Parent changePasswordParent = FXMLLoader.load(getClass().getResource("ChangedPassword.fxml"));
        Scene changePasswordScene = new Scene(changePasswordParent, 600, 500);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(changePasswordScene);
        window.show();
    }

    
}
