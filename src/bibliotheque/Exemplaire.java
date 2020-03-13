/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotheque;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 *
 * @author fricaudc
 */
public class Exemplaire extends Ouvrage implements Serializable{
    // -----------------------------------------------
    //Attributs    private static final long serialVersionUID = 600L;
    // -----------------------------------------------
    private static final long serialVersionUID = 800L;
    private GregorianCalendar dateReception;
    private int numExemplaire;
    private boolean statutEmprunt;
    private String isbn;

    // -----------------------------------------------
    //Constructeur
    // -----------------------------------------------
    public Exemplaire(Ouvrage ouvrage, GregorianCalendar dateReception, int numExemplaire, boolean statutEmprunt) {
        this.dateReception = dateReception;    
        this.numExemplaire = numExemplaire;
        this.statutEmprunt = statutEmprunt;
        this.isbn = ouvrage.getIsbn();        
    }
    
    public Integer getNumExemplaire() {
        return numExemplaire;
    }

    public GregorianCalendar getDateReception(){
        return dateReception;
    }
    
    public boolean getStatutEmprunt(){
        return statutEmprunt;
    }

    public void afficherExemplaire() {
        System.out.println("Date reception : " + EntreesSorties.ecrireDate(this.getDateReception()));
        System.out.println("Numero d'exemplaire : "+ this.getNumExemplaire());
        System.out.println("Statut d'emprunt de cet exemplaire : " + this.getStatutEmprunt()+" (true = empruntable | false = seulement consultable)");
        EntreesSorties.afficherMessage("");
    }   
}
