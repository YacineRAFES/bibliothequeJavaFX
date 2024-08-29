package fr.afpa.dev.pompey.bibliothequejavafx.model.ListeModele;

import fr.afpa.dev.pompey.bibliothequejavafx.exception.SaisieException;

import java.time.LocalDate;

public class Abonnees {
    private String nomAbonnees;
    private String prenomAbonnees;
    private String emailAbonnees;
    private LocalDate dateAbonnees;

    public Abonnees() {

    }

    public Abonnees(String nomAbonnees, String prenomAbonnees, String emailAbonnees, LocalDate dateAbonnees) {
        this.nomAbonnees = nomAbonnees;
        this.prenomAbonnees = prenomAbonnees;
        this.emailAbonnees = emailAbonnees;
        this.dateAbonnees = dateAbonnees;
    }

    public String getNomAbonnees() {
        return nomAbonnees;
    }

    public void setNomAbonnees(String nom) throws SaisieException {
        if ( nom == null ) {
            throw new SaisieException("le nom ne doit pas être vide");
        }
        if (nom.isEmpty() ) {
            throw new SaisieException("le nom ne doit pas être vide");
        }
        this.nomAbonnees = nom;

    }

    public String getPrenomAbonnees() {
        return prenomAbonnees;
    }

    public void setPrenomAbonnees(String prenom) throws SaisieException {
        if ( prenom == null ) {
            throw new SaisieException("le prenom ne doit pas être vide");
        }
        if (prenom.isEmpty() ) {
            throw new SaisieException("le prenom ne doit pas être vide");
        }
        this.prenomAbonnees = prenom;

    }

    public String getEmailAbos() {
        return emailAbonnees;
    }

    public void setEmailAbos(String email) throws SaisieException {
        if ( email == null ) {
            throw new SaisieException("l'email ne doit pas être vide");
        }
        if (email.isBlank() ) {
            throw new SaisieException("l'email ne doit pas être vide");
        }
        this.emailAbonnees = email;
    }

    public LocalDate getDateInscriptionAbonnees() {
        return dateAbonnees;
    }

    public void setDateInscriptionAbonnees(LocalDate dateAbonnees) throws SaisieException {
        if( dateAbonnees == null ) {
            throw new SaisieException("La date ne doit pas être vide");
        }
        if (dateAbonnees.isBefore(LocalDate.now())) {
            throw new SaisieException("La date ne doit pas être antérieure qu'aujourd'hui");
        }
        if (dateAbonnees.isAfter(LocalDate.now())) {
            throw new SaisieException("La date d'inscription ne doit pas être supérieur qu'aujourd'hui");
        }
        this.dateAbonnees = dateAbonnees;
    }

    @Override
    public String toString() {
        return this.getNomAbonnees() + " " + this.getPrenomAbonnees();

        // "Le nom de l'abonné : " + getNomAbos() + '\n' +
        //  "le prenom de l'abonne : " + getPrenomAbos() + '\n' +
        //  "le email : " + getEmailAbos() + '\n' +
        //   "La date de l'inscription : " + getDateInscriptionAbos() + '\n';
    }
}
