package models;

import entities.Rubrica;

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

}
