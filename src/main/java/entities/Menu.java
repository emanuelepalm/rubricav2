package entities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import interfaces.Filters;
import models.Contatti;
import models.MapModel;
import models.Ruoli;
import utils.FileHandler;
import utils.InputHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Menu implements Filters {
    private Rubrica rubrica;
    private Map<Ruoli, Rubrica> mapRubrica = new HashMap();
    private String ruoloNome;

    public void mainMenu() {
        if (this.mapRubrica.isEmpty()) {
            System.out.println("Non esistono rubriche e ruoli!");
            System.out.println("Vuoi: \n1)Aggiungere un nuovo ruolo\n2)Importare la rubrica");
            int nextInt = InputHandler.nextInt();
            switch(nextInt) {
                case 1:
                addRubrica();
                break;
                case 2:
                    System.out.println("Inserisci il nome della rubrica da importare");
                    importMapRubrica(InputHandler.nextLn());
            }
        } else {
            System.out.println("1)Aggiungere un nuovo ruolo\n2)Visualizza tutti i ruoli\n3)Accedi alla rubrica di un ruolo\n4)Modifica un ruolo\n5)Elimina un ruolo\n6)Importa Json\n7)Esporta Json\n0)Esci");
            int nextInt = InputHandler.nextInt();
            String fileName;
            switch (nextInt) {
                case 1:
                    addRubrica();
                    break;
                case 2:
                    printRuoli();
                    break;
                case 3:
                    System.out.println("Inserisci il ruolo: ");
                    String ruoloName = InputHandler.nextLn();
                    for (Ruoli ruolo: mapRubrica.keySet()) {
                        if(ruolo.getRole().equals(ruoloName)) {
                            Rubrica rubrica = mapRubrica.get(ruolo);
                            ruoloNome = ruoloName;
                            mainMenuRubrica(rubrica);
                    } else {
                            System.out.println("Non esiste nessun ruolo con questo nome!");
                        }

                    }
                    break;
                case 4:
                    System.out.println("Inserisci il nome del ruolo da modificare");
                    String ruoloSearch = InputHandler.nextLn();
                    if(!InputHandler.checkRuolo(ruoloSearch, mapRubrica.keySet())) {
                        System.out.println("Inserisci il nuovo nome del ruolo");
                        ruoloName = InputHandler.nextLn();
                        System.out.println("Inserisci la nuova descrizione");
                        String desc = InputHandler.nextLn();
                        for (Ruoli ruolo: mapRubrica.keySet()) {
                            if(ruolo.getRole().equals(ruoloSearch)) {
                                ruolo.setRole(ruoloName);
                                ruolo.setDescription(desc);
                                System.out.println("Ruolo Aggiornato");
                            }
                        }
                    } else {
                        System.out.println("Questo nome non è associato ad alcun ruolo!");
                    }
                    break;
                case 5:
                    System.out.println("Inserisci il nome del ruolo da eliminare");
                    ruoloSearch = InputHandler.nextLn();
                    if(!InputHandler.checkRuolo(ruoloSearch, mapRubrica.keySet())) {
                        for (Ruoli ruolo: mapRubrica.keySet()) {
                            if(ruolo.getRole().equals(ruoloSearch)) {
                                mapRubrica.remove(ruolo);
                                System.out.println("Contatto eliminato");
                                break;
                            }
                        }
                    } else {
                        System.out.println("Questo nome non è associato ad alcun ruolo!");
                    }
                    break;
                case 6:
                    System.out.println("Inserisci il nome del file da caricare:");
                    fileName = InputHandler.nextLn();
                    importMapRubrica(fileName);
                    break;
                case 7:
                    System.out.println("Inserisci il nome del file da salvare:");
                    fileName = InputHandler.nextLn();
                    FileHandler.writeMapInFile(fileName, mapRubrica);
                    break;
                case 0:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Errore numero non valido");
            }
        }

    }

    public void addRubrica() {
        System.out.println("Inserisci il nome del ruolo");
        String nomeRuolo = InputHandler.nextLn();
        System.out.println("Inserisci una descrizione");
        String desc = InputHandler.nextLn();
        if(InputHandler.checkRuolo(nomeRuolo, mapRubrica.keySet())) {
            Ruoli ruolo = new Ruoli(nomeRuolo, desc);
            mapRubrica.put(ruolo, new Rubrica());
        } else {
            System.out.println("Questo ruolo esiste già!");
            addRubrica();
        }
    }

    public void printRuoli() {
        for(Ruoli ruolo: this.mapRubrica.keySet()) {
            Rubrica rubrica = this.mapRubrica.get(ruolo);
            System.out.println("Nome: " + ruolo.getRole() + "\n" + "Descrizione: " + ruolo.getDescription()  + "\n" + rubrica.getContattiList().size() + " contatti");
        }
    }

    public void updateRubricaInMap(Rubrica rubrica) {
        for (Ruoli ruolo : mapRubrica.keySet()) {
            if (ruolo.getRole().equals(ruoloNome)) {
                mapRubrica.put(ruolo, rubrica);
            }
        }
    }

    public Menu() {

    }

    public void mainMenuRubrica(Rubrica rubrica) {
        boolean in = true;
        while (in) {
            System.out.println("\nHai " + rubrica.getContattiList().size() + " contatti in rubrica");
            System.out.println("Cosa vuoi fare?\n1)Visualizza tutte le voci in rubrica\n2)Aggiungi una voce in rubrica \n3)Modifica una voce in rubrica \n4)Elimina una voce in rubrica \n5)Ricerca per Nome\n6)Esporta entities.Rubrica\n7)Importa entities.Rubrica\n0)Esci ");
            int num = InputHandler.nextInt();
            switch (num) {
                case 0:
                    updateRubricaInMap(rubrica);
                    in = false;
                    break;
                case 1:
                    print(rubrica.getContattiList());
                    break;
                case 2:
                    rubrica.addOne(contattoDaInput());
                    break;
                case 3:
                    System.out.println("Inserisci il numero del contatto da modificare");
                    rubrica.updateOne(InputHandler.nextInt(), contattoDaInput());
                    break;
                case 4:
                    System.out.println("Inserisci l'indice del contatto da eliminare");
                    rubrica.deleteOne(InputHandler.nextInt());
                    break;
                case 5:
                    System.out.println("Inserisci il nome da cercare");
                    ArrayList<Contatti> searchResult = rubrica.searchByName(InputHandler.nextLn());
                    if (searchResult.size() > 0) {
                        print(searchResult);
                    } else {
                        System.out.println("La ricerca non ha prodotto risultati");
                    }
                    break;
                case 6:
                    System.out.println("Inserisci il nome del backup da salvare:");
                    rubrica.exportRubrica(InputHandler.nextLn());
                    break;
                case 7:
                    System.out.println("Inserisci il nome del backup da importare: ");
                    rubrica.importRubrica(InputHandler.nextLn());
                    break;

                default:
                    System.err.println("TASTO NON VALIDO!");
                    mainMenu();
            }
        }
    }
    @Override
    public Contatti contattoDaInput() {
        System.out.println("Inserisci il Nome");
        String firstName = InputHandler.nextLn();
        if (firstName == null || firstName.trim().isEmpty()) {
            System.err.println("IL NOME E' OBBLIGATORIO!");
            contattoDaInput();
        }
        System.out.println("Inserisci il cognome");
        String lastName = InputHandler.nextLn();
        System.out.println("Inserisci il Numero di telefono");
        String number = InputHandler.nextLn();
        if (!InputHandler.checkNumber(number)) {
            System.out.println("FORMATO NON VALIDO\nIL NUMERO E' OBBLIGATORIO\nRicorda che il numero di telefono può avere solo 10 cifre");
            contattoDaInput();
        }
        System.out.println("Inserisci l'indirizzo email");
        String email = InputHandler.nextLn();
        if (!InputHandler.checkEmail(email)) {
            System.out.println("FORMATO NON VALIDO");
            contattoDaInput();
            }
        return new Contatti(firstName,lastName,number,email);
    }

    @Override
    public void print(ArrayList<Contatti> contatti) {
        int i = 1;
        for (Contatti contatto:contatti) {
            System.out.println("\n" + i +")NOME:  " + contatto.getFirstName());
            System.out.println(   "COGNOME: " + contatto.getLastName());
            System.out.println(   "EMAIL:   " + contatto.getEmail());
            System.out.println(   "NUMERO:  " + contatto.getNumber());
            System.out.println(   "UID:     " + contatto.getUid());
            i++;
        }
    }
    public void importMapRubrica(String fileName) {
        try {
            String json = FileHandler.readFileMap(fileName);
            ArrayList<MapModel> mapList= new ArrayList<MapModel>(Arrays.asList(new Gson().fromJson(json, MapModel[].class)));
            for(MapModel entry : mapList) {
                if(InputHandler.checkRuolo(entry.getRuolo().getRole(), mapRubrica.keySet())) {
                    mapRubrica.put(entry.getRuolo(),entry.getRubrica());
                } else {
                    System.out.println("Errore importando: "+ entry.getRuolo().getRole() +"\nQuesto ruolo esiste già!\nI contatti del ruolo verranno sovrascritti");
                    mapRubrica.put(InputHandler.getRoleFromName(entry.getRuolo().getRole(), mapRubrica.keySet()),entry.getRubrica());
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}