/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;
import com.google.zxing.Binarizer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Med Iheb
 */
public class main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
       //barcharts 
      //addevnt
      //ParticpeEvent
      //ImgWithEvent
       Parent root = FXMLLoader.load(getClass().getResource("addevnt.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
            
    }
    
      
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
