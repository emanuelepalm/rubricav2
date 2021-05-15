import interfaces.Actions;
import models.Contatti;
import utils.FileHandler;
import utils.InputHandler;

import java.util.ArrayList;

public class Rubrica implements Actions {
    private static Rubrica rubricaInstance= null;
    private ArrayList<Contatti> contattiList = new ArrayList<Contatti>();

    private Rubrica() {
    }

    public static Rubrica getInstance() {
        if (rubricaInstance == null) {
            rubricaInstance = new Rubrica();
        }
        return rubricaInstance;
    }

    public ArrayList<Contatti> getContattiList() {
        return contattiList;
    }

    public void setContattiList(ArrayList<Contatti> contattiList) {
        this.contattiList = contattiList;
    }


    @Override
    public void addOne(Contatti contatto) {
        contattiList.add(contatto);
    }

    @Override
    public void updateOne(int i, Contatti contatto) {
        contattiList.set(i, contatto);
    }

    @Override
    public void deleteOne(int i) {
        contattiList.remove(i);
    }

    @Override
    public ArrayList<Contatti> searchByName(String firstName) {
        ArrayList<Contatti> searchResult = new ArrayList<Contatti>(10);
        for (int i = 0; i < contattiList.size(); i++) {
            if (this.contattiList.get(i).equals(firstName)) {
                searchResult.add(i, this.contattiList.get(i));
            }
        }
        return searchResult;
    }

    @Override
    public void exportRubrica(String fileName) {
        FileHandler.writeFile(fileName,rubricaInstance.contattiList);
    }

    @Override
    public void importRubrica(String fileName) {
        contattiList.addAll(FileHandler.readFile(fileName));
    }
}
