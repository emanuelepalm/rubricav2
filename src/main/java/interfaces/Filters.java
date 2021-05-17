package interfaces;

import models.Contatti;

import java.util.ArrayList;

public interface Filters {
    void start();
    void mainMenu();
    Contatti contattoDaInput();
    void print(ArrayList<Contatti> contattiArrayList);
}
