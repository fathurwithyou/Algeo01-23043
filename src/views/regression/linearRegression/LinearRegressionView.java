package src.views.regression.linearRegression;

import java.util.Scanner;

import src.datatypes.Matrix;
import src.helpers.GetString;
import src.helpers.Utils;
import src.views.regression.RegressionView;
import src.views.Menu;
import src.views.Pprint;

public class LinearRegressionView extends RegressionView {
    private Menu menu = new Menu();
    private Pprint pprint = new Pprint();
    @Override
    public void showMenu() {
        System.out.println("Regresi Linear Berganda");
        System.out.println("1. Metode OLS");
        System.out.println("2. Metode Ridge");
        System.out.println("3. Keluar");
    }

    @Override
    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            System.out.print("Pilihan: ");
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 3);
        return choice;
    }

    public void showHeader() {
        Utils.clearTerminal();
        String header = "\n\033[1m\033[32m" + GetString.main("regression/linearRegression/header") + "\033[0m";
        System.out.println(header);
    }

    public void printOutput(Matrix beta, Matrix y_pred) {
        pprint.showResult();
        System.out.print("\033[1mf(X) = " + String.format("%.2f", beta.get(0, 0)));

        for (int i = 1; i < beta.getRowCount(); i++) {
            double coeff = beta.get(i, 0);
            if (coeff >= 0) {
                System.out.print(" + " + String.format("%.2f", coeff) + "x" + i);
            } else {
                System.out.print(" - " + String.format("%.2f", Math.abs(coeff)) + "x" + i);
            }
        }

        System.out.println();

        for (int i = 0; i < y_pred.getRowCount(); i++) {
            System.out.println("f(x" + (i + 1) + ") = " + String.format("%.2f", y_pred.get(i, 0)));
        }
    }
}
