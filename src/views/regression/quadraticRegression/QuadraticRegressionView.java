package src.views.regression.quadraticRegression;

import java.util.Scanner;

import src.datatypes.Matrix;
import src.helpers.Utils;
import src.views.regression.RegressionView;
import src.views.Menu;

public class QuadraticRegressionView extends RegressionView {
    private Menu menu = new Menu();

    public void showHeader() {
        Utils.clearTerminal();
        String header = "\n\033[1m\033[32m" + getString("quadraticRegression/header") + "\033[0m";
        System.out.println(header);

    }

    public void printOutput(Matrix beta, Matrix y_pred, int numFeatures) {
        menu.showResult();
        System.out.print("f(X) = " + String.format("%.2f", beta.get(0, 0)));

        int index = 1;

        // Linear terms
        for (int i = 1; i <= numFeatures; i++) {
            double coeff = beta.get(index++, 0);
            if (coeff >= 0) {
                System.out.print(" + " + String.format("%.2f", coeff) + "x" + i);
            } else {
                System.out.print(" - " + String.format("%.2f", Math.abs(coeff)) + "x" + i);
            }
        }

        // Squared terms
        for (int i = 1; i <= numFeatures; i++) {
            double coeff = beta.get(index++, 0);
            if (coeff >= 0) {
                System.out.print(" + " + String.format("%.2f", coeff) + "x" + i + "^2");
            } else {
                System.out.print(" - " + String.format("%.2f", Math.abs(coeff)) + "x" + i + "^2");
            }
        }

        // Interaction terms
        for (int i = 1; i <= numFeatures; i++) {
            for (int j = i + 1; j <= numFeatures; j++) {
                double coeff = beta.get(index++, 0);
                if (coeff >= 0) {
                    System.out.print(" + " + String.format("%.2f", coeff) + "x" + i + "x" + j);
                } else {
                    System.out.print(" - " + String.format("%.2f", Math.abs(coeff)) + "x" + i + "x" + j);
                }
            }
        }

        System.out.println();

        // Print the predicted values
        for (int i = 0; i < y_pred.getRowCount(); i++) {
            System.out.println("f(x" + (i + 1) + ") = " + String.format("%.2f", y_pred.get(i, 0)));
        }
    }

}
