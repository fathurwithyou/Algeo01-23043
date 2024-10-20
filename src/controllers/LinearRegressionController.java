package src.controllers;

import src.datatypes.Tuple5;
import src.datatypes.Tuple4;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.views.regression.linearRegression.LinearRegressionView;
import src.models.linearRegression.LinearRegression;

public class LinearRegressionController {
    private LinearRegressionView view = new LinearRegressionView();
    private Matrix y_pred, y, X, X_test;

    public void getInput() {
        int method = view.getMethod();
        if (method == 1) {
            Tuple5<Integer, Integer, Matrix, Matrix, Matrix> input = view.getInputFromFile(1);
            X = input.getItem3();
            y = input.getItem4();
            X_test = input.getItem5();

        } else {
            Tuple4<Integer, Integer, Matrix, Matrix> input = view.getInput();
            X = input.getItem3();
            y = input.getItem4();
            Tuple3<Integer, Integer, Matrix> inputToPredict = view.getInputToPredict(input.getItem1());
            X_test = inputToPredict.getItem3();
        }
    }

    public void main() {
        int choice = view.getChoice();
        LinearRegression model = null;
        if (choice >= 1 && choice <= 2) {
            getInput();
            switch (choice) {
                case 1:
                    model = new LinearRegression("OLS");
                    model.fit(X, y);
                    y_pred = model.predict(X_test);
                    view.printOutput(model.getBeta(), y_pred);
                    break;
                case 2:
                    double alpha = view.getAlpha();
                    model = new LinearRegression("Ridge", alpha);
                    model.fit(X, y);
                    y_pred = model.predict(X_test);
                    view.printOutput(model.getBeta(), y_pred);
                    break;
            }
        }
    }
}
