package bibliotheque;

import java.io.*;
import java.util.*;
import java.io.Serializable;

// Classe de gestion de la Bibliotheque
public class Bibliotheque {

    private static final long serialVersionUID = 262L;

    // -----------------------------------------------
    //Attributs
    // -----------------------------------------------
    private Integer prochainNumLecteur = 1;
    private HashMap<Integer, Lecteur> _dicoLecteur;
    private HashMap<String, Ouvrage> dicoOuvrage;
    private ArrayList<Emprunt> dicoEmprunts;

    /*
		 * Le dictionnaire de lecteur permet à bibliotheque de 
		 * garantir l'unicité de ces derniers, et facilitent les recherches et créations.
     */
    // -----------------------------------------------
    //Constructeur
    // -----------------------------------------------
    public Bibliotheque() {
        this.creerLecteurs(new HashMap<>());
        this.creerOuvrages(new HashMap<>());
        this.creerEmprunts(new ArrayList());
    }

// -----------------------------------------------
    // Public
// -----------------------------------------------	
    // -----------------------------------------------
    // Methodes
    // -----------------------------------------------
    /*
		 * La méthode nouveauLecteur permet de créé un lecteur en demandant la saisie de son numéro
		 * nom, prénom, date de naissance, adresse et numéro de téléphone.
		 * L'age doit être compris entre 3 et 110 ans
		 * Le lecteur est identifié par son numéro, si celui ci existe déjà dans le dictionnaire
		 * de bibliothèque, un message d'erreur est affiché.
		 * Une fois le nouveau lecteur créé, il est ajouté au dictionnaire de lecteur
		 * afin de garantir la cohérence des données.
     */
    public void nouveauLecteur() {

        Integer numLecteur = getProchainNumeroLecteur();
        String nom = EntreesSorties.lireChaine("Entrez le nom :");
        String prenom = EntreesSorties.lireChaine("Entrez le prenom :");
        Integer age;
        GregorianCalendar dateNaiss, dateNaissComp;
        GregorianCalendar dateActuelle = new GregorianCalendar();
        do {
            dateNaiss = EntreesSorties.lireDate("Entrez la date de naissance du lecteur :");
            dateNaissComp = new GregorianCalendar(dateActuelle.get(GregorianCalendar.YEAR), dateNaiss.get(GregorianCalendar.MONTH), dateNaiss.get(GregorianCalendar.DATE));
            if (dateNaissComp.before(dateActuelle)) {
                age = dateActuelle.get(GregorianCalendar.YEAR) - dateNaiss.get(GregorianCalendar.YEAR);
            } else {
                age = dateActuelle.get(GregorianCalendar.YEAR) - dateNaiss.get(GregorianCalendar.YEAR) - 1;
            }
            if ((age <= 3) | (age >= 110)) {
                EntreesSorties.afficherMessage("Age incorrecte (" + age + "), veuillez recommencer.");
            } else {
                EntreesSorties.afficherMessage("Age du lecteur : " + age + " ans");
            }
        } while ((age <= 3) | (age >= 110));
        String adresse = EntreesSorties.lireChaine("Entrez l'adresse :");
        String tel = EntreesSorties.lireChaine("Entrez le numero de telephone :");
        EntreesSorties.afficherMessage("Fin de saisie");

        Lecteur L = new Lecteur(numLecteur, nom, prenom, dateNaiss, adresse, tel);
        setLecteur(numLecteur, L);
        EntreesSorties.afficherMessage("Fiche lecteur créée.");
        //} else {
        //    EntreesSorties.afficherMessage("Ce numero de lecteur existe deja.");
        //}

    }

    /*
	 * La méthode consulterLecteur permet d'afficher l'ensemble des informations relatives à
	 * un lecteur, par la saisie de son identifiant (numéro de lecteur).
	 * Si le numéro de lecteur n'est pas dans la base de données de bibliotheque un message d'erreur est
	 * renvoyé a l'utilisateur.
         * une fois le lecteur trouvé, un message lui demande de s'afficher. Il serait préférable de demander 
         * au lecteur ses infos et de les afficher. Ainsi les interactions avec l'utilisateur seraient regroupées dans la classe application
     */
    public void consulterLecteur() {
        Integer numLecteur = EntreesSorties.lireEntier("Entrez le numero du lecteur : ");

        Lecteur L = getLecteur(numLecteur);

        if (L != null) {
            L.afficherLecteur();
        } else {
            EntreesSorties.afficherMessage("Aucun lecteur n'est associe a ce numero.");
        }
    }

    public void consulterLecteurs() {
        for (Map.Entry<Integer, Lecteur> l : _dicoLecteur.entrySet()) {
            l.getValue().afficherLecteur();
        }
    }

