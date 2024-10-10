package src.controllers;

import src.datatypes.Matrix;
import src.datatypes.Tuple4;
import src.models.interpolasiPolinom.InterpolasiPolinom;
import src.views.interpolasiPolinom.InterpolasiPolinomView;

public class InterpolasiPolinomController {
    private InterpolasiPolinomView view = new InterpolasiPolinomView();
    private Matrix X, y;
    private Double x, y_pred;

    public void main() {
        InterpolasiPolinom model = new InterpolasiPolinom();
        Tuple4<Integer, Integer, Matrix, Matrix> input = view.getInput();

        X = input.getItem3();
        y = input.getItem4();
        x = X.get(X.getRowCount()-1, 0);

        model.fit(X, y);
        y_pred = model.predict(x);
        view.printPrediction(y_pred);
    }
}   
