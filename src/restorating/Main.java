/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restorating;



import java.sql.Connection;

import java.time.LocalDate;
import java.util.List;
import tn.esprit.entities.Evennement;
import tn.esprit.entities.Participant;
import tn.esprit.entities.User;
import tn.esprit.services.Eventservice;
import tn.esprit.services.Participationservices;
import tn.esprit.utils.Datasource;


/**
 *
 * @author remo
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
             
        // TODO code application logic here
        Connection cnx;
        cnx = Datasource.getInstance().getCnx();
           
        Evennement evennement1=new Evennement( 2,"titreupdated", LocalDate.now(),"iheb","C:/iheb/img/photo.png", "adresseUpdated", "appppp");
        Evennement evennementPourModifier=new Evennement( 4,"titreupdated", LocalDate.now(),"descriptionUpdate","C:/iheb/img/photo.png", "adresseUpdated", "TunisieUpdated");
Participationservices participation=new Participationservices();
               // Eventservice  evennement =new Eventservice();
    
       Eventservice eventservice = new Eventservice();
   
       // participation.supprimerMonParticipation(1,14);
         // Evennement eve=new Evennement( 3,"titreupdated", LocalDate.now(),"iheb","C:/iheb/img/photo.png", "adresseUpdated", "appppp");
      
       // List<Evennement> recupererBytitre;
       
       //for(Evennement ee :recupererBytitre  ){
         //   System.out.println("affiche :"+ee);
       // }
        
        // String titre="titreupdated";
        
    //System.out.println("Affichge : \n" +eventservice.recupererBytitre());
      
        
 // eventservice.ajouter(evennement1);     
      //eventservice.supprimer(9);
    // System.out.println("Affichge : \n"+eventservice.getAll(evennement1));
      //  System.out.println("afficher:\n"+eventservice.getAll());
    //eventservice.modifier(evennementPourModifier);
       
     // participation.ajouter(p);
       //participation.modifier(participantModifier);
     // participation.supprimer(3);
      
      
      
   // System.out.println("Resultas : " +participation.getAll());
     
     
             
     
    
    }
    
}
