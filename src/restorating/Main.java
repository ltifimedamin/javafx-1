/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restorating;



import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import tn.esprit.entities.Achat;
import tn.esprit.entities.CategorieP;

import tn.esprit.entities.Commande;
import tn.esprit.entities.Plat;
import tn.esprit.entities.TypeC;
import tn.esprit.entities.User;
import tn.esprit.services.ServiceAchat;
import tn.esprit.services.ServiceCommande;
import tn.esprit.services.ServicePlat;
import tn.esprit.utils.Datasource;

/**
 *
 * @author remo
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
            
        Connection cnx;
                cnx = Datasource.getInstance().getCnx();
            ServiceAchat sa = new ServiceAchat();

     /* List<Plat> list = pl.recuperer();
      for(int i=0;i<list.size();i++){
      System.out.println(list.get(i));*/
      
     // pl.supprimer(12);
          //System.out.println("platsupprimer");
      
      //Plat plat =new Plat(1, "amine", "brh", "ok",22, CategorieP.Berger);
      //pl.ajouter(plat);*/

        ServiceCommande sc = ServiceCommande.getInstance();
         ServicePlat p = ServicePlat.getInstance();
         //Plat pp = new Plat("jhjh","dddf","ddf",333f,CategorieP.Berger);
        // p.ajouter(pp);
         Plat ppp = new Plat(13,"hazem","brahmi","ddf",700f,CategorieP.Berger);
         //p.modifier(ppp);
        /*List<Commande> list = sc.recuperer();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }*/
         //sc.supprimer(1);
         //Commande cmd = new Commande(222f,TypeC.livraison);
          Commande cmdd = new Commande(3,555f,TypeC.livraison);
          User u = new User(1);
         //sc.ajouter(cmd);
         //sc.modifier(cmdd);
       //ServiceAchat ac = ServiceAchat.getInstance();
      /* List<Achat> achats = new ArrayList<>();
       achats = sa.recuperer();
       for(int i=0;i<achats.size();i++){
            System.out.println(achats.get(i));
        }*/
     //Achat a = new Achat(21, u, cmdd, ppp, 55);
           Achat aa = new Achat(u, cmdd, ppp, 2);
            sa.ajouter(aa);
           //sa.modifier(a);
          //sa.supprimer(21);
      }} 
    
    

