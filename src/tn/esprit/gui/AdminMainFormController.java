/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.services.ServiceUser;
import tn.esprit.entities.User;
import tn.esprit.entities.UserRole;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AdminMainFormController implements Initializable {

    @FXML
    private Label greet_username;
    @FXML
    private Button btnDash;
    @FXML
    private Button btnUser;
    @FXML
    private Button add_buton_dash;
    @FXML
    private Button update_button_dash;
    @FXML
    private Button clea_button_dash;
    @FXML
    private Button delete_button_dash;
    @FXML
    private TextField firstNameFielddasha;
    @FXML
    private TextField lastNameFielddash;
    @FXML
    private TextField usernamedashfield;
    @FXML
    private TextField emailDashField;
    @FXML
    private TextField passwordDashField;
    @FXML
    private TextField numberDashField;
    @FXML
    private TextField confirPasswordDashField;
    @FXML
    private TextField AddressDashField;
    @FXML
    private ComboBox  roleDashComboBox;
    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<?, ?> userIdColumn;
    @FXML
    private TableColumn<?, ?> firstNameColumn;
    @FXML
    private TableColumn<?, ?> lastNameColumn;
    @FXML
    private TableColumn<?, ?> userNameColumn;
    @FXML
    private TableColumn<?, ?> emailColumn;
    @FXML
    private TableColumn<?, ?> passwordColumn;
    @FXML
    private TableColumn<?, ?> NumberColumn;
    @FXML
    private TableColumn<?, ?> addressColumn;
    @FXML
    private TableColumn<?, ?> roleColumn;

    /**
     * Initializes the controller class.
     */
     private ServiceUser userService;
    private int selectedUserIndex = -1;
    @FXML
    
    public void loadUserData() {
    ServiceUser serviceUser = new ServiceUser();
    List<User> users = serviceUser.afficherTous();
    ObservableList<User> userObservableList = FXCollections.observableArrayList(users);
    userTableView.setItems(userObservableList);
}
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialisez ici les autres éléments si nécessaire
         ObservableList <String> list = FXCollections.observableArrayList("CLIENT", "EXPERT", "ADMIN");
         roleDashComboBox.setItems(list);
         delete_button_dash.setOnAction(event -> deleteSelectedUser());
        userService = new ServiceUser();

        // Configurez les CellValueFactory pour chaque colonne en utilisant PropertyValueFactory
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        NumberColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        // Chargez les données des utilisateurs dans la TableView
        loadUserData();
          // Add a listener to handle user selection in the TableView
        userTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Update the text fields with the selected user's data
                User selectedUser = newValue;
                firstNameFielddasha.setText(selectedUser.getFirstName());
                lastNameFielddash.setText(selectedUser.getLastName());
                usernamedashfield.setText(selectedUser.getUsername());
                emailDashField.setText(selectedUser.getEmail());
                passwordDashField.setText(selectedUser.getPassword());
                numberDashField.setText(selectedUser.getTel());
                AddressDashField.setText(selectedUser.getAddress());
                roleDashComboBox.setValue(selectedUser.getRole());
                selectedUserIndex = userTableView.getSelectionModel().getSelectedIndex();
                 }
        });
     }
     @FXML
    void selected(ActionEvent event) {
        String s =  roleDashComboBox.getSelectionModel().getSelectedItem().toString();
        
    }   

    
    
    @FXML
    private void addUser(ActionEvent event) {
         String username = usernamedashfield.getText();
        String password = passwordDashField.getText();
        String confirmPassword = confirPasswordDashField.getText();
        String firstName = firstNameFielddasha.getText();
        String lastName = lastNameFielddash.getText();
        String email = emailDashField.getText();
        String telephone = numberDashField.getText();
        String address = AddressDashField.getText();
        String role = roleDashComboBox.getValue().toString() ; // Récupérer la valeur sélectionnée dans le ComboBox
        
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
                  UserRole type;

                        if ("CLIENT".equals(roleDashComboBox.getValue())) {
                                         type = UserRole.CLIENT;
                                } else if ("EXPERT".equals(roleDashComboBox.getValue())) {
                                             type = UserRole.EXPERT;
                                } else {
                                             type = UserRole.ADMIN;
}      

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
        loadUserData();
    }
    @FXML
    private void updateUser(ActionEvent event) {
        UserRole type;

                        if ("CLIENT".equals(roleDashComboBox.getValue())) {
                                         type = UserRole.CLIENT;
                                } else if ("EXPERT".equals(roleDashComboBox.getValue())) {
                                             type = UserRole.EXPERT;
                                } else {
                                             type = UserRole.ADMIN;
}      
        // Handle the event when the Update button is clicked
        if (selectedUserIndex >= 0) {
            // Get the selected user from the TableView
            User selectedUser = userTableView.getSelectionModel().getSelectedItem();

            // Update the selected user's data with the data from text fields and ComboBox
            selectedUser.setUsername(usernamedashfield.getText());
            selectedUser.setEmail(emailDashField.getText());
            selectedUser.setPassword(passwordDashField.getText());
            selectedUser.setFirstName(firstNameFielddasha.getText());
            selectedUser.setLastName(lastNameFielddash.getText());
            selectedUser.setTel(numberDashField.getText());
            selectedUser.setAddress(AddressDashField.getText());
            selectedUser.setRole(type);



            // Call the service to update the user
            userService.modifier(selectedUser);

            // Reload the user data in the TableView
            loadUserData();
            // Show a success alert
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Success");
        successAlert.setHeaderText("User Updated Successfully");
        successAlert.setContentText("The user has been updated successfully.");
        successAlert.showAndWait();
        } else {
            // Show an alert that no user is selected for updating
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No User Selected");
            alert.setContentText("Please select a user to update.");
            alert.showAndWait();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private boolean isValidEmail(String email) {
    // Vous pouvez utiliser une expression régulière (regex) pour valider l'adresse e-mail.
    // Voici un exemple simple de regex pour une adresse e-mail :
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

    return email.matches(emailRegex);
}
    public void deleteSelectedUser() {
    User selectedUser = userTableView.getSelectionModel().getSelectedItem();

    if (selectedUser != null) {
        // Remove the selected user from the TableView
        userTableView.getItems().remove(selectedUser);

        // Delete the user from the database
        userService.supprimer(selectedUser.getIduser()); // Use the ID to delete the user
    }
    }
   @FXML
private void clearFields(ActionEvent event) {
    firstNameFielddasha.clear();
    lastNameFielddash.clear();
    usernamedashfield.clear();
    emailDashField.clear();
    passwordDashField.clear();
    numberDashField.clear();
    confirPasswordDashField.clear();
    AddressDashField.clear();
    roleDashComboBox.getSelectionModel().clearSelection(); // Clear the selected item in the ComboBox
}

}

/*
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList <String> list = FXCollections.observableArrayList("CLIENT","EXPERT");
         roleDashComboBox.setItems(list);
        userService = new ServiceUser();
        configureTableView();

        userTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                User selectedUser = userTableView.getSelectionModel().getSelectedItem();
                displayUserDetails(selectedUser);
                selectedUserIndex = userTableView.getSelectionModel().getSelectedIndex();
            }
        });

        loadUserData();
    }

    private void configureTableView() {
        // Configurez les CellValueFactory pour chaque colonne
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        NumberColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    private void displayUserDetails(User user) {
        if (user != null) {
            firstNameFielddasha.setText(user.getFirstName());
            lastNameFielddash.setText(user.getLastName());
            usernamedashfield.setText(user.getUsername());
            emailDashField.setText(user.getEmail());
            passwordDashField.setText(user.getPassword());
            numberDashField.setText(user.getTel());
            AddressDashField.setText(user.getAddress());
            roleDashComboBox.setValue(user.getRole().toString());
        } else {
            clearFields();
        }
    }

    private void clearFields() {
        firstNameFielddasha.clear();
        lastNameFielddash.clear();
        usernamedashfield.clear();
        emailDashField.clear();
        passwordDashField.clear();
        numberDashField.clear();
        AddressDashField.clear();
        roleDashComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void addOrUpdateUser(ActionEvent event) {
        String username = usernamedashfield.getText();
        String password = passwordDashField.getText();
        String firstName = firstNameFielddasha.getText();
        String lastName = lastNameFielddash.getText();
        String email = emailDashField.getText();
        String tel = numberDashField.getText();
        String address = AddressDashField.getText();
        String role = roleDashComboBox.getValue();

        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() ||
            email.isEmpty() || tel.isEmpty() || address.isEmpty() || role == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez remplir tous les champs.");
            return;
        }

        UserRole userRole = UserRole.valueOf(role);
        User newUser = new User(username, email, password, firstName, lastName, tel, address, userRole);

        if (selectedUserIndex == -1) {
            userService.ajouter(newUser);
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Succès", "Utilisateur ajouté avec succès.");
        } else {
            int userId = userTableView.getItems().get(selectedUserIndex).getIduser();
            newUser.setIduser(userId);
            userService.modifier(newUser);
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Succès", "Utilisateur mis à jour avec succès.");
        }

        clearFields();
        loadUserData();
    }

    @FXML
    private void deleteUser(ActionEvent event) {
        if (selectedUserIndex == -1) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Erreur", "Sélectionnez un utilisateur à supprimer.");
            return;
        }

        int userId = userTableView.getItems().get(selectedUserIndex).getIduser();
        userService.supprimer(userId);
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Succès", "Utilisateur supprimé avec succès.");
        clearFields();
        loadUserData();
    }

    private void loadUserData() {
        List<User> users = userService.afficher
                }
    @FXML
    private void selectcombo(ActionEvent event) {
        String s =  roleDashComboBox.getSelectionModel().getSelectedItem().toString();
    }
    }
    
}
*/