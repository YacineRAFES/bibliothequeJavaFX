package fr.afpa.dev.pompey.bibliothequejavafx.model.ListeModele;

import fr.afpa.dev.pompey.bibliothequejavafx.exception.SaisieException;

import java.time.LocalDate;

public class Prets{
    private Abonnees abonnees;
    private Livres livres;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    // Constructeur sans paramètre
    public Prets(){
    }

    public Abonnees getAbonnees() {
        return abonnees;
    }

    public void setAbonnees(Abonnees abonnees) {
        this.abonnees = abonnees;
    }

    public Livres getLivres() {
        return livres;
    }

    public void setLivre(Livres livre) {
        this.livres = livre;
    }

    public Prets(Abonnees abonnees, Livres livres, LocalDate dateDebut, LocalDate dateFin) {
        this.abonnees= abonnees;
        this.livres = livres;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;

    }
    public Prets(LocalDate dateDebut, LocalDate dateFin){
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    //Gutter et Setter de DateDebut
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) throws SaisieException {
        if (dateDebut.isAfter(LocalDate.now())) {
            throw new SaisieException("La date de fin ne doit pas être supérieure de 7 jours.");
        }
        if (dateDebut.isBefore(LocalDate.now())) {
            throw new SaisieException("la date de fin ne doit pas être inférieur de 7 jours.");
        }
        this.dateDebut = dateDebut;
    }

    //Gutter et Setter de DateFin
    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) throws SaisieException {
        if (dateFin.isAfter(LocalDate.now().plusDays(7))) {
            throw new SaisieException("La date de fin ne doit pas être supérieure de 7 jours.");
        }
        if (dateFin.isBefore(LocalDate.now().plusDays(7))) {
            throw new SaisieException("la date de fin ne doit pas être inférieur de 7 jours.");
        }
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Prêt : " + "\n" +
                "Le prêt du livre : " + "\n" +
                "débute le " + getDateDebut() + '\n' +
                "terminera le " + getDateFin() + '\n';
    }
}
