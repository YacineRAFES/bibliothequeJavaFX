package fr.afpa.dev.pompey.bibliothequejavafx.vue;

import fr.afpa.dev.pompey.bibliothequejavafx.controller.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    private final StringProperty clientSelectedMenuItem;
    private AnchorPane accueilView;
    private AnchorPane abonneesView;
    private AnchorPane livresView;
    private AnchorPane pretsView;


    public ViewFactory() {
        this.clientSelectedMenuItem = new SimpleStringProperty("");
    }

    public StringProperty getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    public AnchorPane getAccueilView() {
        if(accueilView == null) {
            try{
                accueilView = new FXMLLoader(getClass().getResource("/fr/afpa/dev/pompey/bibliothequejavafx/Accueil.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return accueilView;
    }

    public AnchorPane getAbonneesView() {
        if(abonneesView == null) {
            try{
                abonneesView = new FXMLLoader(getClass().getResource("/fr/afpa/dev/pompey/bibliothequejavafx/Abonnees.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return abonneesView;
    }

    public AnchorPane getLivresView() {
        if(livresView == null) {
            try{
                livresView = new FXMLLoader(getClass().getResource("/fr/afpa/dev/pompey/bibliothequejavafx/Livres.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return livresView;
    }

    public AnchorPane getPretsView() {
        if(pretsView == null) {
            try{
                pretsView = new FXMLLoader(getClass().getResource("/fr/afpa/dev/pompey/bibliothequejavafx/Prets.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return pretsView;
    }

    public void showAccueil() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/dev/pompey/bibliothequejavafx/Accueil.fxml"));
        ControllerAccueil controllerAccueil = new ControllerAccueil(accueilView);
        loader.setController(controllerAccueil);
        createStage(loader);
    }

    public void showAbonnees() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/dev/pompey/bibliothequejavafx/Abonnees.fxml"));
        ControllerAbonnees controllerAbonnees = new ControllerAbonnees();
        loader.setController(controllerAbonnees);
        createStage(loader);
    }

    public void showLivres() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/dev/pompey/bibliothequejavafx/Livres.fxml"));
        ControllerLivres controllerLivres = new ControllerLivres();
        loader.setController(controllerLivres);
        createStage(loader);
    }

    public void showPrets(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/dev/pompey/bibliothequejavafx/Prets.fxml"));
        ControllerPrets controllerPrets = new ControllerPrets();
        loader.setController(controllerPrets);
        createStage(loader);
    }

    public void showClientWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/afpa/dev/pompey/bibliothequejavafx/Client.fxml"));
        ControllerClient controllerClient = new ControllerClient();
        loader.setController(controllerClient);
        createStage(loader);
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        }catch(Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Bibliotheque JavaFX");
        stage.show();
    }

}
