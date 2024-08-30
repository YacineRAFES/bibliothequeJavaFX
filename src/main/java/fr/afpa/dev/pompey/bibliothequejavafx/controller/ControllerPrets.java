package fr.afpa.dev.pompey.bibliothequejavafx.controller;

import fr.afpa.dev.pompey.bibliothequejavafx.exception.SaisieException;
import fr.afpa.dev.pompey.bibliothequejavafx.model.ListeModele.Abonnees;
import fr.afpa.dev.pompey.bibliothequejavafx.model.ListeModele.Livres;
import fr.afpa.dev.pompey.bibliothequejavafx.model.ListeModele.Prets;
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
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerPrets implements Initializable {

    @FXML
    private TableView<Prets> PretsTable;
    @FXML
    private TableColumn<Prets, String> abonneesColumnPrets;

    @FXML
    private ComboBox<Abonnees> abonnnesComboBoxPrets;

    @FXML
    private TableColumn<Prets, String> actionsColumnPrets;

    @FXML
    private Button annulerButtonPrets;

    @FXML
    private TableColumn<Prets, LocalDate> dateDebutColumnPrets;

    @FXML
    private TableColumn<Prets, LocalDate> dateFinColumnPrets;

    @FXML
    private TableColumn<Prets, String> livresColumnPrets;

    @FXML
    private ComboBox<Livres> livresComboBoxPrets;

    @FXML
    private AnchorPane pretsView;

    @FXML
    private TextField searchTextFieldPrets;

    @FXML
    private Button validerButtonPrets;

    private ObservableList<Prets> pretsListes;

    public ControllerPrets() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        abonnnesComboBoxPrets.getItems().addAll(Listes.getAbonnes());
        livresComboBoxPrets.getItems().addAll(Listes.getLivres());
        abonneesColumnPrets.setCellValueFactory(new PropertyValueFactory<>("abonnees"));
        livresColumnPrets.setCellValueFactory(new PropertyValueFactory<>("livres"));
        dateDebutColumnPrets.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dateFinColumnPrets.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        Callback<TableColumn<Prets, String>, TableCell<Prets, String>> cellFactory
                =
                new Callback<>() {
                    @Override
                    public TableCell call(final TableColumn<Prets, String> param) {
                        return new TableCell<Prets, String>() {

                            final Button btn = new Button("Supprimer");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Prets prets = getTableView().getItems().get(getIndex());
                                        Livres livre = prets.getLivres();
                                        pretsListes.remove(prets);
                                        Listes.removePret(prets);
                                        livre.setQuantite(livre.getQuantite() + 1);
                                        ControllerLivres.updateLivresTable();

                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                    }
                };
        actionsColumnPrets.setCellFactory(cellFactory);

        pretsListes = FXCollections.observableArrayList(Listes.getPret());


        PretsTable.setItems(pretsListes);
        addListeners();
    }

    private void addListeners() {
        validerButtonPrets.setOnAction(event -> {
            try {
                enregistrerPrets();
            } catch (SaisieException e) {
                new RuntimeException(e);
            }
        });
        annulerButtonPrets.setOnAction(event -> {
            try {
                annulerPrets();
            } catch (SaisieException e) {
                new RuntimeException(e);
            }
        });
    }


    private void enregistrerPrets() throws SaisieException {
        Object livres = livresComboBoxPrets.getValue();
        Object abonnees = abonnnesComboBoxPrets.getValue();

        if (livres == null || abonnees == null ) {
            AffichageFenetre.Fenetre("Saisie manquant", null, "Les champs de sélection sont vides");
            throw new SaisieException();
        }

        if (((Livres) livres).getQuantite() <= 0){
            AffichageFenetre.Fenetre("Livre non disponible",null, "Il n'y a plus d'exemplaires disponibles pour ce livre.");
            throw new SaisieException();
        }

        for(Prets pret : Listes.getPret()){
            if(pret.getAbonnees().equals(abonnees) && pret.getLivres().equals(livres)){
                AffichageFenetre.Fenetre("Utilisateur Prêt", null, "L'utilisateur a déjà un prêt sur ce livre");
                throw new SaisieException();
            }
        }

        Prets prets = new Prets(
                (Abonnees) abonnees,
                (Livres) livres,
                Input.CreateDateNow(),
                Input.CreateDateFin()
        );

        System.out.println("Quantité avant: " + ((Livres) livres).getQuantite());
        ((Livres) livres).setQuantite(((Livres) livres).getQuantite() - 1);
        System.out.println("Quantité après: " + ((Livres) livres).getQuantite());

        ControllerLivres.updateLivresTable();

        Listes.addPret(prets);

        pretsListes.setAll(Listes.getPret());

        AffichageFenetre.Fenetre("Prêts ajouté",null, "Le Pret a été ajouté");
    }

    private void annulerPrets() throws SaisieException {
        abonnnesComboBoxPrets.getItems().clear();
        livresComboBoxPrets.getItems().clear();
        abonnnesComboBoxPrets.getItems().addAll(Listes.getAbonnes());
        livresComboBoxPrets.getItems().addAll(Listes.getLivres());
    }


}
