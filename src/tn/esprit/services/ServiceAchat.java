/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.Achat;
import tn.esprit.entities.CategorieP;
import tn.esprit.entities.Commande;
import tn.esprit.entities.Plat;
import tn.esprit.entities.Role;
import tn.esprit.entities.TypeC;
import tn.esprit.entities.User;

/**
 *
 * @author Med-Amine
 */
public class ServiceAchat implements IServiceAchat<Achat>{
    
    private static ServiceAchat instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public ServiceAchat() {
        connection = tn.esprit.utils.Datasource.getInstance().getCnx();    

}
     public static ServiceAchat getInstance() {
        if (instance == null) {
            instance = new ServiceAchat();
        }
        return instance;
     }
    
    

    @Override
    public void ajouter(Achat A) throws SQLException {
          String request = "INSERT INTO `achat`(`iduser`, `idcmnd`, `idplat`, `quantite`) VALUES(?, ?, ?, ?)";
    try {
        preparedStatement = connection.prepareStatement(request);

        preparedStatement.setInt(1, A.getUser().getIduser());
        preparedStatement.setInt(2, A.getCmnd().getIdcmnd());
        preparedStatement.setInt(3, A.getPlat().getIdplat());
        preparedStatement.setInt(4, A.getQuantite());

        int rowsInserted = preparedStatement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Achat ajouter avec succes");
        } else {
            System.out.println("Achat insertion failed");
        }
    } catch (SQLException exception) {
        System.out.println("Error (ajouter) Achat : " + exception.getMessage());
        throw exception; 
    }
    }

    @Override
    public void modifier(Achat A) throws SQLException {
        String request = "UPDATE `achat` SET `iduser` = ?, `idcmnd` = ?, `idplat` = ?, `quantite` = ? WHERE `idachat` = ?";
    try {
        preparedStatement = connection.prepareStatement(request);

        preparedStatement.setInt(1, A.getUser().getIduser());
        preparedStatement.setInt(2, A.getCmnd().getIdcmnd());
        preparedStatement.setInt(3, A.getPlat().getIdplat());
        preparedStatement.setInt(4, A.getQuantite());
        preparedStatement.setInt(5, A.getIdachat());

        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Achat updated avec succès");
        } else {
            System.out.println("Achat update failed");
        }
    } catch (SQLException exception) {
        System.out.println("Error (modifier) Achat : " + exception.getMessage());
        throw exception;
    }
    }

    @Override
    public void supprimer(int i) throws SQLException {
     String req = "DELETE FROM achat WHERE idachat = ?";
    PreparedStatement ps = connection.prepareStatement(req);
    ps.setInt(1, i);
    
    ps.executeUpdate();
    }

    @Override
    public List<Achat> recuperer() throws SQLException {
    List<Achat> listAchat = new ArrayList<>();
    try {
        preparedStatement = connection.prepareStatement("" +
                "SELECT * FROM `achat` AS a " +
                "INNER JOIN `commande` AS c ON a.idcmnd = c.idcmnd " +
                "INNER JOIN `plat` AS p ON a.idplat = p.idplat " +
                "INNER JOIN `user` AS u ON a.iduser = u.iduser");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Achat achat = new Achat();
            achat.setIdachat(resultSet.getInt("idachat"));
            achat.setQuantite(resultSet.getInt("quantite"));

            Commande commande = new Commande();
            commande.setIdcmnd(resultSet.getInt("idcmnd"));
            commande.setMontanttotal(resultSet.getFloat("montanttotal"));
            commande.setTypec(TypeC.valueOf(resultSet.getString("type")));           
            Plat plt = new Plat();
            plt.setIdplat(resultSet.getInt("idplat"));
            plt.setNom(resultSet.getString("nom"));
            plt.setDescription(resultSet.getString("description"));
            plt.setImage(resultSet.getString("image"));
            plt.setPrix(resultSet.getFloat("prix"));
            plt.setCategorie(CategorieP.valueOf(resultSet.getString("categorie")));

            User user = new User();
            user.setIduser(resultSet.getInt("iduser"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            user.setTel(resultSet.getString("tel"));
            user.setRole(Role.valueOf(resultSet.getString("role")));


            achat.setCmnd(commande);
            achat.setPlat(plt);
            achat.setUser(user);

            listAchat.add(achat);
        }
    } catch (SQLException exception) {
        System.out.println("Error (recuperer) Achat : " + exception.getMessage());
    } finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
    return listAchat;
}}

   

    

