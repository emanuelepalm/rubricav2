import jdk.internal.util.xml.impl.Input;
import models.Contatti;
import utils.InputHandler;

import java.util.ArrayList;

public class Menu {
    private Rubrica rubrica;
    public Menu() {
        start();
    }

    public void start() {
        this.rubrica = Rubrica.getInstance();
    }

    public void mainMenu() {
        System.out.println("Hai " + rubrica.getContattiList().size() + " contatti in rubrica");
        String verbo;
        System.out.println("Cosa vuoi fare?\n1)Visualizza tutte le voci in rubrica\n2)Aggiungi una voce in rubrica \n3)Modifica una voce in rubrica \n4)Elimina una voce in rubrica \n5)Ricerca per Nome\n6)Esporta Rubrica\n7)Importa Rubrica\n0)Esci ");
        int num = InputHandler.nextInt();
        switch (num) {
            case 0:
                System.out.println("Grazie e Arrivederci!!!");
                System.exit(0);
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
                if(searchResult.size()>0) {
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

    public void print(ArrayList<Contatti> contatti) {
        for (Contatti contatto:contatti) {
            int i = 1;
            System.out.println("\n" + i +")NOME:   " + contatto.getFirstName());
            System.out.println(   "COGNOME: " + contatto.getLastName());
            System.out.println(   "EMAIL:   " + contatto.getEmail());
            System.out.println(   "NUMERO:  " + contatto.getNumber());
            System.out.println(   "UID:     " + contatto.getUid());
            System.out.println("\n");
        }
    }
}