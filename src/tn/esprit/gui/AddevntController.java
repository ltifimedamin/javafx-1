/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import tn.esprit.entities.Evennement;
import tn.esprit.services.Eventservice;

/**
 * FXML Controller class
 *
 * @author Med Iheb
 */
public class AddevntController implements Initializable {
    private Stage stage;
    private Stage scene;
    
    @FXML
    private TextField recTitre;
    @FXML
    private TextField recDiscription;
    @FXML
    private TextField recAdresse;
    @FXML
    private DatePicker recDate;
    @FXML
    private Button recImage;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField recChercher;
    @FXML
    private TableView<Evennement> EventTable;
    @FXML
    private TableColumn<Evennement, String> titre_eventView;
    
    @FXML
    private TableColumn<Evennement, LocalDate> date_eventView;
    @FXML
    private TableColumn<Evennement, String> description_eventView;
    @FXML
    private TableColumn<Evennement, String> adresse_eventView;
    @FXML
    private TextField recLieu;
    @FXML
    private TableColumn<Evennement, String> lieu_eventView;
    @FXML
    private TableColumn<Evennement,Integer > id_eventView1;
    
    Eventservice e = new Eventservice();

    /**
     * Initializes the controller class.
     */
    
    int    myIndex;
    @FXML
    private Button Participantajoouter;
    @FXML
    private TextField TXTimg;
    @FXML
    private ImageView lbl_image;
    @FXML
    private Button chercher;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        EventTable();
    }    
 
 
    @FXML
    private void ADDimage(ActionEvent event) {
        
          JFileChooser  chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        TXTimg.setText(filename);
        String path= TXTimg.getText();
        System.out.println("PATH :"  +path);
       

    
    
             Image imn = new Image(
              "file:/" +path );
            lbl_image.setImage(imn);
//       // System.out.println("file:/" + eq.getImage_eq());
        
        
        
        
    }
 
    @FXML
    private void addevent(ActionEvent event) {
      if (isInputValid()) {  
     String titre;
     LocalDate date;
     String  description;
     String adresse;    
     String img;   
     String lieu ;
    titre =recTitre.getText();
    description= recDiscription.getText();
    adresse= recAdresse.getText();
    date= recDate.getValue();
    lieu =recLieu.getText();
    img =TXTimg.getText();
 
    Evennement evennementPourAjouter = new  Evennement( titre, date,  description, img,  adresse, lieu);
    Eventservice Serviceevent = new Eventservice();

    Serviceevent.ajouter(evennementPourAjouter);
     showAlert("AJOUTER AVEC SUCCES", "AJOUTER AVEC SUCCES");
    EventTable();
       }  
    }

    @FXML
    private void Modifierevent(ActionEvent event) {
         if (isInputValid()) { 
        myIndex = EventTable.getSelectionModel().getSelectedIndex();
       int idEvent = Integer.parseInt(String.valueOf(EventTable.getItems().get(myIndex).getId()));
        
     String titre;
     LocalDate date;
     String  description;
     String adresse;    
     String img;   
     String lieu ;
    titre =recTitre.getText();
    description= recDiscription.getText();
    adresse= recAdresse.getText();
    date= recDate.getValue();
    lieu =recLieu.getText(); 
    img =TXTimg.getText();

    Evennement evennementPourUpdate = new  Evennement( idEvent,titre, date,  description, img,  adresse, lieu);
    Eventservice Serviceevent = new Eventservice();
    Serviceevent.modifier(evennementPourUpdate);
     showAlert("MODIFIER AVEC SUCCES", "MODIFIER AVEC SUCCES");
    EventTable();
         } 
    }

    @FXML
    private void supprimerevent(ActionEvent event) {
         myIndex = EventTable.getSelectionModel().getSelectedIndex();
       int idEvent = Integer.parseInt(String.valueOf(EventTable.getItems().get(myIndex).getId()));
       Evennement evennementPoursupprimer = new  Evennement( idEvent,"", LocalDate.now(),  "", "",  "", "");
        Eventservice Serviceevent = new Eventservice();
        Serviceevent.supprimer(idEvent);
        EventTable();
        
    }

    @FXML
    private void displayDateD(ActionEvent event) {
    }
    
      
      public void EventTable(){
          Eventservice ServiceEvent= new Eventservice();
     ArrayList<Evennement> challengess = new ArrayList<>();
              
           challengess=  (ArrayList<Evennement>) ServiceEvent.getAll();
              
  
    ObservableList<Evennement> obsl = FXCollections.observableArrayList(challengess); 
  
    EventTable.setItems(obsl);
    
    titre_eventView.setCellValueFactory(new  PropertyValueFactory<>("titre"));
    date_eventView.setCellValueFactory(new  PropertyValueFactory<>("date")); 
    description_eventView.setCellValueFactory(new  PropertyValueFactory<>("description"));
     id_eventView1.setCellValueFactory(new  PropertyValueFactory<>("id"));
    adresse_eventView.setCellValueFactory(new  PropertyValueFactory<>("adresse"));
    lieu_eventView.setCellValueFactory(new  PropertyValueFactory<>("lieu"));
    EventTable.setRowFactory( tv -> {
     TableRow<Evennement> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
           myIndex =  EventTable.getSelectionModel().getSelectedIndex();
            
     
    recTitre.setText(EventTable.getItems().get(myIndex).getTitre());
     recDiscription.setText(EventTable.getItems().get(myIndex).getDescription());

    recAdresse.setText(EventTable.getItems().get(myIndex).getAdresse());
  
    recDate.setValue(EventTable.getItems().get(myIndex).getDate());  
    recLieu.setText(EventTable.getItems().get(myIndex).getLieu());

         }
        
            
            
            
        
     });
        return myRow;
                   });
    
    }

    @FXML
    private void addParticipant(ActionEvent event) {
       
       
 try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("addparticipant.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
           
       
    }
    
     ObservableList<Evennement> eventsData = FXCollections.observableArrayList();
    @FXML
    private void chercher(ActionEvent event) throws SQLException {
        List<Evennement> events = null;
        String recherche = recChercher.getText();
         
            events = recupererBytitre(recherche);
         
              if (events != null && !events.isEmpty()) {
            eventsData.setAll(events);
            EventTable.setItems(eventsData);
        } else {
            afficherAlerte("Aucun résultat", "Aucun résultat trouvé pour la recherche.", Alert.AlertType.INFORMATION);
        }
        }
    
    private void afficherAlerte(String titre, String contenu, Alert.AlertType type) {
    Alert alert = new Alert(type);
    alert.setTitle(titre);
    alert.setHeaderText(contenu);
    alert.showAndWait();
}
    
    
        private List<Evennement> recupererBytitre(String titre) throws SQLException {
        return e.recupererBytitre(titre);
    }

    private boolean isInputValid() {
          if (recTitre.getText().isEmpty() || recDiscription.getText().isEmpty() || 
        recDate.getValue() == null || recAdresse.getText().isEmpty() || 
        TXTimg.getText().isEmpty() || recLieu.getText().isEmpty()) {
       
        showAlert("Champs vides", "Tous les champs doivent être remplis.");
        return false;
    }
    
   
    
    return true;
    }

    private void showAlert(String champs_vides, String tous_les_champs_doivent_être_remplis) {
       Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle( champs_vides);
    alert.setHeaderText(null);
    alert.setContentText(tous_les_champs_doivent_être_remplis);
    alert.showAndWait();
    }
    }
    


    
    

