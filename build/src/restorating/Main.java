/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restorating;



import tn.esprit.entities.User;
import tn.esprit.entities.UserRole;
import tn.esprit.services.ServiceUser;
import tn.esprit.services.IServiceUser;
import tn.esprit.utils.Datasource;

import java.sql.Connection;
import java.util.List;

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
 IServiceUser userService = new ServiceUser();

        // Ajouter un nouvel utilisateur
        User newUser = new User("yamen","yamen@test.com","123456","Yamen","khefacha","55530947","",UserRole.ADMIN);
        
        userService.ajouter(newUser);
        System.out.println("Utilisateur ajouté : " + newUser); 
        
   List<User> users = userService.afficherTous();
System.out.println("Liste de tous les utilisateurs :");
for (User user : users) {
    System.out.println("ID: " + user.getIduser());
    System.out.println("Nom d'utilisateur: " + user.getUsername());
    System.out.println("Email: " + user.getEmail());
    System.out.println("Mot de passe: " + user.getPassword());
    System.out.println("Prénom: " + user.getFirstName());
    System.out.println("Nom: " + user.getLastName());
    System.out.println("Téléphone: " + user.getTel());
    System.out.println("Adresse: " + user.getAddress());
    System.out.println("Rôle: " + user.getRole());
    // Vous pouvez afficher d'autres informations si nécessaire
    System.out.println();
}


        // Mettre à jour un utilisateur (par exemple, changer le rôle)
        User userToUpdate = userService.getById(3); // Remplacez 1 par l'ID de l'utilisateur à mettre à jour
        if (userToUpdate != null) {
            userToUpdate.setRole(UserRole.ADMIN);
            userService.modifier(userToUpdate);
            System.out.println("Utilisateur mis à jour : " + userToUpdate);
        }

        // Supprimer un utilisateur (par exemple, supprimer l'utilisateur avec l'ID 2)
        int userIdToDelete = 1; // Remplacez 2 par l'ID de l'utilisateur à supprimer
        userService.supprimer(1);
        System.out.println("Utilisateur supprimé avec l'ID : " + userIdToDelete);
    }
}