package src.controllers;

import src.datatypes.Tuple5;
import src.datatypes.Tuple4;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.views.Menu;
import src.views.Pprint;
import src.views.regression.linearRegression.LinearRegressionView;
import src.models.linearRegression.LinearRegression;

import src.helpers.Utils;

public class LinearRegressionController {
    private Pprint pprint = new Pprint();
    private LinearRegressionView view = new LinearRegressionView();
    private Matrix y_pred, y, X, X_test;
    public StringBuilder savedString = new StringBuilder();

    public void getInput() {
        view.showHeader();
        int method = new Menu().getMethod();
        if (method == 1) {
            Tuple5<Integer, Integer, Matrix, Matrix, Matrix> input = view.getInputFromFile(1);
            X = input.getItem3();
            y = input.getItem4();
            pprint.showData();
            Utils.printData(X, y);
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
        LinearRegression model = null;
        getInput();
        model = new LinearRegression("Ridge", 0.001);
        model.fit(X, y);
        y_pred = model.predict(X_test);
        view.printOutput(model.getBeta(), y_pred, savedString);
        view.saveOutput(savedString);
        pprint.thanks();
    }

}
