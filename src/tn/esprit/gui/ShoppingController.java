/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import tn.esprit.entities.Achat;
import tn.esprit.entities.CategorieP;
import tn.esprit.entities.Plat;
import tn.esprit.entities.TypeC;
import tn.esprit.entities.User;
import tn.esprit.entities.dto.Item;
import tn.esprit.services.ServiceAchat;
import tn.esprit.services.ServicePlat;

/**
 * FXML Controller class
 *
 * @author Med-Amine
 */
public class ShoppingController implements Initializable {

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
    private TableColumn<Plat,Integer> plat_idview;
    @FXML
    private TableColumn<Plat, Integer> plat_idView;
    @FXML
    private TextField nomplt;
    @FXML
    private Button AjouterAuPanier;
    @FXML
    private Label Nbr_Plat_Au_panier;
    @FXML
    private TextField quantity_;
    @FXML
    private ComboBox<TypeC> typeCmdbox;
    @FXML
    private ImageView panierImg;

    /**
     * Initializes the controller class.
     */
    
    List<Achat> achatsUser= new ArrayList<Achat>();
    TableView<Item> tableView = new TableView<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
  

TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

TableColumn<Item, Double> priceColumn = new TableColumn<>("Price");
priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


tableView.getColumns().addAll(nameColumn, priceColumn);
        

        
       typeCmdbox.getItems().setAll(TypeC.values());
       
    panierImg.setOnMouseClicked(event -> {
        System.out.println("List des Achats :" + achatsUser);
        // This code will be executed when the image is clicked
       Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("plats Items");
        dialog.getDialogPane().setContent(tableView);
        Label totalLabel = new Label("Total Price: $"+ 200);//getTotalePannier
        dialog.getDialogPane().getButtonTypes().add(ButtonType.FINISH);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
    // Handle the OK button action
    // You can get the selected items from the TableView if needed
        }
        System.out.println("Mouse clicked on the image");
    });
        
        try {
            // TODO
            PlatTable();
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    int myIndex;    

private int compteur = 0; // Variable pour stocker la valeur actuelle


@FXML
private void AjouterAuPanier(ActionEvent event) throws SQLException {
     
      if(!nomplt.getText().isEmpty()){
       myIndex = pltTV.getSelectionModel().getSelectedIndex();
       int idplat = Integer.parseInt(String.valueOf(pltTV.getItems().get(myIndex).getIdplat()));
       float prix = pltTV.getItems().get(myIndex).getPrix();

       Plat plat = new Plat();
       plat.setIdplat(idplat);
       plat.setPrix(prix);
       User user = new User();
       user.setIduser(24);
       Achat achatPourAjouter =new Achat(user,plat,0,Integer.parseInt(quantity_.getText()),new Date(System.currentTimeMillis()),typeCmdbox.getValue());
       achatsUser.add(achatPourAjouter);
       
       //read indice from nbr du pannier
      int indice_element= Integer.parseInt(Nbr_Plat_Au_panier.getText()) ; 
       Item item1 = new Item(nomplt.getText(), prix);

  
    increment_Panier_Nbr_Plat();
     
    tableView.getItems().addAll(item1);
    }

   
    
}



 public void  increment_Panier_Nbr_Plat(){
    // Incrémente le compteur
    compteur++;
    // Met à jour la Label avec la nouvelle valeur
    Nbr_Plat_Au_panier.setText(Integer.toString(compteur));
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
            
     

     nomplt.setText(pltTV.getItems().get(myIndex).getNom());       
        }
     });
        return myRow;
    });
    
    }
        
        public float getTotalePanier(List<Achat> achatsUser){
            float Total = 0.0f;

        for (Achat A : achatsUser) {
        Plat plat = A.getPlat(); 
        if (plat != null) {
            Total += plat.getPrix(); 
        }
    }
    return Total;    
                }
}
