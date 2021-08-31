package com.palmieri.interfaces;

import com.palmieri.models.Contatti;

import java.util.ArrayList;

public interface Actions {
    void addOne(Contatti contatto);
    void updateOne(int i, Contatti newContatto);
    boolean deleteOne(int i);
    ArrayList<Contatti> searchByName(String firstName);
    void exportRubrica(String fileName);
    void importRubrica(String fileName);
}
