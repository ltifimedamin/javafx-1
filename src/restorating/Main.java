/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restorating;



import java.sql.Connection;

import java.time.LocalDate;
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


    
        Participationservices participation=new Participationservices();
      
  //eventservice.ajouter(evennement1);     
      //eventservice.supprimer(9);
     // System.out.println("Affichge : \n"+eventservice.getAll(evennement1));
   //  eventservice.modifier(evennementPourModifier);
       
     // participation.ajouter(p);
       //participation.modifier(participantModifier);
     // participation.supprimer(3);
      
      
      
    System.out.println("Resultas : " +participation.getAll());
     
     
             
     
    
    }
    
}
