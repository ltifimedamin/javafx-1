/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import tn.esprit.entities.Evennement;
import tn.esprit.services.Eventservice;
import tn.esprit.services.IserviceEvenement;

/**
 * FXML Controller class
 *
 * @author Med Iheb
 */
public class ImgWithEventController implements Initializable {

   
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane NumeroParticipantT;

    /**
     * Initializes the controller class.
     */
    
    public static String num;
    
    @FXML
    static TextField NumerParticipant;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Eventservice sc = new Eventservice();
        // TODO
              List<Evennement> eqs = sc.getAll();
        try {
            afficherEquipementsMag(eqs);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }    

    @FXML
    private void chercherProduitsMag(KeyEvent event) {
    }

    @FXML
    private void clearSelection(MouseEvent event) {
    }

    @FXML
    private void trierCat(ActionEvent event) {
    }

    @FXML
    private void switchToItemInt(MouseEvent event) {
    }
    
        public void afficherEquipementsMag(List<Evennement> eqs) throws IOException {
            IserviceEvenement sc = new Eventservice();
        int column = 0;
        int row = 1;
        List<Evennement> listEvent = sc.getAll();
            System.out.println("List Event : "+listEvent);
        try{
        for (int i = 0; i < listEvent.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("itemEquipement.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            ItemEquipementController itemController = fxmlLoader.getController();
            
            itemController.setData(listEvent.get(i));

            if (column == 1) {
                column = 0;
                row++;
            }
            grid.add(anchorPane, column++, row); 
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);

            //set grid height
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);
            
            GridPane.setMargin(anchorPane, new Insets(10));
        }
            }catch(IOException ex){
                     ex.printStackTrace();
                    }
    }
    
}
