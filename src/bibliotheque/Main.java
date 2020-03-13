package bibliotheque;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

    public static void main(String[] args)  {

        Bibliotheque bibliotheque = new Bibliotheque();
        MenuBiblio menu = new MenuBiblio(bibliotheque);
        menu.menuPrincipal();

    }
}
