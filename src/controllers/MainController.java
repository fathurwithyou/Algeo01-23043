package src.controllers;
import src.views.Menu;

public class MainController {
    public void main() {
        Menu menu = new Menu();
        int choice = menu.getChoice();
        switch (choice) {
            case 1:
                SistemPersamaanLinier spl = new SistemPersamaanLinier();
                spl.main();
                break;
            case 2:
                Determinan det = new Determinan();
                det.main();
                break;
            default:
                break;
        }
    }
}
