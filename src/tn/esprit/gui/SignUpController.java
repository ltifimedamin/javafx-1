/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.entities.User;
import tn.esprit.entities.UserRole;
import tn.esprit.services.ServiceUser;
/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField firstNameField;
    @FXML
    private PasswordField passwordSignupField;
    @FXML
    private Button signUpButton;
    @FXML
    private Hyperlink loginHyperlink;
    @FXML
    private PasswordField confirmPasswordSignupField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameSignupField;
    @FXML
    private TextField telephoneField;
    @FXML
    private TextField addressField;
    @FXML
    private ComboBox roleComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList <String> list = FXCollections.observableArrayList("CLIENT","EXPERT");
         roleComboBox.setItems(list);
          // Gestion du clic sur le lien "Already registered? Log in now"
        loginHyperlink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Code pour charger la page de connexion (login.fxml)
                loadLoginPage();
            }
        });
         
    }    
     @FXML
    void select(ActionEvent event) {
        String s =  roleComboBox.getSelectionModel().getSelectedItem().toString();
        
    }   

    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
         String username = usernameSignupField.getText();
        String password = passwordSignupField.getText();
        String confirmPassword = confirmPasswordSignupField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String telephone = telephoneField.getText();
        String address = addressField.getText();
        String role = roleComboBox.getValue().toString(); // Récupérer la valeur sélectionnée dans le ComboBox
        
 // Vérifier si les mots de passe correspondent
        if (!password.equals(confirmPassword)) {
            showAlert("Erreur d'inscription", "Les mots de passe ne correspondent pas.");
            return;
        }
         if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
        firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || role == null ) {
        showAlert("Erreur d'inscription", "Tous les champs obligatoires doivent être renseignés.");
        return;
    }
         if (!isValidEmail(email)) {
        showAlert("Erreur d'inscription", "L'adresse e-mail n'est pas valide.");
        return;
    }
        UserRole type = "CLIENT".equals(roleComboBox.getValue()) ? UserRole.CLIENT : UserRole.EXPERT;

        // Créer un nouvel utilisateur
         // Créer un nouvel utilisateur avec les données du formulaire
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setTel(telephone);
        user.setAddress(address);
        user.setRole(type);

        // Appeler la méthode d'ajout
        ServiceUser userService = new ServiceUser();
        userService.ajouter(user);

        // Affichez un message de confirmation
        showAlert("Inscription réussie", "Votre compte a été créé avec succès.");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void loadLoginPage() {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    private boolean isValidEmail(String email) {
    // Vous pouvez utiliser une expression régulière (regex) pour valider l'adresse e-mail.
    // Voici un exemple simple de regex pour une adresse e-mail :
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

    return email.matches(emailRegex);
}
}
    

