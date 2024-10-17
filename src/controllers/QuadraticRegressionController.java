package src.controllers;

import src.datatypes.Tuple4;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.views.regression.quadraticRegression.QuadraticRegressionView;
import src.models.quadraticRegression.QuadraticRegression;

public class QuadraticRegressionController {
    private QuadraticRegressionView view = new QuadraticRegressionView();
    private Matrix y_pred, y, X;

    public void main() {
        int choice = view.getChoice();
        QuadraticRegression model = null;
        
        if (choice >= 1 && choice <= 2) {
            Tuple4<Integer, Integer, Matrix, Matrix> input = view.getInput();
            X = input.getItem3();
            y = input.getItem4();
            Tuple3<Integer, Integer, Matrix> inputToPredict = view.getInputToPredict(input.getItem1());
            switch (choice) {
                case 1:
                    model = new QuadraticRegression("Ridge", 0.01);
                    model.fit(X, y);

                    y_pred = model.predict(inputToPredict.getItem3());
                    view.printMatrix(y_pred);
                    break;
            }
        } else {
            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        }
    }
}
