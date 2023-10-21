/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import tn.esprit.entities.Evennement;
import tn.esprit.services.Eventservice;
import tn.esprit.services.Participationservices;

public class BarchartsController implements Initializable {

    @FXML
    private BarChart<String, Number> barcharts;

    @FXML
    private CategoryAxis CategoryAxis;

    @FXML
    private NumberAxis NumberAxis;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Récupérez les données depuis votre base de données
        Participationservices participationService = new Participationservices();
        Eventservice eventService = new Eventservice();

        // Créez une série de données pour les événements et le nombre de participants
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Nombre de Participants");

        // Récupérez la liste des événements
        for (Evennement event : eventService.getAll()) {
            // Pour chaque événement, récupérez le nombre de participants
            int nombreParticipants = participationService.getNombreParticipants(event.getId());
            // Ajoutez les données à la série
            series.getData().add(new XYChart.Data<>(event.getTitre(), nombreParticipants));
        }

        // Ajoutez la série de données au graphique
        barcharts.getData().add(series);
    }
}