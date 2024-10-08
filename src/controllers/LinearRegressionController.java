package src.controllers;

import src.datatypes.Tuple4;
import src.datatypes.Matrix;
import src.views.linearRegression.LinearRegressionView;
import src.models.linearRegression.LinearRegression;


public class LinearRegressionController {
    private LinearRegressionView view = new LinearRegressionView();
    private LinearRegression model = new LinearRegression();

    public Matrix OLSLinearRegression(){
        Tuple4<Integer, Integer, Matrix, Matrix> input = view.getInput();
        Matrix X = input.getItem3();
        Matrix y = input.getItem4();
        model.fit(X, y);
        Matrix y_pred = model.predict(X);
        return y_pred;
    }

    public void main(){
        int choice = view.getChoice();
        Matrix y_pred = null;
        switch (choice) {
            case 1:
                y_pred = OLSLinearRegression();
                view.printMatrix(y_pred);
                break;
            case 2:
                System.out.println("Keluar");
                break;
        }
    }
}
