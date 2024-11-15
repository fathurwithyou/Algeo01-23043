package src.controllers;
import src.views.Menu;
import src.views.Pprint;

public class MainController {
    public void main() {
        Menu menu = new Menu();
        Pprint pprint = new Pprint();
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
            case 4:
                InterpolasiPolinomController ipc = new InterpolasiPolinomController();
                ipc.main();
                break;
            case 5:
                BicubicSplineInterpolationController bsic = new BicubicSplineInterpolationController();
                bsic.main();
                break;
            case 6:
                RegressionController rc = new RegressionController();
                rc.main();
                break;
            case 7:
                ImageResizingController irc = new ImageResizingController();
                irc.main();
                break;
            case 8:
                pprint.thanks();
                break;
            default:
                break;
        }
    }
}