    // le getter getLecteurs et le setter setLecteurs sont publics pour permettre le chargement et la sauvegarde de la HashMap
    private Integer getProchainNumeroLecteur() {
        return prochainNumLecteur;
    }

    public HashMap<Integer, Lecteur> getLecteurs() {
        return _dicoLecteur;
    }

    public void creerLecteurs(HashMap<Integer, Lecteur> dicoLecteur) {
        _dicoLecteur = dicoLecteur;
    }

// -----------------------------------------------
    // Private
// -----------------------------------------------
    // -----------------------------------------------
    // Methodes
    // -----------------------------------------------
    /*
	 * La méthode getLecteur permet de rechercher dans la base de donnée de bibliotheque un objet 
	 * lecteur identifié par son numéro, et de renvoyer l'objet. (ou la donnée null s'il n'est pas trouvé)
     */
    private Lecteur getLecteur(Integer numLecteur) {
        return _dicoLecteur.get(numLecteur);
    }


    /*
	 * La méthode lierLecteur permet d'ajouter un lecteur a la base de donnée de bibliotheque.
     */
    private void setLecteur(Integer numLecteur, Lecteur L) {
        _dicoLecteur.put(numLecteur, L);
        this.prochainNumLecteur = getProchainNumeroLecteur() + 1;
    }

    /*
	 * La méthode lesLecteurs permet de créer un iterator sur les lecteurs, dans le but de les parcourir
	 * pour eventuellement les relancer.
     */
    private Iterator<Lecteur> lesLecteurs() {
        return _dicoLecteur.values().iterator();
    }

    /*______________________________________________________________________________________________________________________________________________________________
-----------------------------------------------------------------          Ouvrage             ----------------------------------------------------------------
______________________________________________________________________________________________________________________________________________________________*/
    public void nouvelOuvrage() {
        String isbn = EntreesSorties.lireChaine("Entrez le numero ISBN de l'ouvrage : ");
        Ouvrage O = getOuvrage(isbn);
        if (O == null) {
            String titre = EntreesSorties.lireChaine("Entrez le titre de l'ouvrage : ");
            String nomEditeur = EntreesSorties.lireChaine("Entrez le nom de l'éditeur de l'ouvrage : ");
            GregorianCalendar dateActuelle = new GregorianCalendar();
            GregorianCalendar dateParution = EntreesSorties.lireDate("Entrez la date de parution de l'ouvrage : ");
            while (dateParution.after(dateActuelle)) {
                dateParution = EntreesSorties.lireDate("Date incorrecte, entrez la date de parution de l'ouvrage : (antérieure à la date d'aujourd'hui) : ");
            }

            String nomsAuteurs = EntreesSorties.lireChaine("Entrez le(s) nom(s) de(s) auteur(s) : ");
            Integer sCible = 0;
            CibleOuvrage cible = CibleOuvrage.adefinir;

            while (sCible != 1 && sCible != 2 && sCible != 3) {
                sCible = EntreesSorties.lireEntier("Entrez la cible de l'ouvrage (1 pour enfant; 2 pour adolescent; 3 pour adulte) : ");
                if (sCible == 1) {
                    cible = CibleOuvrage.enfant;
                } else if (sCible == 2) {
                    cible = CibleOuvrage.adolescent;
                } else if (sCible == 3) {
                    cible = CibleOuvrage.adulte;
                } else {
                    EntreesSorties.afficherMessage("Saisie non valide, cible à définir");
                }
            }

            EntreesSorties.afficherMessage("Fin de saisie");

            Ouvrage o = new Ouvrage(titre, nomEditeur, dateParution, nomsAuteurs, isbn, cible);
            setOuvrage(isbn, o);
            EntreesSorties.afficherMessage("Ouvrage créé.");
        } else {
            EntreesSorties.afficherMessage("Cet ouvrage existe déjà.");
        }

    }

    private void setOuvrage(String isbn, Ouvrage O) {
        dicoOuvrage.put(isbn, O);
    }

    public void consulterOuvrages() {
        for (Map.Entry<String, Ouvrage> o : dicoOuvrage.entrySet()) {
            o.getValue().afficherReduit();
            o.getValue().afficherComplementaire();
        }
    }

    private Ouvrage getOuvrage(String isbn) {
        return dicoOuvrage.get(isbn);
    }

    public HashMap<String, Ouvrage> getOuvrages() {
        return dicoOuvrage;
    }

    public void consulterOuvrage() {
        String isbn = EntreesSorties.lireChaine("Entrez le numéro isbn de l'ouvrage que vous voulez consulter : ");

        Ouvrage O = getOuvrage(isbn);

        if (O != null) {
            O.afficherReduit();
            O.afficherComplementaire();
        } else {
            EntreesSorties.afficherMessage("Aucun Ouvrage n'est associe à ce numero.");
        }
    }

