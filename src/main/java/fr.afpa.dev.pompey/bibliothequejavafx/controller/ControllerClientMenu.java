package fr.afpa.dev.pompey.bibliothequejavafx.controller;

import fr.afpa.dev.pompey.bibliothequejavafx.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerClientMenu implements Initializable {

    @FXML
    public Button btnAccueil;

    @FXML
    public Button btnAbonnees;

    @FXML
    public Button btnLivres;

    @FXML
    public Button btnPrets;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners(){
        btnAccueil.setOnAction(event -> onAccueil());
        btnAbonnees.setOnAction(event -> onAbonnees());
        btnLivres.setOnAction(event -> onLivres());
        btnPrets.setOnAction(event -> onPrets());
    }

    private void onPrets() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Prets");
    }

    private void onLivres() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Livres");
    }

    private void onAccueil(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Accueil");
    }

    private void onAbonnees(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Abonnees");
    }
}
