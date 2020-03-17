/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotheque;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 *
 * @author fricaudc
 */
public class Ouvrage implements Serializable {

    private static final long serialVersionUID = 9029278399512987782L;

    // -----------------------------------------------
    //Attributs
    // -----------------------------------------------
    private String titre;
    private String nomEditeur;
    private GregorianCalendar dateParution;
    private String nomsAuteurs;
    private String isbn;
    private CibleOuvrage cible;
    private int prochainNumeroExemplaire = 1;
    public HashMap<Integer, Exemplaire> exemplaires;

    public Ouvrage() {
        exemplaires = new HashMap<>();
    }

    // -----------------------------------------------
    //Constructeur
    // -----------------------------------------------
    public Ouvrage(String titre, String nomEditeur, GregorianCalendar dateParution, String nomsAuteurs, String isbn, CibleOuvrage cible) {
        this.titre = titre;
        this.nomEditeur = nomEditeur;
        this.dateParution = dateParution;
        this.nomsAuteurs = nomsAuteurs;
        this.isbn = isbn;
        this.cible = cible;
        this.creerExemplaires(new HashMap<>());
    }

    public void creerExemplaires(HashMap<Integer, Exemplaire> _dicoExemplaires) {
        exemplaires = _dicoExemplaires;
    }

    public int getProchainNumeroExemplaire() {
        return prochainNumeroExemplaire;
    }

    public String getTitre() {
        return titre;
    }

    public String getNomEditeur() {
        return nomEditeur;
    }

    public GregorianCalendar getDateParution() {
        return dateParution;
    }

    public String getNomsAuteurs() {
        return nomsAuteurs;
    }

    public String getIsbn() {
        return isbn;
    }

    public CibleOuvrage getCible() {
        return cible;
    }

    public Exemplaire getExemplaire(Integer numExemplaire) {
        return exemplaires.get(numExemplaire);
    }    
    
    public void getExemplaires(String isbn) {
        afficherReduit();
        exemplaires.forEach((key, value) -> value.afficherExemplaire());
    }

    public void setExemplaire(Integer numExemplaire, Exemplaire e) {
        exemplaires.put(numExemplaire, e);
    }

    public void setProchainNumeroExemplaire(Integer nb) {
        prochainNumeroExemplaire = this.prochainNumeroExemplaire + nb;
    }

    public void afficherReduit() {
        System.out.println("Titre ouvrage : " + this.titre);
        System.out.println("Numéro ISBN : " + this.isbn);
    }

    public void afficherComplementaire() {
        System.out.println("Nom Editeur : " + this.nomEditeur);
        System.out.println("Nom(s) Auteur(s) : " + this.nomsAuteurs);
        String date = EntreesSorties.ecrireDate(getDateParution());
        System.out.println("Date de parution :" + date);
        System.out.println("Cible de l'ouvrage : " + this.cible);
        EntreesSorties.afficherMessage("");
    }

    public void ajouterExemplaire(GregorianCalendar dateReception, Integer nouvNb, boolean statutEmprunt) {
        Integer numExemplaire = this.getProchainNumeroExemplaire();
        this.setProchainNumeroExemplaire(nouvNb);
        while (nouvNb >= 1) {
            Exemplaire e = new Exemplaire(this, dateReception, numExemplaire, statutEmprunt);
            this.setExemplaire(numExemplaire, e);

            numExemplaire++;
            nouvNb--;
        }
    }

}
