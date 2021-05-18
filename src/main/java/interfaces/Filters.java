package interfaces;

import models.Contatti;
import java.util.ArrayList;

public interface Filters {

    Contatti contattoDaInput();
    void print(ArrayList<Contatti> contattiArrayList);
}
