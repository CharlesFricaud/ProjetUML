package bibliotheque;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;

// Classe de gestion de Lecteur
public class Lecteur implements Serializable {

    private static final long serialVersionUID = 422L;

    // -----------------------------------------------
    //Attributs
    // -----------------------------------------------
    private String nom;
    private String prenom;
    private Integer numLecteur;
    private GregorianCalendar dateNaissance;
    private String adresse;
    private String numTel;
    private Integer nbEmprunt;

    // -----------------------------------------------
    //Constructeur
    // -----------------------------------------------
    public Lecteur(Integer numLecteur, String nom, String prenom, GregorianCalendar dateNaissance, String adresse, String numTel) {
        this.setNumLecteur(numLecteur);        
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setDateNaissance(dateNaissance);
        this.setAdresse(adresse);
        this.setNumTel(numTel);
        this.nbEmprunt = 0;
    }

// -----------------------------------------------
    // Public
// -----------------------------------------------
    // -----------------------------------------------
    //Getters
    // -----------------------------------------------
    public Integer getNumLecteur() {
        return numLecteur;
    }   
    
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public GregorianCalendar getDateNaissance() {
        return dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNumTel() {
        return numTel;
    }
    
    public CibleOuvrage getCible(){
        if(this.calculAge()<10){
            return CibleOuvrage.enfant;
        }
        else if(this.calculAge()>=10 && this.calculAge()<16){
            return CibleOuvrage.adolescent;
        }
        else{
            return CibleOuvrage.adulte;
        }
    }
    
    // -----------------------------------------------
    // Methodes
    // -----------------------------------------------

    /*
		 * La methode afficherLecteur affiche l'ensemble des informations relatives à un lecteur.
     */
    public void afficherLecteur() {
        System.out.println("Numero lecteur : " + this.getNumLecteur());
        System.out.println("Nom et prenom du lecteur: " + this.getNom() + " " + this.getPrenom());
        System.out.println("Age : " + this.calculAge() + " ans");
        System.out.println("Adresse : " + this.getAdresse());
        System.out.println("Telephone : " + this.getNumTel());
        EntreesSorties.afficherMessage("");
    }

    /*
		 * la methode calculAge permet de determiner l'age des lecteurs grace a leur date de naissance
		 * et la date actuelle. De cette façon, il n'y a pas de mise a jour a faire sur l'age des lecteurs.
     */
    public Integer calculAge() {
        Integer age;
        GregorianCalendar dateNaissComp;
        GregorianCalendar dateActuelle = new GregorianCalendar();
        dateNaissComp = new GregorianCalendar(dateActuelle.get(GregorianCalendar.YEAR), dateNaissance.get(GregorianCalendar.MONTH), dateNaissance.get(GregorianCalendar.DATE));
        if (dateNaissComp.before(dateActuelle)) {
            age = dateActuelle.get(GregorianCalendar.YEAR) - dateNaissance.get(GregorianCalendar.YEAR);
        } else {
            age = dateActuelle.get(GregorianCalendar.YEAR) - dateNaissance.get(GregorianCalendar.YEAR) - 1;
        }
        return age;
    }
    
    public boolean isLecteurSature(){
        if(this.nbEmprunt>4){
            return true;
        }
        else{
            return false;
        }
    }
    
    public CibleOuvrage isLecteurOK(){
        return this.getCible();
    }
    
    
// -----------------------------------------------
    // Private
// -----------------------------------------------
    // -----------------------------------------------
    //Setters
    // -----------------------------------------------
    private void setNumLecteur(Integer numLecteur) {
        this.numLecteur = numLecteur;
    }    
    
    private void setNom(String nom) {
        this.nom = nom;
    }

    private void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    private void setDateNaissance(GregorianCalendar dateNaiss) {
        this.dateNaissance = dateNaiss;
    }

    private void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    private void setNumTel(String numTel) {
        this.numTel = numTel;
    }
    
    public void plusNbEmprunt(){
        this.nbEmprunt++;
    }

    public void moinsNbEmprunt(){
        this.nbEmprunt--;
    }    

}