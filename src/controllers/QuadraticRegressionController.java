package src.controllers;

import src.datatypes.Tuple4;
import src.datatypes.Tuple5;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.views.Menu;
import src.views.regression.quadraticRegression.QuadraticRegressionView;
import src.models.quadraticRegression.QuadraticRegression;

public class QuadraticRegressionController {
    private QuadraticRegressionView view = new QuadraticRegressionView();

    private Matrix y_pred, y, X, X_test;

    public void getInput() {
        view.showHeader();
        int method = new Menu().getMethod();
        if (method == 1) {
            Tuple5<Integer, Integer, Matrix, Matrix, Matrix> input = view.getInputFromFile(2);
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
        getInput();
        QuadraticRegression model = new QuadraticRegression("Ridge", 0);
        model.fit(X, y);
        y_pred = model.predict(X_test);
        view.printOutput(model.getBeta(), y_pred, X.getColumnCount());
    }
}
