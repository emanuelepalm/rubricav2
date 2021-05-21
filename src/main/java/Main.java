import entities.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        if(args.length > 0) {
            try {
                for (int i = 0; i < args.length; i++) {
                    switch (args[i]) {
                        case "-populate":
                            menu.populate();
                            break;
                        case "-help":
                            menu.help();
                            break;
                        case "-export":
                            menu.setFileNameExp(args[i + 1]);
                            break;
                    }
                }
            }catch (Exception e) {
                System.out.println(e.getMessage() + "\n" +e.getCause());
            }
        }
        while(true){
            menu.mainMenu();
        }
    }
}
