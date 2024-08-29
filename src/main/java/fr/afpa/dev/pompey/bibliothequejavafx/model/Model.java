package fr.afpa.dev.pompey.bibliothequejavafx.model;

import fr.afpa.dev.pompey.bibliothequejavafx.vue.ViewFactory;

import javax.swing.text.View;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;

    private Model(){
        this.viewFactory = new ViewFactory();
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
}
