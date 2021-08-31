package com.palmieri.interfaces;

import com.palmieri.models.Contatti;
import java.util.ArrayList;

public interface Filters {

    Contatti contattoDaInput();
    void print(ArrayList<Contatti> contattiArrayList);
}
