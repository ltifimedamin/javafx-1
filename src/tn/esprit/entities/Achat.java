/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

/**
 *
 * @author Med-Amine
 */
public class Achat {
    private int idachat;
    private User user;
    private Commande cmnd;
    private Plat plat;
    private int quantite;

    public Achat() {
    }
   public Achat( User user, Commande cmnd, Plat plat, int quantite) {
        this.user = user;
        this.cmnd = cmnd;
        this.plat = plat;
        this.quantite = quantite;
    }
    public Achat(int idachat, User user, Commande cmnd, Plat plat, int quantite) {
        this.idachat = idachat;
        this.user = user;
        this.cmnd = cmnd;
        this.plat = plat;
        this.quantite = quantite;
    }

    public int getIdachat() {
        return idachat;
    }

    public void setIdachat(int idachat) {
        this.idachat = idachat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Commande getCmnd() {
        return cmnd;
    }

    public void setCmnd(Commande cmnd) {
        this.cmnd = cmnd;
    }

    public Plat getPlat() {
        return plat;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
        public void setIduser(int iduser) {
    }

    // Setter pour idcmnd
    public void setIdcmnd(int idcmnd) {
    }

    @Override
    public String toString() {
        return "Achat{" + "idachat=" + idachat + ", user=" + user + ", cmnd=" + cmnd + ", plat=" + plat + ", quantite=" + quantite + '}';
    }

    
}
