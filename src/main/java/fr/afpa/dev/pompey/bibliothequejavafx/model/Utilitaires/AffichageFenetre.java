package fr.afpa.dev.pompey.bibliothequejavafx.model.Utilitaires;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AffichageFenetre {

    public static void Fenetre(String titreMessage, String enteteMessage, String contexteMessage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titreMessage);
        alert.setHeaderText(enteteMessage);
        alert.setContentText(contexteMessage);

        alert.showAndWait();
    }
}
