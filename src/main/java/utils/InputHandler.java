package utils;

import models.Ruoli;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class InputHandler {
    /**
     * Gestione dell'eccezione InputMismatchException
     *
     * @return int nextInt (in caso di eccezione nextInt sarà uguale a -1)
     */
    public static int nextInt() {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        try {
            i = scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.err.println("HO DETTO UN NUMERO INTERO!");
            i = -1;
        }
        return i;
    }

    /**
     * Gestisce l'input next line impedendo loop, e stringhe vuote
     *
     * @return String nextLn
     */
    public static String nextLn() {
        Scanner scanner = new Scanner(System.in);
        String nextLn = scanner.nextLine();
        if (nextLn.trim().length() <= 0 || nextLn.isEmpty()) {
            nextLn = nextLn();
        }
        return nextLn;
    }

    /**
     * Controlla la formattazione della String numero inserita dall'utente
     *
     * @param number Stringa inserita in input dall'utente
     * @return boolean true = Il formato è corretto
     * false= Il formato è errato
     */

    public static boolean checkNumber(String number) {
        boolean ok = false;
        if (number.matches("[0-9]+") && number.length() == 10) {
            ok = true;
        }
        return ok;
    }

    /**
     * Controlla la formattazione della String email inserita dall'utente
     *
     * @param email Stringa inserita in input dall'utente
     * @return boolean true = Il formato è corretto
     * false= Il formato è errato
     */
    public static boolean checkEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static boolean checkRuolo(String ruoloName, Set<Ruoli> keys) {
        Boolean isnew = true;
        for (Ruoli ruolo : keys) {
            if (ruolo.getRole().equalsIgnoreCase(ruoloName)) {
                isnew = false;
            }
        }
        return isnew;
    }

}
