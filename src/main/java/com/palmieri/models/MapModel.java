package com.palmieri.models;

import com.palmieri.entities.Rubrica;

import java.lang.reflect.Field;

public class MapModel {
    private Ruoli ruolo;
    private Rubrica rubrica;

    public Ruoli getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruoli ruolo) {
        this.ruolo = ruolo;
    }

    public Rubrica getRubrica() {
        return rubrica;
    }

    public void setRubrica(Rubrica rubrica) {
        this.rubrica = rubrica;
    }
    public MapModel(Ruoli ruolo, Rubrica rubrica) {
        this.ruolo = ruolo;
        this.rubrica = rubrica;
    }
    @Override
    public boolean equals(Object obj) {
        for(Field f:  obj.getClass().getDeclaredFields()){
            try {
                if(!f.get(this).equals(f.get(obj))){
                    System.out.println("aaaaaaaaa");
                    return false;

                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
