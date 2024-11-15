package src.controllers;

import src.views.Menu;
import src.views.Pprint;
// View
import src.views.determinan.DeterminanView;
// Model
import src.models.determinan.ReduksiBaris;
import src.models.determinan.EkspansiKofaktor;
import src.models.determinan.Matrix2x2;

import src.helpers.Utils;

// Data types
import src.datatypes.Matrix;
import src.datatypes.Tuple3;

public class Determinan {
    private DeterminanView view;
    private Matrix matrix;
    private Tuple3<Integer, Integer, Matrix> input;
    private Double result;
    private int n;
    private Pprint pprint = new Pprint();

    public Determinan() {
        view = new DeterminanView();
    }

    public void getInput(int c) {
        view.showHeader(c);
        int method = new Menu().getMethod();
        if (method == 1) {
            input = view.getInputFromFile();
            pprint.inputMatrix();
            Utils.printMatrix(input.getItem3());
            n = input.getItem1();
            matrix = input.getItem3();
        } else {
            input = view.getInput();
            n = input.getItem1();
            Utils.printMatrix(input.getItem3());
            matrix = input.getItem3();
        }
    }

    public double matrix2x2() {
        if (n != 2) {
            return Double.NaN;
        }

        Matrix2x2 matrix2x2 = new Matrix2x2();
        double result = matrix2x2.main(matrix);
        return result;
    }

    public double reduksiBaris() {
        ReduksiBaris reduksiBaris = new ReduksiBaris();
        double result = reduksiBaris.main(matrix);
        return result;
    }

    public double ekspansiKofaktor() {
        double result = new EkspansiKofaktor().main(matrix);
        return result;
    }

    public void main() {
        int choice = view.getChoice();
        getInput(choice);
        switch (choice) {
            case 1:
                result = matrix2x2();
                break;
            case 2:
                result = reduksiBaris();
                break;
            case 3:
                result = ekspansiKofaktor();
                break;
            default:
                break;
        }
        view.printDeterminant(result);
        view.saveOutput(result);
        pprint.thanks();
    }
}
