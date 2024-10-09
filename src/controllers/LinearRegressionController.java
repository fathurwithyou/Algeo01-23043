package src.controllers;

import src.datatypes.Tuple4;
import src.datatypes.Matrix;
import src.views.regression.linearRegression.LinearRegressionView;
import src.models.linearRegression.LinearRegression;

public class LinearRegressionController {
    private LinearRegressionView view = new LinearRegressionView();
    private Matrix y_pred, y, X;

    public void main() {
        int choice = view.getChoice();
        LinearRegression model = null;
        if (choice >= 1 && choice <= 2) {
            Tuple4<Integer, Integer, Matrix, Matrix> input = view.getInput();
            X = input.getItem3();
            y = input.getItem4();
            switch (choice) {
                case 1:
                    model = new LinearRegression("OLS");
                    model.fit(X, y);
                    y_pred = model.predict(X);
                    view.printMatrix(y_pred);
                    break;
                case 2:
                    double alpha = view.getAlpha();
                    model = new LinearRegression("Ridge", alpha);
                    model.fit(X, y);
                    y_pred = model.predict(X);
                    view.printMatrix(y_pred);
                    break;
            }
        }
    }
}
