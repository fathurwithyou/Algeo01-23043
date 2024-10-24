package src.controllers;

import src.views.regression.RegressionView;
import src.views.Pprint;

public class RegressionController {
    private RegressionView view = new RegressionView();
    private Pprint pprint = new Pprint();
    private LinearRegressionController LinearRegressionController = new LinearRegressionController();
    private QuadraticRegressionController QuadraticRegressionController = new QuadraticRegressionController();

    public void main() {
        int choice = view.getChoice();
        switch (choice) {
            case 1:
                LinearRegressionController.main();
                break;

            case 2:
                QuadraticRegressionController.main();
                break;
            case 3:
                pprint.thanks();
                break;
        }
    }
}
