package fr.afpa.dev.pompey.bibliothequejavafx.controller;

import fr.afpa.dev.pompey.bibliothequejavafx.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerClient implements Initializable {

    public BorderPane client_parent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case "Accueil" -> client_parent.setCenter(Model.getInstance().getViewFactory().getAccueilView());
                case "Abonnees" -> client_parent.setCenter(Model.getInstance().getViewFactory().getAbonneesView());
                case "Livres" -> client_parent.setCenter(Model.getInstance().getViewFactory().getLivresView());
                case "Prets" -> client_parent.setCenter(Model.getInstance().getViewFactory().getPretsView());
                default -> client_parent.setCenter(Model.getInstance().getViewFactory().getAccueilView());
            }
        });
    }
}
