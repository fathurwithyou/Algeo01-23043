package src.controllers;

import src.views.regression.RegressionView;


public class RegressionController {
    private RegressionView view = new RegressionView();
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

                System.out.println("Keluar");
                break;
        }
    }
}
