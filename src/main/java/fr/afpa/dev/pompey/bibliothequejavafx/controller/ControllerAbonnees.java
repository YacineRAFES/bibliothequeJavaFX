package fr.afpa.dev.pompey.bibliothequejavafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAbonnees implements Initializable {

    @FXML
    private TextField nomTextFieldAbonnees;
    @FXML
    private TextField prenomTextFieldAbonnees;
    @FXML
    private TextField emailTextFieldAbonnees;
    @FXML
    private Button validerButtonAbonnees;
    @FXML
    private Button annulerButtonAbonnees;

    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
    }

    private void addListeners() {
        validerButtonAbonnees.setOnAction(event -> enregistrerAbonnees());
        annulerButtonAbonnees.setOnAction(event -> annulerAbonnees());
    }

    private void enregistrerAbonnees() {
        String nom = nomTextFieldAbonnees.getText();
        String prenom = prenomTextFieldAbonnees.getText();
        String email = emailTextFieldAbonnees.getText();
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty()) {

        }

    }

    private void annulerAbonnees() {
        nomTextFieldAbonnees.clear();
        prenomTextFieldAbonnees.clear();
        emailTextFieldAbonnees.clear();
    }

    public ControllerAbonnees(AnchorPane abonneesView) {

    }


}
