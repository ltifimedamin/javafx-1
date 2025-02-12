/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JFileChooser;
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
    @FXML
    
    private ComboBox<String> typerech;
   // @FXML
   // private TableColumn<?, ?> plat_idview;
    @FXML
    private ImageView imgrech;
    @FXML
    private TableColumn<?, ?> plat_idview;
    @FXML
    private TextField TxtImg;
    @FXML
    private ImageView lbl_image;
    

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
            
         
       imgrech.setOnMouseClicked(event -> {
    try {
        rechercherPlats(new ActionEvent()); 
    } catch (SQLException e) {
        e.printStackTrace();
    }
});
       typerech.getItems().addAll("Nom", "Categorie", "Prix");

                
    }   
  
    int myIndex;
    
    
    @FXML
    private void PlatAdd(ActionEvent event) throws SQLException {
    String nom = nomplt.getText();
    String description = descplt.getText();
    String prixText = prixplt.getText();
    CategorieP categorie = catgbox.getValue();
    String img=TxtImg.getText();
    if (estChampValide(nom) && estChampValide(description) && estPrixValide(prixText) && categorie != null) {
        float prix = Float.parseFloat(prixText);
        Plat platPourAjouter = new Plat(nom, description, img, prix, categorie);
        ServicePlat _servicePlat = new ServicePlat();
        _servicePlat.ajouter(platPourAjouter);
        PlatTable();
        
        afficherAlerteSucces("Succès", "Plat ajouté avec succès !");
    } else {

        afficherAlerteErreur("Erreur de saisie", "Veuillez remplir tous les champs correctement.");
    }
}

private boolean estChampValide(String champ) {
    return !champ.isEmpty(); 
}

private boolean estPrixValide(String prixText) {
    try {
        float prix = Float.parseFloat(prixText);
        return prix > 0; 
    } catch (NumberFormatException e) {
        return false; 
    }
}

private void afficherAlerteErreur(String titre, String contenu) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(titre);
    alert.setHeaderText(contenu);
    alert.showAndWait();
}

private void afficherAlerteSucces(String titre, String contenu) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(titre);
    alert.setHeaderText(contenu);
    alert.showAndWait();
}

private boolean estEntierPositif(String str) {
    try {
        int valeur = Integer.parseInt(str);
        return valeur >= 0;
    } catch (NumberFormatException e) {
        return false;
    }
}

@FXML
private void PlatUpdate(ActionEvent event) throws SQLException {
    myIndex = pltTV.getSelectionModel().getSelectedIndex();
    
    if (myIndex >= 0) {
        int idplat = Integer.parseInt(String.valueOf(pltTV.getItems().get(myIndex).getIdplat()));
        
        String nom = nomplt.getText();
        String description = descplt.getText();
        String prixText = prixplt.getText();
        CategorieP categorie = catgbox.getValue();
        String img=TxtImg.getText();
        if (estChampValide(nom) && estChampValide(description) && estPrixValide(prixText) && categorie != null) {
            float prix = Float.parseFloat(prixText);
            Plat platPourUpdate = new Plat(idplat, nom, description, img, prix, categorie);
            ServicePlat _servicePlat = new ServicePlat();
            _servicePlat.modifier(platPourUpdate);
            PlatTable();
            afficherAlerte("Succès", "Plat modifié avec succès.", AlertType.INFORMATION);
        } else {
            afficherAlerte("Erreur de saisie", "Veuillez remplir tous les champs correctement.", AlertType.ERROR);
        }
    } else {
        afficherAlerte("Erreur de modification", "Veuillez sélectionner un plat à modifier.", AlertType.WARNING);
    }
}




@FXML
private void PlatDeleted(ActionEvent event) throws SQLException {
    myIndex = pltTV.getSelectionModel().getSelectedIndex();
    if (myIndex >= 0) {
        int idplat = Integer.parseInt(String.valueOf(pltTV.getItems().get(myIndex).getIdplat()));
        ServicePlat _servicePlat = new ServicePlat();
        _servicePlat.supprimer(idplat);
        PlatTable();
        afficherAlerte("Succès", "Plat supprimé avec succès.", AlertType.INFORMATION);
    } else {
        afficherAlerte("Erreur de suppression", "Veuillez sélectionner un plat à supprimer.", AlertType.WARNING);
    }
}

// Fonction pour afficher une alerte
private void afficherAlerte(String titre, String contenu, AlertType type) {
    Alert alert = new Alert(type);
    alert.setTitle(titre);
    alert.setHeaderText(contenu);
    alert.showAndWait();
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

 private ServicePlat servicePlat = new ServicePlat();
private ObservableList<Plat> platsData = FXCollections.observableArrayList();


    private void rechercherPlats(ActionEvent event) throws SQLException {
             List<Plat> plats = null;

        String typeRecherche = typerech.getValue();
        String recherche = Rechercheplt.getText();

        if (typeRecherche.equals("Nom")) {
            plats = recupererByNom(recherche);
        } else if (typeRecherche.equals("Categorie")) {
            CategorieP rechercheCategorie = CategorieP.valueOf(recherche);
            plats = recupererByCategorie(rechercheCategorie);
        } else if (typeRecherche.equals("Prix")) {
            try {
                float recherchePrix = Float.parseFloat(recherche);
                plats = recupererByPrix(recherchePrix);
            } catch (NumberFormatException e) {
                afficherAlerte("Erreur de recherche", "La valeur de recherche de prix n'est pas valide.", AlertType.ERROR);
                return;
            }
        }

        if (plats != null && !plats.isEmpty()) {
            platsData.setAll(plats);
            pltTV.setItems(platsData);
        } else {
            afficherAlerte("Aucun résultat", "Aucun résultat trouvé pour la recherche.", AlertType.INFORMATION);
        }
    }

    private List<Plat> recupererByNom(String nom) throws SQLException {
        return servicePlat.recupererByNom(nom);
    }

    private List<Plat> recupererByCategorie(CategorieP categorie) throws SQLException {
        return servicePlat.recupererByCategorie(categorie);
    }

    private List<Plat> recupererByPrix(float prix) throws SQLException {
        return servicePlat.recupererByPrix(prix);
    }

    @FXML
    private void AddImg(ActionEvent event) {
                  JFileChooser  chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        TxtImg.setText(filename);
        String path= TxtImg.getText();
        System.out.println("PATH :"  +path);
       // Image getAbsolutePath = null;
       // ImageIcon icon = new ImageIcon(filename);

    
    
             Image imn = new Image(
              "file:/" +path );
            lbl_image.setImage(imn);
//       // System.out.println("file:/" + eq.getImage_eq());
        
        
        
    }


}



    
    

