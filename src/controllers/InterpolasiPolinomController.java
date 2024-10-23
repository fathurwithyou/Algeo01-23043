package src.controllers;

import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.datatypes.Tuple4;
import src.datatypes.Tuple5;
import src.helpers.Utils;
import src.models.interpolasiPolinom.InterpolasiPolinom;
import src.views.Menu;
import src.views.interpolasiPolinom.InterpolasiPolinomView;
import src.views.Pprint;

public class InterpolasiPolinomController {
    private InterpolasiPolinomView view = new InterpolasiPolinomView();
    private Pprint pprint = new Pprint();
    private Matrix X, y, X_test, input;
    private Double x, y_pred;

    public void getInput() {
        view.showHeader(0);
        int method = new Menu().getMethod();
        if (method == 1) {
            Tuple5<Integer, Integer, Matrix, Matrix, Matrix> input = view.getInputFromFile(1);
            X = input.getItem3();
            y = input.getItem4();
            X_test = input.getItem5();
            
        } else {
            Tuple4<Integer, Integer, Matrix, Matrix> input = view.getInput();
            X = input.getItem3();
            y = input.getItem4();
            Tuple3<Integer, Integer, Matrix> inputToPredict = view.getInputToPredict(1);
            X_test = inputToPredict.getItem3();
        }
    }
    public void main() {
        InterpolasiPolinom model = new InterpolasiPolinom();
        getInput();
        model.fit(X, y);
        
        y_pred = model.predict(X_test.get(0, 0));
        pprint.showData();
        Utils.printData(X, y);


        view.printPrediction(model.getPers(), X_test.get(0, 0), y_pred);
    }
}   
