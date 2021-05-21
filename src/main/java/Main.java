import entities.Menu;
import utils.FileHandler;
import utils.InputHandler;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Properties prop = FileHandler.loadProp();


        while(true){
            System.out.println("1)vai al menu\n2)Importa properties da file\n3)Esporta arguments in properties");
            switch (InputHandler.nextInt()) {
                case 1:
                    menu.mainMenu();
                    break;
                case 2:
                    menu.importProperties(prop);
                    break;
                case 3:
                    menu.exportProperties(args);
                    break;
                case 4:
                    System.exit(1);
            }

        }
    }
}
