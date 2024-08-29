package fr.afpa.dev.pompey.bibliothequejavafx.model.Utilitaires;

import fr.afpa.dev.pompey.bibliothequejavafx.exception.SaisieException;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;

public class Input extends SaisieException {
    private Input(){

    }
    public static String getNomPrenom(String saisie, String type) throws SaisieException {
        if(!saisie.matches(Regex.REGEXNOMPRENOM)){
            AffichageFenetre.Fenetre(
                    "Erreur de saisie",
                    null,
                    "Veuillez re-saisir en caractère sur le champs " + type);
            throw new SaisieException("erreur de saisie nom/prenom");
        }
        return saisie.toUpperCase();
    }

    public static String getEmail(String saisie) throws SaisieException {
        if(!saisie.matches(Regex.REGEXEMAIL)) {
            AffichageFenetre.Fenetre(
                    "Erreur de saisie d'email",
                    null,
                    "Veuillez re-saisir l'email par exemple : votrenom@domaine.com");
            throw new SaisieException("erreur de saisie email");
        }
        return saisie;
    }

    public static int getInt(String saisie) throws SaisieException {
        if(!saisie.matches(Regex.REGEXINT)) {
            AffichageFenetre.Fenetre(
                    "Erreur de saisie",
                    null,
                    "Veuillez re-saisir en entier");
            throw new SaisieException("erreur de saisie");
        }
        try{
            return  Integer.parseInt(saisie);
        }catch(NumberFormatException e){
            AffichageFenetre.Fenetre(
                    "Erreur de saisie",
                    null,
                    "Veuillez re-saisir un entier moins de 2000000000");
            throw new SaisieException();
        }
    }

    public static LocalDate CreateDateNow() {
        return LocalDate.now();
    }

    public static LocalDate CreateDateFin() {
        return LocalDate.now().plusDays(7);
    }


}