    public void creerOuvrages(HashMap<String, Ouvrage> _dicoOuvrage) {
        dicoOuvrage = _dicoOuvrage;
    }

    /*______________________________________________________________________________________________________________________________________________________________
-----------------------------------------------------------------          Exemplaire             ----------------------------------------------------------------
______________________________________________________________________________________________________________________________________________________________*/
    public void nouvelExemplaire() {
        String isbn = EntreesSorties.lireChaine("Entrez le numero ISBN de l'ouvrage :");
        Ouvrage O = getOuvrage(isbn);
        GregorianCalendar dateReception;
        GregorianCalendar dateActuelle = new GregorianCalendar();
        if (O != null) {
            dateReception = EntreesSorties.lireDate("Entrez la date de réception des exemplaires : ");
            while (dateReception.before(O.getDateParution()) || dateReception.after(dateActuelle)) {
                dateReception = EntreesSorties.lireDate("Date incorrecte, entrez la date de réception des exemplaires (postérieure à la date de parution) : ");
            }
            Integer nouvNb = EntreesSorties.lireEntier("Entrez le nombre d'exemplaires que vous souhaitez créer : ");
            Integer statut = EntreesSorties.lireEntier("Entrez le statut d'emprunt des exemplaires (1 si empruntables; 0 si non empruntables) : ");
            Boolean statutEmprunt;

            if (statut == 1) {
                statutEmprunt = true;
            } else {
                statutEmprunt = false;
            }

            O.ajouterExemplaire(dateReception, nouvNb, statutEmprunt);

            EntreesSorties.afficherMessage("Exemplaires créés.");
        } else {
            EntreesSorties.afficherMessage("Cet ouvrage n'existe pas.");
        }

    }

    public void consulterExemplairesOuvrage() {
        String isbn = EntreesSorties.lireChaine("Entrez le numero ISBN de l'ouvrage :");
        Ouvrage O = getOuvrage(isbn);
        if ((O != null) && (O.exemplaires != null)) {
            O.afficherReduit();
            for (Map.Entry<Integer, Exemplaire> e : O.exemplaires.entrySet()) {
                e.getValue().afficherExemplaire();
            }
        } else if (O.exemplaires == null) {
            EntreesSorties.afficherMessage("Cet ouvrage n'a pas d'exemplaires.");

        } else {
            EntreesSorties.afficherMessage("Cet ouvrage n'existe pas.");

        }
    }

    public void consulterExemplaires() {
        for (Map.Entry<String, Ouvrage> o : dicoOuvrage.entrySet()) {
            String isbn = o.getKey();
            System.out.println("");
            o.getValue().getExemplaires(isbn);
        }
    }

