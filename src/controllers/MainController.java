package src.controllers;
import src.views.Menu;
import src.controllers.*;

public class MainController {
    public void main() {
        Menu menu = new Menu();
        int choice = menu.getChoice();
        switch (choice) {
            case 1:
                SistemPersamaanLinier spl = new SistemPersamaanLinier();
                spl.main();
                break;
            default:
                break;
        }
    }
}
