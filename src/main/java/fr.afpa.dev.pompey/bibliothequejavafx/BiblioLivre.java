package fr.afpa.dev.pompey.bibliothequejavafx;

import fr.afpa.dev.pompey.bibliothequejavafx.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class BiblioLivre extends Application {
    @Override
    public void start(Stage stage){
        Model.getInstance().getViewFactory().showClientWindow();
    }
}