package com.palmieri.entities;

import com.google.gson.Gson;
import com.palmieri.interfaces.Actions;
import com.palmieri.models.Contatti;
import com.palmieri.utils.FileHandler;

import java.util.*;

public class Rubrica implements Actions {
    private ArrayList<Contatti> contattiList = new ArrayList<Contatti>();

    public Rubrica() {
    }
    public ArrayList<Contatti> getContattiList() {
        return this.contattiList;
    }
    public void setContattiList(ArrayList<Contatti> contattiList) {
        this.contattiList = contattiList;
    }

    public Contatti addPersona(String firstName, String lastName,String number, String email) {
        return new Contatti(firstName, lastName, number, email);
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
    public boolean deleteOne(int i) {
        boolean check;
        try {
            this.contattiList.remove(i);
            check = true;
        }catch(Exception e){
            check = false;
            System.out.println("Indice troppo alto!");
            System.out.println(e.getMessage());
        }
        return check;
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
        FileHandler.writeArrayInFile(fileName,this.contattiList);
    }

    @Override
    public void importRubrica(String fileName) {
        try {
       String json = FileHandler.readFileRubrica(fileName);
            this.contattiList.addAll(new ArrayList<Contatti>(Arrays.asList(new Gson().fromJson(json, Contatti[].class))));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
