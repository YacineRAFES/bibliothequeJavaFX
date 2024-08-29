module fr.afpa.dev.pompey.bibliothequejavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens fr.afpa.dev.pompey.bibliothequejavafx to javafx.fxml;
    exports fr.afpa.dev.pompey.bibliothequejavafx;
    exports fr.afpa.dev.pompey.bibliothequejavafx.controller;
    opens fr.afpa.dev.pompey.bibliothequejavafx.controller to javafx.fxml;
    exports fr.afpa.dev.pompey.bibliothequejavafx.exception;
    opens fr.afpa.dev.pompey.bibliothequejavafx.exception to javafx.fxml;
}