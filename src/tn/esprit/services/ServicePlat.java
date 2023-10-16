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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.CategorieP;
import tn.esprit.entities.Commande;
import tn.esprit.entities.Plat;
import tn.esprit.entities.TypeC;

/**
 *
 * @author Med-Amine
 */
public class ServicePlat implements IservicePlat<Plat>{
    
    
      private static ServicePlat instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public ServicePlat() {
        connection = tn.esprit.utils.Datasource.getInstance().getCnx();    

}
     public static ServicePlat getInstance() {
        if (instance == null) {
            instance = new ServicePlat();
        }
        return instance;
     }

    @Override
    public void ajouter(Plat plt) throws SQLException {
        String req = "insert into plat(nom,description,image,prix,categorie) values(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(req);   
         ps.setString(1, plt.getNom());
         ps.setString(2, plt.getDescription());
         ps.setString(3, plt.getImage());
         ps.setFloat(4,plt.getPrix());
        ps.setString(5,plt.getCategorie().toString());
        ps.executeUpdate();    }

    @Override
    public void modifier(Plat plt) throws SQLException {
      String req = "update plat set nom = ?, description = ?, image = ?, prix = ?, categorie = ? where idplat = ?";
        PreparedStatement ps = connection.prepareStatement(req);
         ps.setString(1, plt.getNom());
         ps.setString(2, plt.getDescription());
         ps.setString(3, plt.getImage());
         ps.setFloat(4,plt.getPrix());
         ps.setString(5,plt.getCategorie().toString());
         ps.setInt(6,plt.getIdplat());


       
         ps.executeUpdate();

    }
    

    @Override
    public void supprimer(int i) throws SQLException {
    String req = "DELETE FROM plat WHERE idplat = ?";
    PreparedStatement ps = connection.prepareStatement(req);
    ps.setInt(1, i);
    
    ps.executeUpdate();
    }

    @Override
    public List<Plat> recuperer() throws SQLException {        
        List<Plat> plats = new ArrayList<>();
        String req = "select * from plat";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
        Plat plt = new Plat();
        plt.setIdplat(rs.getInt("idplat"));
        plt.setNom( rs.getString("nom"));
        plt.setDescription( rs.getString("description"));        
        plt.setImage( rs.getString("image"));
        plt.setPrix(rs.getFloat("prix"));
        plt.setCategorie(CategorieP.valueOf(rs.getString("categorie")));


        plats.add(plt);
        }
        return plats;
    }
    
    
  
}