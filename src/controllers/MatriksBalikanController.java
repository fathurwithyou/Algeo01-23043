package src.controllers;

import src.views.Menu;
import src.views.Pprint;
import src.views.determinan.DeterminanView;
import src.views.matriksBalikan.MatriksBalikanView;

import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.models.determinan.EkspansiKofaktor;
import src.models.matriksBalikan.AdjoinMethod;
import src.models.matriksBalikan.GaussJordanMethod;

import src.helpers.Utils;

public class MatriksBalikanController {
    private MatriksBalikanView view = new MatriksBalikanView();
    private DeterminanView inputView = new DeterminanView();
    private AdjoinMethod adjoinMethod = new AdjoinMethod();
    public GaussJordanMethod gaussJordanMethod = new GaussJordanMethod();
    private Pprint pprint = new Pprint();
    private Matrix matrix;
    private Tuple3<Integer, Integer, Matrix> input;

    public boolean isSingular(Matrix matrix) {
        return new EkspansiKofaktor().main(matrix) == 0.0;
    }

    public Matrix adjoinMethod() {
        return adjoinMethod.main(matrix);
    }

    public Matrix gaussJordanMethod() {
        if (isSingular(matrix)) {
            view.showSingular();
            return null;
        }
        Matrix result = gaussJordanMethod.main(matrix);
        return result;
    }

    public void getInput() {
        int method = new Menu().getMethod();
        if (method == 1) {
            Tuple3<Integer, Integer, Matrix> input = inputView.getInputFromFile();
            matrix = input.getItem3();
        } else {
            Tuple3<Integer, Integer, Matrix> input = inputView.getInputFromFile();
            matrix = input.getItem3();
        }
    }

    public void getInput(int c) {
        view.showHeader(c);
        int method = new Menu().getMethod();
        if (method == 1) {
            input = view.getInputFromFile();
            pprint.inputMatrix();
            Utils.printMatrix(input.getItem3());
            matrix = input.getItem3();
        } else {
            pprint.inputMatrix();
            input = view.getInput();
            matrix = input.getItem3();
        }
    }

    public void main() {
        int choice = view.getChoice();
        getInput(choice);
        switch (choice) {
            case 1:
                matrix = gaussJordanMethod();
                if (matrix != null) {
                    pprint.showResult();
                    Utils.printMatrix(matrix);
                }
                break;
            case 2:
                matrix = adjoinMethod();
                if (matrix != null) {
                    pprint.showResult();
                    Utils.printMatrix(matrix);
                }
                break;
            case 3:
                System.out.println("Keluar");
                break;
        }
    }

}
