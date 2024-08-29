package fr.afpa.dev.pompey.bibliothequejavafx.model;

import fr.afpa.dev.pompey.bibliothequejavafx.model.ListeModele.Abonnees;
import fr.afpa.dev.pompey.bibliothequejavafx.model.ListeModele.Livres;
import fr.afpa.dev.pompey.bibliothequejavafx.model.ListeModele.Prets;

import java.util.ArrayList;
import java.util.List;

public class Listes {
    private static List<Abonnees> abonnees = new ArrayList<>();
    private static List<Livres> livres = new ArrayList<>();
    private static List<Prets> prets = new ArrayList<>();

    //GETTER ET SETTER
    public static List<Abonnees> getAbonnes() {
        return abonnees;
    }

    public static List<Livres> getLivres() {
        return livres;
    }

    public static List<Prets> getPret() {
        return prets;
    }

    public static void addAbonnes(Abonnees abonnees) {
        getAbonnes().add(abonnees);
    }

    public static void removeAbonnes(Abonnees abonnees) {
        getAbonnes().remove(abonnees);
    }

    public static void addLivre(Livres livre) {
        getLivres().add(livre);
    }

    public static void removeLivre(Livres livre) {
        getLivres().remove(livre);
    }

    public static void addPret(Prets pret) {
        getPret().add(pret);
    }

    public static void removePret(Prets pret) {
        getPret().remove(pret);
    }
}
