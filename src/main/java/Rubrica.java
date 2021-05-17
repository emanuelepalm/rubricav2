import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import interfaces.Actions;
import models.Contatti;
import models.Ruoli;
import sun.reflect.generics.tree.Tree;
import utils.FileHandler;
import utils.InputHandler;

import java.util.*;

public class Rubrica implements Actions {
    private ArrayList<Contatti> contattiList = new ArrayList<Contatti>();
    private Map<Object,Object> mapRubrica = new HashMap();

    public void mapp() {
        mapRubrica.put("pippo", contattiList);
        for (Map.Entry entry:mapRubrica.entrySet()) {
           if(entry.getKey().equals("pippo")){
               Menu menu = new Menu();
               menu.print((ArrayList<Contatti>) entry.getValue());
           }
        }
    }

    public Rubrica() {
    }



    public ArrayList<Contatti> getContattiList() {
        return this.contattiList;
    }

    public void setContattiList(ArrayList<Contatti> contattiList) {
        this.contattiList = contattiList;
    }


    @Override
    public void addOne(Contatti contatto) {
        this.contattiList.add(contatto);
    }

    @Override
    public void updateOne(int i, Contatti contatto) {
        this.contattiList.set(i, contatto);
    }

    @Override
    public void deleteOne(int i) {
        try {
            this.contattiList.remove(i);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Contatti> searchByName(String firstName) {
        ArrayList<Contatti> searchResult = new ArrayList<Contatti>(10);
        for (int i = 0; i < this.contattiList.size(); i++) {
            if (this.contattiList.get(i).getFirstName().equals(firstName)) {
                searchResult.add(this.contattiList.get(i));
            }
        }
        return searchResult;
    }

    @Override
    public void exportRubrica(String fileName) {
        FileHandler.writeFile(fileName,this.contattiList);
    }

    @Override
    public void importRubrica(String fileName) {
        try {
       String json = FileHandler.readFile(fileName);
            this.contattiList.addAll(new ArrayList<Contatti>(Arrays.asList(new Gson().fromJson(json, Contatti[].class))));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
