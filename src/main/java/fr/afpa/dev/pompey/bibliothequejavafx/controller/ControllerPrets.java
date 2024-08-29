package fr.afpa.dev.pompey.bibliothequejavafx.controller;

import fr.afpa.dev.pompey.bibliothequejavafx.model.ListeModele.Livres;
import fr.afpa.dev.pompey.bibliothequejavafx.model.ListeModele.Prets;
import fr.afpa.dev.pompey.bibliothequejavafx.model.Listes;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPrets implements Initializable {

    @FXML
    private TableColumn<Prets, String> abonneesColumnPrets;

    @FXML
    private ComboBox<?> abonnnesComboBoxPrets;

    @FXML
    private TableColumn<?, ?> actionsColumnPrets;

    @FXML
    private Button annulerButtonPrets;

    @FXML
    private TableColumn<?, ?> dateDebutColumnPrets;

    @FXML
    private TableColumn<?, ?> dateFinColumnPrets;

    @FXML
    private TableColumn<?, ?> livresColumnPrets;

    @FXML
    private ComboBox<?> livresComboBoxPrets;

    @FXML
    private AnchorPane pretsView;

    @FXML
    private TextField searchTextFieldPrets;

    @FXML
    private Button validerButtonPrets;

    public ControllerPrets() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titreLivresColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        auteurLivresColumn.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        quantiteLivresColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        Callback<TableColumn<Livres, String>, TableCell<Livres, String>> cellFactory
                =
                new Callback<>() {
                    @Override
                    public TableCell call(final TableColumn<Prets, String> param) {
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
}
