/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entities.Achat;
import tn.esprit.entities.CategorieP;
import tn.esprit.entities.Plat;
import tn.esprit.entities.TypeC;
import tn.esprit.entities.User;
import tn.esprit.services.ServiceAchat;
import tn.esprit.services.ServicePlat;

/**
 * FXML Controller class
 *
 * @author Med-Amine
 */
public class AchatFXMLController implements Initializable {

    @FXML
    private TableView<Achat> achatTV;
    @FXML
    private TableColumn<Achat, User> clientview;
    @FXML
    private TableColumn<Achat, Plat> platview;
    @FXML
    private TableColumn<Achat, Integer> quantiteview;
    @FXML
    private TableColumn<Achat, Float> Mtotalview;
    @FXML
    private TableColumn<Achat, Date> dateview;
  //  @FXML
  //  private TableColumn<Achat, ?> plat_idview;
    //@FXML
  //  private TableColumn<Achat, ?> plat_idView;
    @FXML
    private TableColumn<Achat, TypeC> typeview;
    @FXML
    private TextField Rechercheachat;
    @FXML
    private TextField nomclientacht;
    @FXML
    private TextField pltach;
    @FXML
    private Button btajtach;
    @FXML
    private Button btmdfach;
    @FXML
    private Button btsupach;
    @FXML
    private ComboBox<TypeC> typeachbox;
    @FXML
    private Button rtrach;
    @FXML
    private TextField mntnach;
    @FXML
    private TextField quntach;
    @FXML
    private DatePicker dateach;
    @FXML
    private TableColumn<?, ?> plat_idview;
    @FXML
    private TableColumn<?, ?> plat_idView;
  
   // @FXML
    //private TableColumn<?, ?> plat_idview;
    //@FXML
   // private TableColumn<?, ?> plat_idView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
                typeachbox.getItems().setAll(TypeC.values());
     
        try {
            AchatTable();
        } catch (SQLException ex) {
            Logger.getLogger(AchatFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        
  
        
    }    
    int myIndex;

    private void AchatTable() throws SQLException{
   ServiceAchat ServiceEvent= new ServiceAchat();
     ArrayList<Achat> challengess = new ArrayList<>();
              
    challengess=  (ArrayList<Achat>) ServiceEvent.recuperer();
              
  
    ObservableList<Achat> obsl = FXCollections.observableArrayList(challengess); 
  
    achatTV.setItems(obsl);
    clientview.setCellValueFactory(new  PropertyValueFactory<>("user")); 
    platview.setCellValueFactory(new  PropertyValueFactory<>("plat"));
    Mtotalview.setCellValueFactory(new  PropertyValueFactory<>("montanttotal"));
    quantiteview.setCellValueFactory(new  PropertyValueFactory<>("quantite"));
    dateview.setCellValueFactory(new  PropertyValueFactory<>("date"));
    typeview.setCellValueFactory(new  PropertyValueFactory<>("typec")); 



  achatTV.setRowFactory( tv -> {
     TableRow<Achat> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
           myIndex =  achatTV.getSelectionModel().getSelectedIndex();
            
     nomclientacht.setText(achatTV.getItems().get(myIndex).getUser().getFirstName());
     pltach.setText(achatTV.getItems().get(myIndex).getPlat().getNom());
     mntnach.setText(Float.toString(achatTV.getItems().get(myIndex).getMontanttotal()));
     quntach.setText(Integer.toString(achatTV.getItems().get(myIndex).getQuantite()));
      dateach.setValue(LocalDate.now());

//dateach.setValue(achatTV.getItems().get(myIndex).getDate().toLocalDate());   
     typeachbox.setValue(achatTV.getItems().get(myIndex).getTypec());
     
     
        }
     });
        return myRow;
                   });
    
    
    
    }

    @FXML
    private void AchatAdd(ActionEvent event) throws SQLException {
        
     User user = new User();
    user.setIduser(24); 
    user.setUsername("hazem");
    Plat plat = new Plat();
    plat.setIdplat(13);
    String MontantText = mntnach.getText();
    float montanttotal = Float.parseFloat(MontantText); 
    String quantiteText = quntach.getText();
    int quantite = Integer.parseInt(quantiteText);
    
    Date date = Date.valueOf(LocalDate.now());
    TypeC typec=typeachbox.getValue();
    
     Achat achatPourAjouter =new Achat(user,plat,montanttotal,quantite,date,typec);
 
        ServiceAchat _serviceAchat = new ServiceAchat();
 
        _serviceAchat.ajouter(achatPourAjouter);
        AchatTable();
    }

    @FXML
    private void AchatUpdate(ActionEvent event) throws SQLException {
        
        myIndex = achatTV.getSelectionModel().getSelectedIndex();
 
    
    int idachat=Integer.parseInt(String.valueOf(achatTV.getItems().get(myIndex).getIdachat()));
    User user = new User();
    user.setIduser(24); 
    user.setUsername("hazem");
    Plat plat = new Plat();
    plat.setIdplat(13);
  
    String MontantText = mntnach.getText();
    float montanttotal = Float.parseFloat(MontantText); 
    String quantiteText = quntach.getText();
    int quantite = Integer.parseInt(quantiteText);
    
    Date date = Date.valueOf(LocalDate.now());
    TypeC typec=typeachbox.getValue();
 
 
 
 Achat achatPourUpdate =new Achat(idachat,user,plat,montanttotal,quantite,date,typec);
 
        ServiceAchat _serviceAchat = new ServiceAchat();
 
        _serviceAchat.modifier(achatPourUpdate);
        AchatTable();
    }


    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void AchatDelete(ActionEvent event) throws SQLException {
                
     myIndex = achatTV.getSelectionModel().getSelectedIndex();
    int idachat = Integer.parseInt(String.valueOf(achatTV.getItems().get(myIndex).getIdachat()));
    ServiceAchat _serviceAchat = new ServiceAchat();
    _serviceAchat.supprimer(idachat); 
    AchatTable();
       
    }

}




    

