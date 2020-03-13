/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotheque;

import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 *
 * @author fricaudc
 */
public class Emprunt {
    private static final long serialVersionUID = 1500L;
    private Integer emprunteur;
    private Integer exemplaire;
    private String isbn_ouvrage;
    private GregorianCalendar dateEmprunt;
    private GregorianCalendar dateRetour;
    private EtatEmprunt etat;
    

    public Emprunt(Integer numLecteur, Integer numExemplaire, String isbn) {
        this.emprunteur = numLecteur;
        this.exemplaire = numExemplaire;
        this.isbn_ouvrage = isbn;
        this.dateEmprunt = new GregorianCalendar();
        this.dateRetour = new GregorianCalendar();
        //this.dateRetour = dateEmprunt.get(GregorianCalendar.DAY_OF_WEEK)+8;
        this.etat = EtatEmprunt.en_cours;
    }
}
