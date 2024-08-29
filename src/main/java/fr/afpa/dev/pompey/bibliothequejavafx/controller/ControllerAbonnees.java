package fr.afpa.dev.pompey.bibliothequejavafx.controller;

import fr.afpa.dev.pompey.bibliothequejavafx.exception.SaisieException;
import fr.afpa.dev.pompey.bibliothequejavafx.model.ListeModele.Abonnees;
import fr.afpa.dev.pompey.bibliothequejavafx.model.Listes;
import fr.afpa.dev.pompey.bibliothequejavafx.model.Utilitaires.AffichageFenetre;
import fr.afpa.dev.pompey.bibliothequejavafx.model.Utilitaires.Input;
import fr.afpa.dev.pompey.bibliothequejavafx.model.Utilitaires.filterTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerAbonnees implements Initializable {

    @FXML
    private TableView<Abonnees> TableAbonnees;

    @FXML
    private TableColumn<Abonnees, String> actionsAbonneesColumn;

    @FXML
    private Button annulerButtonAbonnees;

    @FXML
    private TableColumn<Abonnees, LocalDate> dateAbonneesColumn;

    @FXML
    private TableColumn<Abonnees, String> emailAbonneesColumn;

    @FXML
    private TextField emailTextFieldAbonnees;

    @FXML
    private TableColumn<Abonnees, String> nomAbonneesColumn;

    @FXML
    private TextField nomTextFieldAbonnees;

    @FXML
    private TableColumn<Abonnees, String> prenomAbonneesColumn;

    @FXML
    private TextField prenomTextFieldAbonnees;

    @FXML
    private TextField searchTextFieldAbonnees;

    @FXML
    private Button validerButtonAbonnees;

    private ObservableList<Abonnees> abonneesListe;

    public void initialize(URL location, ResourceBundle resources) {

        nomAbonneesColumn.setCellValueFactory(new PropertyValueFactory<>("nom")); //<- entre les parenthèses doivent correspondre à la classe Abonnees
        prenomAbonneesColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailAbonneesColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateAbonneesColumn.setCellValueFactory(new PropertyValueFactory<>("dateInscription"));
        Callback<TableColumn<Abonnees, String>, TableCell<Abonnees, String>> cellFactory
                =
                new Callback<>() {
                    @Override
                    public TableCell call(final TableColumn<Abonnees, String> param) {
                        return new TableCell<Abonnees, String>() {

                            final Button btn = new Button("Supprimer");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Abonnees abonnees = getTableView().getItems().get(getIndex());
                                        abonneesListe.remove(abonnees);
                                        Listes.removeAbonnes(abonnees);
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                    }
                };

        actionsAbonneesColumn.setCellFactory(cellFactory);

        abonneesListe = FXCollections.observableArrayList(Listes.getAbonnes());

        TableAbonnees.setItems(abonneesListe);
        abonneesListe.add(new Abonnees("Dupont", "Jean", "jean.dupont@example.com", LocalDate.now()));
        abonneesListe.add(new Abonnees("Martin", "Marie", "marie.martin@example.com", LocalDate.now()));


        new filterTable<>(searchTextFieldAbonnees, TableAbonnees);

        addListeners();

    }

    private void addListeners() {
        validerButtonAbonnees.setOnAction(event -> {
            try {
                enregistrerAbonnees();
            } catch (SaisieException e) {
                new RuntimeException(e);
            }
        });
        annulerButtonAbonnees.setOnAction(event -> {
            try {
                annulerAbonnees();
            } catch (SaisieException e) {
                new RuntimeException(e);
            }
        });
    }

    private void enregistrerAbonnees() throws SaisieException {
        String nom = nomTextFieldAbonnees.getText();
        String prenom = prenomTextFieldAbonnees.getText();
        String email = emailTextFieldAbonnees.getText();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty()) {
            AffichageFenetre.Fenetre(
                    "Saisie manquant",
                    null,
                    "Les champs manquants doivent être saisis");
            throw new SaisieException("erreur de saisie");
        }

        for (Abonnees uniqueEmail : Listes.getAbonnes()) {
            if (uniqueEmail.getEmail().equalsIgnoreCase(email)) {
                emailTextFieldAbonnees.setText("");
                AffichageFenetre.Fenetre(
                        "Email existant",
                        null,
                        "L'email existe déjà, il doit être unique");
                throw new SaisieException("erreur de saisie");
            }
        }

        Abonnees abonnees = new Abonnees(
                Input.getNomPrenom(nom, "Nom"),
                Input.getNomPrenom(prenom, "Prénom"),
                Input.getEmail(email),
                Input.CreateDateNow());

        Listes.addAbonnes(abonnees);

        abonneesListe.setAll(Listes.getAbonnes());

        // La fenêtre de succès est affichée seulement après la réussite de toutes les étapes précédentes
        AffichageFenetre.Fenetre(
                "L'enregistrement succès",
                null,
                "Abonné enregistré avec succès");

        nomTextFieldAbonnees.clear();
        prenomTextFieldAbonnees.clear();
        emailTextFieldAbonnees.clear();
    }


    private void annulerAbonnees() throws SaisieException{
        nomTextFieldAbonnees.clear();
        prenomTextFieldAbonnees.clear();
        emailTextFieldAbonnees.clear();
    }
}
