/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import static java.lang.Integer.parseInt;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entities.Evennement;
import tn.esprit.entities.Participant;
import tn.esprit.entities.User;
import tn.esprit.services.Eventservice;
import tn.esprit.services.Participationservices;

/**
 * FXML Controller class
 *
 * @author Med Iheb
 */
public class ParticpeEventController implements Initializable {

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
    private TableColumn<Evennement, String> lieu_eventView;
    @FXML
    private TableColumn<Evennement,Integer > id_eventView1;
    @FXML
    private Button participerEventBT;
    @FXML
    private Button annulerParticipationBT;
    @FXML
    private TextField NumeroParticipant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        EventTable();
    }    
    int myIndex;

    @FXML
    private void participer_Event(ActionEvent event) {
       myIndex = EventTable.getSelectionModel().getSelectedIndex();
       int idEvent = Integer.parseInt(String.valueOf(EventTable.getItems().get(myIndex).getId()));
       Evennement selectedEvent= new Evennement();
       selectedEvent.setId(idEvent);
       User userConnecter = new User(); 
       userConnecter.setIduser(1);
        int NumeroTel= parseInt(NumeroParticipant.getText());
       Participant particperAEvent=new Participant(LocalDate.now(), NumeroTel, userConnecter ,selectedEvent);
        Participationservices participation=new Participationservices();
        participation.ajouter(particperAEvent);
        
        
    }

    @FXML
    private void annulerParticipation(ActionEvent event) {
        
          myIndex =   EventTable.getSelectionModel().getSelectedIndex();
         //int numero = Integer.parseInt(String.valueOf( EventTable.getItems().get(myIndex).));
        User userConnecter = new User(); 
       userConnecter.setIduser(1);

          Participationservices participation=new Participationservices();
     
          participation.supprimerMonParticipation(userConnecter.getIduser(),Integer.parseInt(String.valueOf( EventTable.getItems().get(myIndex).getId())));
              
        
    }
    
    
    
    
          public void EventTable(){
          Eventservice ServiceEvent= new Eventservice();
     ArrayList<Evennement> challengess = new ArrayList<>();
              
                     challengess=  (ArrayList<Evennement>) ServiceEvent.getAll();
              
  
    ObservableList<Evennement> obsl = FXCollections.observableArrayList(challengess); 
  
    EventTable.setItems(obsl);
    id_eventView1.setCellValueFactory(new  PropertyValueFactory<>("id")); 
    titre_eventView.setCellValueFactory(new  PropertyValueFactory<>("titre"));
    date_eventView.setCellValueFactory(new  PropertyValueFactory<>("date"));
    description_eventView.setCellValueFactory(new  PropertyValueFactory<>("description"));
    adresse_eventView.setCellValueFactory(new  PropertyValueFactory<>("adresse")); 
    lieu_eventView.setCellValueFactory(new  PropertyValueFactory<>("lieu"));

 EventTable.setRowFactory( tv -> {
     TableRow<Evennement> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
            myIndex = EventTable.getSelectionModel().getSelectedIndex();
            

         }
        
            
            
            
        
     });
        return myRow;
                   });
    
    }
       int generateNumberTicket() {
        Random rand = new Random();
        int min = 10000;  
        int max = 99999;  
        return rand.nextInt((max - min) + 1) + min;
    }
}
