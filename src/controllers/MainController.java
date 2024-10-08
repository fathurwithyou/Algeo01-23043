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
            case 3:
                MatriksBalikanController mbc = new MatriksBalikanController();
                mbc.main();
                break;
            case 6:
                LinearRegressionController lrc = new LinearRegressionController();
                lrc.main();
                break;
            default:
                break;
        }
    }
}
