package interfaces;

import models.Contatti;

import java.util.ArrayList;

public interface Actions {
    void addOne(Contatti contatto);
    void updateOne(int i, Contatti contatto);
    void deleteOne(int i);
    ArrayList<Contatti> searchByName(String firstName);
    void exportRubrica(String fileName);
    void importRubrica(String fileName);
}