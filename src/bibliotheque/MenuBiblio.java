package bibliotheque;

public class MenuBiblio {

    private Bibliotheque _bibliotheque;

    public MenuBiblio(Bibliotheque bibliotheque) {
        _bibliotheque = bibliotheque;
    }

    /*
	 * menuPrincipal permet à l'utilisateur de selectionner un type de sous menu (Lecteur, Ouvrage ou Exemplaire) 
	 * où il effectuera par la suite l'action désirée. Si l'utilisateur a fini d'utiliser le programme, il choisit l'option Quitter.
     */
    public void menuPrincipal() {
        Integer menu;
        do {
            EntreesSorties.afficherMessage(" ========================================================");
            EntreesSorties.afficherMessage("|                   Menu Principal                       |");
            EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
            EntreesSorties.afficherMessage("| Menu Lecteur : 1                                       |");
            EntreesSorties.afficherMessage("| Menu Ouvrage : 2                                       |");
            EntreesSorties.afficherMessage("| Menu Exemplaire : 3                                    |");
            EntreesSorties.afficherMessage("| Quitter : 0                                            |");
            EntreesSorties.afficherMessage(" ========================================================");
            menu = EntreesSorties.lireEntier();

            switch (menu) {
                case 1: {
                    this.menuLecteur();
                    break;
                }
                case 2: {
                    this.menuOuvrage();
                    break;
                }
                case 3: {
                    this.menuExemplaire();
                    break;
                }
                case 0:
                    break;
                default: {
                    System.out.println("Choix non valide");
                    break;

                }

            }
        } while (menu != 0);
    }

    /* menuLect permet d'effectuer une série d'action concernant les utilisateur (lecteurs) de la bibliothèque.
	 * Une fois une action effectuée, l'utilisateur sera rediriger vers ce même menu afin de pouvoir sélectionner
	 * une nouvelle fois une action concernant les lecteurs.
	 * "Retour Menu Principal" renvoi l'utilisateur au menu principal.
     */
    public void menuLecteur() {
        Integer menuLect;
        do {
            EntreesSorties.afficherMessage(" ========================================================");
            EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
            EntreesSorties.afficherMessage("| Nouveau lecteur : 1                                    |");
            EntreesSorties.afficherMessage("| Consulter lecteur : 2                                  |");
            EntreesSorties.afficherMessage("| Afficher tous les lecteurs : 3                         |");
            EntreesSorties.afficherMessage("| Retour Menu Principal : 0                              |");
            EntreesSorties.afficherMessage(" ========================================================");
            menuLect = EntreesSorties.lireEntier();

            switch (menuLect) {
                case 1: {
                    DonneesUtilitaire.loadDB(_bibliotheque);
                    _bibliotheque.nouveauLecteur();
                    DonneesUtilitaire.updateDB(_bibliotheque);
                    break;
                }
                case 2: {
                    DonneesUtilitaire.loadDB(_bibliotheque);
                    _bibliotheque.consulterLecteur();
                    DonneesUtilitaire.updateDB(_bibliotheque);
                    break;
                }
                case 3: {
                    DonneesUtilitaire.loadDB(_bibliotheque);
                    _bibliotheque.consulterLecteurs();
                    DonneesUtilitaire.updateDB(_bibliotheque);
                    break;
                }
                case 0:
                    break;
                default: {
                    System.out.println("Choix non valide");
                    break;
                }
            }
        } while (menuLect != 0);
    }

    public void menuOuvrage() {
        Integer menuLect;
        do {
            EntreesSorties.afficherMessage(" ========================================================");
            EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
            EntreesSorties.afficherMessage("| Ajouter un ouvrage : 1                                 |");
            EntreesSorties.afficherMessage("| Consulter un ouvrage : 2                               |");
            EntreesSorties.afficherMessage("| Afficher tous les ouvrages : 3                         |");
            EntreesSorties.afficherMessage("| Retour Menu Principal : 0                              |");
            EntreesSorties.afficherMessage(" ========================================================");
            menuLect = EntreesSorties.lireEntier();

            switch (menuLect) {
                case 1: {
                    DonneesUtilitaire.loadDB(_bibliotheque);
                    _bibliotheque.nouvelOuvrage();
                    DonneesUtilitaire.updateDB(_bibliotheque);
                    break;
                }
                case 2: {
                    DonneesUtilitaire.loadDB(_bibliotheque);
                    _bibliotheque.consulterOuvrage();
                    DonneesUtilitaire.updateDB(_bibliotheque);
                    break;
                }
                case 3: {
                    DonneesUtilitaire.loadDB(_bibliotheque);
                    _bibliotheque.consulterOuvrages();
                    DonneesUtilitaire.updateDB(_bibliotheque);
                    break;
                }
                case 0:
                    break;
                default: {
                    System.out.println("Choix non valide");
                    break;
                }
            }
        } while (menuLect != 0);
    }

    public void menuExemplaire() {
        Integer menuLect;
        do {
            EntreesSorties.afficherMessage(" ========================================================");
            EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
            EntreesSorties.afficherMessage("| Nouvel exemplaire : 1                                  |");
            EntreesSorties.afficherMessage("| Consulter les exemplaires d'un ouvrage : 2             |");
            EntreesSorties.afficherMessage("| Consulter tous les  exemplaires : 3                    |");
            EntreesSorties.afficherMessage("| Retour Menu Principal : 0                              |");
            EntreesSorties.afficherMessage(" ========================================================");
            menuLect = EntreesSorties.lireEntier();

            switch (menuLect) {
                case 1: {
                    DonneesUtilitaire.loadDB(_bibliotheque);
                    _bibliotheque.nouvelExemplaire();
                    DonneesUtilitaire.updateDB(_bibliotheque);
                    break;
                }
                case 2: {
                    DonneesUtilitaire.loadDB(_bibliotheque);
                    _bibliotheque.consulterExemplairesOuvrage();
                    DonneesUtilitaire.updateDB(_bibliotheque);
                    break;
                }
                case 3: {
                    DonneesUtilitaire.loadDB(_bibliotheque);
                    _bibliotheque.consulterExemplaires();
                    DonneesUtilitaire.updateDB(_bibliotheque);
                    break;
                }
                case 0:
                    break;
                default: {
                    System.out.println("Choix non valide");
                    break;
                }
            }
        } while (menuLect != 0);
    }
}





