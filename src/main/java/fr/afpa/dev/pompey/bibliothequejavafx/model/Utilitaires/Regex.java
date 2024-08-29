package fr.afpa.dev.pompey.bibliothequejavafx.model.Utilitaires;

public class Regex {
    public static final String REGEXNOMPRENOM = "^([a-zA-Za-zàáâäçèéêëìíîïñòóôöùúûü]+[,.]?[ ]?|[a-zA-Za-zàáâäçèéêëìíîïñòóôöùúûü]+[\\'-]?)+$";
    public static final String REGEXSTRING = "^[a-zA-Z0-9_.-]*$";
    public static final String REGEXINT = "^[0-9]+$";
    public static final String REGEXEMAIL = "^(?=.{1,64}@)[\\p{L}0-9\\+_-]+(\\.[\\p{L}0-9\\+_-]+)*@"
            + "[^-][\\p{L}0-9\\+-]+(\\.[\\p{L}0-9\\+-]+)*(\\.[\\p{L}]{2,})$";
}
