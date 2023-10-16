/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import tn.esprit.entities.CategorieP;
import tn.esprit.entities.Plat;
import tn.esprit.services.ServicePlat;

/**
 * FXML Controller class
 *
 * @author Med-Amine
 */
public class AjouterPlatController implements Initializable {

    @FXML
    private TableView<Plat> pltTV;
    @FXML
    private TableColumn<Plat, String> nomview;
    @FXML
    private TableColumn<Plat, String> descriptionview;
    @FXML
    private TableColumn<Plat, String> imageview;
    @FXML
    private TableColumn<Plat, Float> prixview;
    @FXML
    private TableColumn<Plat, CategorieP> categorieview;
    @FXML
    private TextField Rechercheplt;
    @FXML
    private TextField nomplt;
    @FXML
    private TextField descplt;
    @FXML
    private Button imgplt;
    @FXML
    private Button btajtplt;
    @FXML
    private Button btmdfplt;
    @FXML
    private Button btsupplt;
    @FXML
    private ComboBox<CategorieP> catgbox;
    @FXML
    private Button rtrplt;
    @FXML
    private TextField prixplt;

    @FXML
    private TableColumn<Plat, Integer> plat_idView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        catgbox.getItems().setAll(CategorieP.values());
        try {
            PlatTable();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterPlatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    int myIndex;
    @FXML
    private void PlatAdd(ActionEvent event) throws SQLException {
     String nom = nomplt.getText();      
     String description = descplt.getText();        
     String prixText = prixplt.getText();
     float prix = Float.parseFloat(prixText); 
     CategorieP categorie=catgbox.getValue() ;
     Plat platPourAjouter =new Plat(  nom,  description,  "taw nrak7ouha ",  prix,  categorie);
        ServicePlat _servicePalt = new ServicePlat();
 
        _servicePalt.ajouter(platPourAjouter);
        PlatTable();
    
    }

    @FXML
    private void PlatUpdate(ActionEvent event) throws SQLException {
       myIndex = pltTV.getSelectionModel().getSelectedIndex();
       int idplat = Integer.parseInt(String.valueOf(pltTV.getItems().get(myIndex).getIdplat()));
        
     String nom = nomplt.getText();      
  String description = descplt.getText();        
   String prixText = prixplt.getText();
    float prix = Float.parseFloat(prixText); 
 CategorieP categorie=catgbox.getValue() ;
 Plat platPourUpdate =new Plat(  idplat,nom,  description,  "taw nrak7ouha ",  prix,  categorie);
        ServicePlat _servicePalt = new ServicePlat();
 
        _servicePalt.modifier(platPourUpdate);
        PlatTable();

    }

    @FXML
    private void PlatDeleted(ActionEvent event) throws SQLException {
        
       myIndex = pltTV.getSelectionModel().getSelectedIndex();
       int idplat = Integer.parseInt(String.valueOf(pltTV.getItems().get(myIndex).getIdplat()));
       ServicePlat _servicePalt = new ServicePlat();
       _servicePalt.supprimer(idplat);
       PlatTable();
       
        
    }
    
    public void PlatTable() throws SQLException{
     ServicePlat ServiceEvent= new ServicePlat();
     ArrayList<Plat> challengess = new ArrayList<>();
              
    challengess=  (ArrayList<Plat>) ServiceEvent.recuperer();
              
  
    ObservableList<Plat> obsl = FXCollections.observableArrayList(challengess); 
  
    pltTV.setItems(obsl);
    categorieview.setCellValueFactory(new  PropertyValueFactory<>("categorie")); 
    prixview.setCellValueFactory(new  PropertyValueFactory<>("prix"));
    descriptionview.setCellValueFactory(new  PropertyValueFactory<>("description"));
    nomview.setCellValueFactory(new  PropertyValueFactory<>("nom"));
    plat_idView.setCellValueFactory(new  PropertyValueFactory<>("idplat")); 


 pltTV.setRowFactory( tv -> {
     TableRow<Plat> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
           myIndex =  pltTV.getSelectionModel().getSelectedIndex();
            
     
    prixplt.setText(Float.toString(pltTV.getItems().get(myIndex).getPrix()));
     nomplt.setText(pltTV.getItems().get(myIndex).getNom());

    descplt.setText(pltTV.getItems().get(myIndex).getDescription());
  
    catgbox.setValue(pltTV.getItems().get(myIndex).getCategorie());

        
           
           

    
       
             
        }
     });
        return myRow;
                   });
    
    }


    
}
    
    

