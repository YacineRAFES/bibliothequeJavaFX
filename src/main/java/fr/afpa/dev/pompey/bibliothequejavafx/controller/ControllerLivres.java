package fr.afpa.dev.pompey.bibliothequejavafx.controller;

import fr.afpa.dev.pompey.bibliothequejavafx.exception.SaisieException;
import fr.afpa.dev.pompey.bibliothequejavafx.model.ListeModele.Abonnees;
import fr.afpa.dev.pompey.bibliothequejavafx.model.ListeModele.Livres;
import fr.afpa.dev.pompey.bibliothequejavafx.model.Listes;
import fr.afpa.dev.pompey.bibliothequejavafx.model.Utilitaires.AffichageFenetre;
import fr.afpa.dev.pompey.bibliothequejavafx.model.Utilitaires.Input;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLivres implements Initializable {
    @FXML
    private TableView<Livres> LivresTable;

    @FXML
    private TableColumn<Livres, String> actionsLivresColumn;

    @FXML
    private Button annulerButtonLivres;

    @FXML
    private TableColumn<Livres, String> auteurLivresColumn;

    @FXML
    private TextField auteurTextFieldLivres;

    @FXML
    private AnchorPane livresView;

    @FXML
    private TableColumn<Livres, String> quantiteLivresColumn;

    @FXML
    private TextField quantiteTextFieldLivres;

    @FXML
    private TextField searchTextFieldLivres;

    @FXML
    private TableColumn<Livres, String> titreLivresColumn;

    @FXML
    private TextField titreTextFieldLivres;

    @FXML
    private Button validerButtonLivres;

    private ObservableList<Livres> livresListe;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titreLivresColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        auteurLivresColumn.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        quantiteLivresColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        Callback<TableColumn<Livres, String>, TableCell<Livres, String>> cellFactory
                =
                new Callback<>() {
                    @Override
                    public TableCell call(final TableColumn<Livres, String> param) {
                        return new TableCell<Livres, String>() {

                            final Button btn = new Button("Supprimer");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Livres livres = getTableView().getItems().get(getIndex());
                                        livresListe.remove(livres);
                                        Listes.removeLivre(livres);
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                    }
                };

        actionsLivresColumn.setCellFactory(cellFactory);
        livresListe = FXCollections.observableArrayList(Listes.getLivres());

        LivresTable.setItems(livresListe);
        addListeners();
    }

    private void addListeners() {
        validerButtonLivres.setOnAction(event -> {
            try {
                enregistrerLivres();
            } catch (SaisieException e) {
                new RuntimeException(e);
            }
        });
        annulerButtonLivres.setOnAction(event -> {
            try {
                annulerLivres();
            } catch (SaisieException e) {
                new RuntimeException(e);
            }
        });
    }



    private void enregistrerLivres() throws SaisieException {
        String titre = titreTextFieldLivres.getText();
        String auteur = auteurTextFieldLivres.getText();
        String quantite = quantiteTextFieldLivres.getText();
        if (titre.isEmpty() || auteur.isEmpty() || quantite.isEmpty()) {
            AffichageFenetre.Fenetre(
                    "Saisie manquant",
                    null,
                    "Les champs manquants doivent être saisis");
            throw new SaisieException("erreur de saisie");
        }

        Livres livres = new Livres(
                Input.getNomPrenom(titre, "titre"),
                Input.getNomPrenom(auteur, "auteur"),
                Input.getInt(quantite));

        Listes.addLivre(livres);

        livresListe.setAll(Listes.getLivres());

        AffichageFenetre.Fenetre(
                "L'enregistrement du livre",
                null,
                "Livre enregistré avec succès");

        titreTextFieldLivres.clear();
        auteurTextFieldLivres.clear();
        quantiteTextFieldLivres.clear();
    }

    private void annulerLivres() throws SaisieException {
        titreTextFieldLivres.clear();
        auteurTextFieldLivres.clear();
        quantiteTextFieldLivres.clear();
    }
}