    /*______________________________________________________________________________________________________________________________________________________________
-----------------------------------------------------------------          Emprunts             ----------------------------------------------------------------
______________________________________________________________________________________________________________________________________________________________*/
    public void emprunterExemplaire() {

        int numLecteur = EntreesSorties.lireEntier("Entrez le numero du lecteur souhaitant emprunter un exemplaire : ");
        Lecteur L = getLecteur(numLecteur);
        if (L == null) {
            EntreesSorties.afficherMessage("La fiche de ce lecteur n'existe pas.");
        } else {
            String isbn = EntreesSorties.lireChaine("Entrez le numero ISBN de l'ouvrage à emprunter : ");
            Ouvrage O = getOuvrage(isbn);
            if (O == null) {
                EntreesSorties.afficherMessage("Ouvrage non présent dans le système.");
            } else {
                int numExemplaire = EntreesSorties.lireEntier("Entrez le numero de l'exemplaire à emprunter : ");
                Exemplaire E = O.getExemplaire(numExemplaire);
                if (E == null) {
                    EntreesSorties.afficherMessage("Cet exemplaire n'existe pas.");
                } else {
                    if (!E.isDisponible()) {
                        EntreesSorties.afficherMessage("Cet exemplaire est déjà en cours d'emprunt ou n'est pas empruntable.");
                    } else {
                        CibleOuvrage co = O.getCible();
                        if (L.isLecteurSature() == true) {
                            EntreesSorties.afficherMessage("Ce lecteur a effectué tous ses emprunts possibles.");
                        } else {
                            CibleOuvrage cl = L.isLecteurOK();

                            if (!cibleOK(co, cl)) {
                                EntreesSorties.afficherMessage("Ce lecteur ne correspond pas à la cible de l'ouvrage.");
                            } else {
                                Emprunt e = new Emprunt(numLecteur, numExemplaire, isbn);
                                dicoEmprunts.add(e);
                                L.plusNbEmprunt();
                                E.setEtat(EtatEmprunt.en_cours);
                                EntreesSorties.afficherMessage("L'emprunt a été créé.");
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean cibleOK(CibleOuvrage co, CibleOuvrage cl) {
        if (cl == CibleOuvrage.enfant && (co == CibleOuvrage.adolescent || co == CibleOuvrage.adulte)) {
            return false;
        } else if (cl == CibleOuvrage.adolescent && co == CibleOuvrage.adulte) {
            return false;
        } else {
            return true;
        }
    }

    public void consulterEmprunts() {
        for (Emprunt emprunt : dicoEmprunts) {
            String isbn = emprunt.getIsbn();
            Ouvrage o = getOuvrage(isbn);
            System.out.println("");
            emprunt.afficherEmprunt();
        }
    }

    public void consulterEmpruntsLecteur() {
        int numLecteur = EntreesSorties.lireEntier("Entrez le numero du lecteur souhaitant emprunter un exemplaire : ");
        Lecteur L = getLecteur(numLecteur);
        if (L == null) {
            EntreesSorties.afficherMessage("La fiche de ce lecteur n'existe pas.");
        } else {
            for (Emprunt emprunt : dicoEmprunts) {
                if (emprunt.getEmprunteur() == numLecteur) {
                    String isbn = emprunt.getIsbn();
                    Ouvrage o = getOuvrage(isbn);
                    System.out.println("");
                    EntreesSorties.afficherMessage("Informations de l'emprunt : ");
                    EntreesSorties.afficherMessage("Titre de l'ouvrage : " + o.getTitre());
                    emprunt.afficherEmprunt();
                }
            }
        }
    }

    public Emprunt getEmprunt(Integer numLecteur, Integer numExemplaire, String isbn) {

        Emprunt e = null;
        for (Emprunt emprunt : dicoEmprunts) {
            if (emprunt.getEmprunteur() == numLecteur && emprunt.getNumExemplaire() == numExemplaire && emprunt.getIsbn() == isbn) {
                e = emprunt;
            } else {
                EntreesSorties.afficherMessage("Aucun emprunt trouvé avec ces informations.");
            }
        }
        return e;
    }

    public void rendreExemplaire() {
        int numLecteur = EntreesSorties.lireEntier("Entrez le numero du lecteur ayant emprunté l'exemplaire à rendre : ");
        Lecteur L = getLecteur(numLecteur);
        if (L == null) {
            EntreesSorties.afficherMessage("La fiche de ce lecteur n'existe pas.");
        } else {
            String isbn = EntreesSorties.lireChaine("Entrez le numero ISBN de l'ouvrage emprunté : ");
            Ouvrage O = getOuvrage(isbn);
            if (O == null) {
                EntreesSorties.afficherMessage("Ouvrage non présent dans le système.");
            } else {
                int numExemplaire = EntreesSorties.lireEntier("Entrez le numero de l'exemplaire emprunté : ");
                Exemplaire E = O.getExemplaire(numExemplaire);
                if (E == null) {
                    EntreesSorties.afficherMessage("Cet exemplaire n'existe pas.");
                } else {
                    Emprunt e = getEmprunt(numLecteur, numExemplaire, isbn);
                    if (e == null && (e.getEtatEmprunt() == EtatEmprunt.disponible)) {
                        EntreesSorties.afficherMessage("Cet emprunt n'existe pas.");
                    } else {
                        dicoEmprunts.remove(e);
                        L.moinsNbEmprunt();
                        E.setEtat(EtatEmprunt.disponible);
                    }
                }
            }
        }
    }

    public void relancerLecteur() {
        int dureeEmprunt;
        for (Emprunt emprunt : dicoEmprunts) {
            dureeEmprunt = emprunt.getDateRetour().get(GregorianCalendar.DAY_OF_WEEK) - emprunt.getDateEmprunt().get(GregorianCalendar.DAY_OF_WEEK);
            if (dureeEmprunt >= 15) {
                emprunt.setEtatEmprunt(EtatEmprunt.en_retard);
                String isbn = emprunt.getIsbn();
                Ouvrage o = getOuvrage(isbn);
                EntreesSorties.afficherMessage("Inforations sur l'emprunt en retard : ");
                EntreesSorties.afficherMessage("Titre de l'ouvrage : " + o.getTitre());
                emprunt.afficherEmprunt();
            }
        }
    }

    public void creerEmprunts(ArrayList<Emprunt> _dicoEmprunts) {
        dicoEmprunts = _dicoEmprunts;
    }

    public ArrayList<Emprunt> getEmprunts() {
        return dicoEmprunts;
    }
}
