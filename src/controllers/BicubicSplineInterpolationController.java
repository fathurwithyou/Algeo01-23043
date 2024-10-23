package src.controllers;

import src.datatypes.Array;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.helpers.Utils;
import src.models.bicubicSplineInterpolation.BicubicSplineInterpolation;
import src.views.Menu;
import src.views.bicubicSplineInterpolation.BicubicSplineInterpolationView;
import src.views.Pprint;

public class BicubicSplineInterpolationController {
    private BicubicSplineInterpolationView view = new BicubicSplineInterpolationView();
    private Pprint pprint = new Pprint();
    private Matrix matrix;
    private Array X_test, y_test;
    private Tuple3<Matrix, Array, Array> input;
    public Double x, y, result;

    public void getInput() {
        view.showHeader();
        int method = new Menu().getMethod();
        if (method == 1) {
            input = view.getInputFromFile();
        } else {
            input = view.getInput();
        }
        matrix = input.getItem1();
        X_test = input.getItem2();
        y_test = input.getItem3();
        pprint.inputMatrix();
        Utils.printMatrix(input.getItem1());
    }

    public void main() {
        BicubicSplineInterpolation model = new BicubicSplineInterpolation();
        StringBuilder sb = new StringBuilder();
        getInput();
        Array pred = new Array(X_test.getSize());
        for (int i = 0; i < X_test.getSize(); i++) {
            x = X_test.get(i);
            y = y_test.get(i);
            model.fit(matrix, x, y);
            result = model.predict(x, y);
            pred.set(i, result);
        }
        pprint.showResult();
        view.printPrediction(result, X_test, y_test, pred, sb);        
        view.saveOutput(sb);
        pprint.thanks();
    }
}
