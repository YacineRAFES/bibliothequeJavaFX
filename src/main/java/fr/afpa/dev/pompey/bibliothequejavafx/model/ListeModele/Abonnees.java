package fr.afpa.dev.pompey.bibliothequejavafx.model.ListeModele;

import fr.afpa.dev.pompey.bibliothequejavafx.exception.SaisieException;

import java.time.LocalDate;

public class Abonnees {
    private String nom;
    private String prenom;
    private String email;
    private LocalDate dateInscription;

    public Abonnees() {}

    public Abonnees(String nom, String prenom, String email, LocalDate dateInscription) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateInscription = dateInscription;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws SaisieException {
        if (nom == null || nom.isEmpty()) {
            throw new SaisieException("Le nom ne doit pas être vide");
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) throws SaisieException {
        if (prenom == null || prenom.isEmpty()) {
            throw new SaisieException("Le prénom ne doit pas être vide");
        }
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws SaisieException {
        if (email == null || email.isBlank()) {
            throw new SaisieException("L'email ne doit pas être vide");
        }
        this.email = email;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) throws SaisieException {
        if (dateInscription == null) {
            throw new SaisieException("La date ne doit pas être vide");
        }
        if (dateInscription.isBefore(LocalDate.now())) {
            throw new SaisieException("La date ne doit pas être antérieure à aujourd'hui");
        }
        if (dateInscription.isAfter(LocalDate.now())) {
            throw new SaisieException("La date d'inscription ne doit pas être supérieure à aujourd'hui");
        }
        this.dateInscription = dateInscription;
    }

    @Override
    public String toString() {
        return this.getNom() + " " + this.getPrenom();
    }
}
