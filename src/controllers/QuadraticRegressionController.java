package src.controllers;

import src.datatypes.Tuple4;
import src.datatypes.Matrix;
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
            
            switch (choice) {
                case 1:
                    model = new QuadraticRegression("Ridge", 0.1);
                    model.fit(X, y);
                    y_pred = model.predict(X);
                    view.printMatrix(y_pred);
                    break;
            }
        } else {
            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        }
    }
}
