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
            pprint.inputMatrix();
            input = view.getInput();
            n = input.getItem1();
            matrix = input.getItem3();
        }
    }

    public double matrix2x2() {

        if (n != 2) {
            System.out.println("Matriks harus berukuran 2x2.");
            return Double.NaN; // kalau error return NaN, kalau mau while loop sampai hasil benar boleh
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
                double matrix2x2_result = matrix2x2();
                view.printDeterminant(matrix2x2_result);
                break;
            case 2:
                double reduksibaris_result = reduksiBaris();
                view.printDeterminant(reduksibaris_result);
                break;
            case 3:
                double ekspansikofaktor_result = ekspansiKofaktor();
                view.printDeterminant(ekspansikofaktor_result);
                break;
            default:
                break;
        }
    }
}
